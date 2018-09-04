package com.revature.servlets;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class SuccessServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    PrintWriter pw = response.getWriter(); 
    String username = (String) request.getAttribute("username");
    String pwd = (String) request.getAttribute("password");
    pw.write(username);
    pw.write("<p>Your username above</p>");
    pw.write("<p>also password:</p>");
    pw.write(pwd);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doGet(request, response);
  }
}
