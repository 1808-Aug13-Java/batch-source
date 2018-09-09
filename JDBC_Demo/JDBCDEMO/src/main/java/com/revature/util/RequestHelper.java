package com.revature.util;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	
	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "directory":
			System.out.println("directory");
			return "directory";
		case "new":
			System.out.println("new");
			return "new";
		default:
			System.out.println("home");
			return "home";
		}
	}

}
