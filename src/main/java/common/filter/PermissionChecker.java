package common.filter;

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

import model.entity.Rights;
import model.entity.User;

/**
 * Servlet Filter implementation class LoginFiter
 */
@WebFilter({
		"/rights/*", "/task/*", "/project/*"
})
public class PermissionChecker implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// request + response
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Optional<User> user = Optional.ofNullable((User) req.getSession().getAttribute("user"));
		if (user.isPresent()) {

			Optional<Rights> rights = Optional.ofNullable(user.get().getRights());
			if (rights.isPresent()) {

				if (rights.get().getLevel() > 0) {

					chain.doFilter(request, response);
					return;
				}

			}
			req.getSession().setAttribute("messagePopup", "Permission.");
		} else
			req.getSession().setAttribute("messagePopup", "You need login.");

		// redirect to home page
		res.sendRedirect(req.getContextPath() + "/home");
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
