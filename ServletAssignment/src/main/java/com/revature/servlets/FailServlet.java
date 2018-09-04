package com.revature.servlets;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class FailServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    PrintWriter pw = response.getWriter(); 
    pw.write("<p>login failed</p>");
    pw.write("<a href=\"login\">try again</a>");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    doGet(request, response);
  }
}
