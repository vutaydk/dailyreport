package controller.filter;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.User;
import model.repo.user.IUserRepo;
import model.repo.user.UserRepoImpl;

/**
 * Servlet Filter implementation class LoginFiter
 */
@WebFilter({ "/home/*", "/report/*", "/project/*", "/rights/*", "/task/*"/*, "/rest/*"*/ })
public class LoginFilter implements Filter {

	@Inject
	private IUserRepo userRepo;

	public LoginFilter() {
		userRepo = new UserRepoImpl();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// request + response + session
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();

		// URI login page
		String loginURI = request.getContextPath() + "/login";

		// check user session exist
		Optional<User> user = Optional.ofNullable((User) session.getAttribute("user"));
		if (user.isPresent()) {

			// check user exist in database
			user = userRepo.find(user.get().getId());
			if (user.isPresent()) {

				req.setAttribute("user", user.get());
				chain.doFilter(req, res);
				return;
			}
		}

		// redirect to login page
		response.sendRedirect(loginURI);
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
