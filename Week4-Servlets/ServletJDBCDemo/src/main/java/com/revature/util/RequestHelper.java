package com.revature.util;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	
	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "directory":
			return "directory";
		case "new":
			return "new";
		default:
			return "home";
		}
	}

}
