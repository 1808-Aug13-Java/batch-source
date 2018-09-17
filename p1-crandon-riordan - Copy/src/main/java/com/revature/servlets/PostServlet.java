package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.revature.dao.PostDao;
import com.revature.dao.PostDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Post;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostDao pd = new PostDaoImpl();
		UserDao ud = new UserDaoImpl();
		List<Post> posts = pd.getPosts();
		
		System.out.println(posts);
		ObjectMapper om = new ObjectMapper();
//		om.registerModule(new Hibernate4Module());
		PrintWriter pw = response.getWriter();
		String postString = om.writeValueAsString(posts);
		System.out.println("Posts string: " + postString);
		pw.write("{\"posts\": "+ postString +"}");
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
