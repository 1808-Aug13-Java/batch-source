
package com.revature.util;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	
	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "empLogin":
			return "empLogin";
		case "manLogin":
			return "manLogin";
		case "addReq":
			return "addReq";
		default:
			return "home";
		}
	}

}