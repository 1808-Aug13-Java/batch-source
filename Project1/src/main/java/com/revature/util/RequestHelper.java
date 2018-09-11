package com.revature.util;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

	// provides a layer so the servlets don't have logic in them. best practice
	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "directory":
			return "directory"; // no need for breaks because of return statements
		case "new":
			return "new";
		default:
			return "home";
		}
	}
}
