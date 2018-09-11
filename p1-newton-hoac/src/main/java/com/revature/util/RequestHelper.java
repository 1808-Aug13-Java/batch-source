package com.revature.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

	public static String process(HttpServletRequest request) throws IOException{
		switch(request.getParameter("destination")) {
			case "login":
				return "login";
			case "loginfail":
				return "loginfail";
			case "choose":
				return "choose";
			case "empprofile":
				return "empprofile";
			default:
				return "login";
		}
	}
}
