package com.revature.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
/*
   Create a new application which includes servlets and is packaged as a war
   Create a servlet which performing a get request to will take you to a simple login page with an input field for username and password
   Define doPost method which processes the post request sent when the login form is submitted
   Check username and password (these can be hardcoded values) and have a user be brought to a page indicating that the username and/or password was incorrect if they are not correct, otherwise they should be brought to a successful login page
   If brought to the incorrect page, include navigability to return to the login page and try again

*/
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("do get request handler method invoked successfully");
    PrintWriter pw = response.getWriter();
    //    pw.write("<p> The calculator page is </p> <a href=\"Calculator.html\">here</a>");
    RequestDispatcher rd = request.getRequestDispatcher("login.html");
    rd.forward(request, response);
  }
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String usr = request.getParameter("username");
    String pwd = request.getParameter("password");
    if(usr.equals("jbki") && pwd.equals("123")) {
      request.setAttribute("username", "<p>" + usr + "</p>");
      request.setAttribute("password", "<p>" + pwd + "</p>");
      RequestDispatcher rd = request.getRequestDispatcher("success");
      rd.forward(request, response);
    }
    else {
      RequestDispatcher rd = request.getRequestDispatcher("fail");
      rd.forward(request, response);
    }
    
  }
}
