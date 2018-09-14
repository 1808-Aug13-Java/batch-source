package com.revature.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.RequestDao;
import com.revature.models.Employee;

public class RequestHelper {
	private RequestHelper() {}
	private static final String JSON_EMP_BASE = "{\"employees\":";
	private static Logger log = Logger.getRootLogger();
//static logic dump, take the thought out of servlets
	public static String verifyLogin(HttpServletRequest request,String user, String pass) {
		EmployeeDao ed = new EmployeeDao();
		if (ed.matchPassword(user, pass)) {
			int id = ed.getEmployeeByUsername(user).getEmpID();
		HttpSession session = request.getSession();
		session.setAttribute("username", user);
		session.setAttribute("id", id);
		return RequestHelper.employeeManager(user);
		}
		else
			return "login";
	}
	
	public static void cleanLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
	public static String employeeManager(String user) {
		EmployeeDao ed = new EmployeeDao();
		Employee e = ed.getEmployeeByUsername(user);
		return e.isManager()? "manager": "employee";
	}
	
	public static String director(int id, String table) {
		String employees;
		RequestDao rd = new RequestDao(); 
		ObjectMapper om = new ObjectMapper();
		try {
			switch(table) {
			case"Pending":
				log.info("Take pending table, id: " + id);
				employees = om.writeValueAsString(rd.getPendingRequestsByEmpID(id));
				return JSON_EMP_BASE + employees + "}";
			case"Reviewed":
				employees = om.writeValueAsString(rd.getRespondedRequestsByEmpID(id));
				log.info("Take reviewed table, id: " + id);
				return JSON_EMP_BASE + employees + "}";
			case"ManPending":
				employees = om.writeValueAsString(rd.getAllPendingRequests());
				log.info("Take manager's pending table");
				return JSON_EMP_BASE + employees + "}";
			case"ManReviewed":
				employees = om.writeValueAsString(rd.getAllRespondedRequests());
				log.info("Take manager's reviewed table");
				return JSON_EMP_BASE + employees + "}";
			default:
				return JSON_EMP_BASE + "[]";
			}
		}
		catch (IOException e) {
			return JSON_EMP_BASE + "[]";
		}
	}
	
	public static void parseEdit(HttpServletRequest request, HttpServletResponse response) {
		String user = (String) request.getSession().getAttribute("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String phoneStr = request.getParameter("phone");
		String secondStr = request.getParameter("secondary");
		String checked = request.getParameter("manager");
		
		String man = request.getParameter("manUsername");
		String manPass = request.getParameter("manPass");
		EmployeeDao ed = new EmployeeDao();
		if (user == null) {
			log.error("somehow didn't manage to keep session???");
			try {
				response.sendRedirect("login");
			}catch (IOException e) {}
		}
		else {
			if (pass != "") {
				ed.updatePass(user, pass);
			}
			if (email != "") {
				ed.updateEmail(user, email);
			}
			if (phoneStr != "") {
				ed.updatePhone(user, Long.parseLong(phoneStr));
			}
			if (secondStr != "") {
				ed.updateSecond(user, Long.parseLong(secondStr));
			}
			if (checked != null && ed.verifyManager(man, manPass)) {
				ed.updateIsManager(user, true);
			}
			log.info("UPDATED" + user + "#" + pass + "#" + email + "#" + phoneStr + "#" + secondStr + "#" + checked);
		}
	}
}