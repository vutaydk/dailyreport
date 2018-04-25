package common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import common.exception.BusinessException;
import common.exception.message.RawMessage;

public class Shiro {

	public static void checkRoles(String... roles) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {

			for (String role : roles) {
				if (currentUser.hasRole(role))
					return;
			}
		}
		throw new BusinessException(new RawMessage("You do not have permission"));
	}

	public static void checkRole(String role) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			if (currentUser.hasRole(role))
				return;
		}
		throw new BusinessException(new RawMessage("You do not have permission"));
	}

	public static boolean checkAuthentication() {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated())
			throw new BusinessException(new RawMessage("you need login"));
		return true;
	}
}
