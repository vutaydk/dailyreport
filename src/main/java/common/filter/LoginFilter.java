package common.filter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReportRepo;
import model.entity.Report;
import model.entity.User;

/**
 * Servlet Filter implementation class LoginFiter
 */
@WebFilter({
		"/home/*", "/report/*", "/project/*", "/rights/*", "/task/*"
})
public class LoginFilter implements Filter {

	private ReportRepo reportDao;

	public LoginFilter() {
		reportDao = new ReportRepo();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// request + response
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// URI login page
		String loginURI = req.getContextPath() + "/login";

		// Check user isPresent
		Optional<User> user = Optional.ofNullable((User) req.getSession().getAttribute("user"));
		if (user.isPresent()) {

			// model attribute
			modelAttribute(req, res);

			chain.doFilter(request, response);
			return;
		}

		// redirect to login page
		res.sendRedirect(loginURI);
	}

	public void modelAttribute(HttpServletRequest request, HttpServletResponse response) {

		Optional<List<Report>> reOptional = Optional.ofNullable(reportDao.getAll());
		if (reOptional.isPresent()) {

			Optional<User> usOptional = Optional.ofNullable((User) request.getSession().getAttribute("user"));
			if (usOptional.isPresent()) {

				Stream<Report> stream = reOptional.get().stream()
						.filter(r -> usOptional.get().getId().equals(r.getUserByUserId().getId()));
				request.setAttribute("listOwn", stream.collect(Collectors.toList()));
			} else {
				request.setAttribute("listOwn", reOptional.get());
			}
		}
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
