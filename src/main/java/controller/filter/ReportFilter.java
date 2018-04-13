package controller.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.util.DataValidation;
import model.entity.Report;
import model.entity.Rights;
import model.entity.User;
import model.repo.report.ReportRepoImpl;

/**
 * Servlet Filter implementation class LoginFiter
 */
@WebFilter("/report/*")
public class ReportFilter implements Filter {

	private ReportRepoImpl reportDao;

	public ReportFilter() {
		reportDao = new ReportRepoImpl();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// request + response
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if ("/report/edit".equals(req.getServletPath())) {
			Optional<String> id = Optional.ofNullable(request.getParameter("id"));
			if (id.isPresent()) {

				if (DataValidation.isNumber(id.get())) {

					Optional<Report> report = reportDao.findById(Integer.valueOf(id.get()));
					if (report.isPresent()) {

						Optional<User> userApprover = Optional.ofNullable(report.get().getApprover());
						if (!userApprover.isPresent()) {

							Optional<User> user = Optional.ofNullable((User) req.getSession().getAttribute("user"));
							if (user.isPresent()) {

								Optional<Rights> rights = Optional.ofNullable(user.get().getRights());

								boolean bool = user.get().getId().equals(report.get().getUser().getId());
								if ((rights.isPresent() && rights.get().getLevel() > 0) || bool) {

									chain.doFilter(request, response);
									return;
								} else
									req.getSession().setAttribute("messagePopup", "Permission.");
							} else
								req.getSession().setAttribute("messagePopup", "You need login.");
						} else
							req.getSession().setAttribute("messagePopup", "The report has been approved.");
					} else
						req.getSession().setAttribute("messagePopup", "Not found data.");
				} else
					req.getSession().setAttribute("messagePopup", "Invalid param.");
			} else
				req.getSession().setAttribute("messagePopup", "URL invalid.");

			// redirect to report page
			res.sendRedirect(req.getContextPath() + "/report");
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}