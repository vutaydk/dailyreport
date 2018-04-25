package model.business.login;

import javax.enterprise.context.RequestScoped;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@RequestScoped
public class LogoutHandler {

	public void logout() {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();
		}
	}
}
