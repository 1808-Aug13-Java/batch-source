package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAccountManager {
	
	
	public void endUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if ((session != null) || (session.getAttribute("username") != null)) {
			// invalidates the session and unbinds any user session info
			session.invalidate();
		}
	}
}
