package controller.dispatcher;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import common.exception.BusinessException;
import common.exception.message.RawMessage;

/**
 * Servlet implementation class LoginSevlet
 */
@WebServlet({ "/login", "/logout" })
public class LoginSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(request, response);
	}

	/**
	 * POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("/login".equals(request.getServletPath())) {

			// get request params
			Optional<String> username = Optional.ofNullable(request.getParameter("txt_username"));
			Optional<String> pass = Optional.ofNullable(request.getParameter("txt_password"));
			if (!username.isPresent() || !pass.isPresent()) {
				throw new BusinessException(new RawMessage("Username and passoword can not be null"));
			}

			login(username.get(), pass.get());

		} else {
			logout();
		}
		response.sendRedirect(request.getContextPath() + "/home");
	}

	private void login(String username, String pass) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return;
		}
		String hexPass = new Sha256Hash(pass).toHex();
		UsernamePasswordToken token = new UsernamePasswordToken(username, hexPass);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			throw new BusinessException(new RawMessage("invalid username or password"));
		}
	}

	private void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
	}

}
