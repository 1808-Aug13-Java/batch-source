package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ProfileDao;
import com.revature.dao.ProfileDaoImpl;
import com.revature.models.Profile;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		ProfileDao pdi = new ProfileDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Profile m = pdi.getProfileById(id);
			
			if (m.getId() == 0) {
				pw.print("Invalid Profile id");
			} else {
				String profileString = om.writeValueAsString(m);
				pw.write(profileString);
			}
		} else {
			List<Profile> profiles = pdi.getProfiles();
			
			String profileString = om.writeValueAsString(profiles);
			
			profileString = "{\"profiles\":"+profileString+"}";
			
			pw.println(profileString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
