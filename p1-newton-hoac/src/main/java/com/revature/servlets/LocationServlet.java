package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.LocationDao;
import com.revature.dao.LocationDaoImpl;
import com.revature.models.Location;

/**
 * Servlet implementation class LocationServlet
 */
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		LocationDao ldi = new LocationDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Location l = ldi.getLocationById(id);
			
			if (l.getlocId() == 0) {
				pw.print("Invalid Location id");
			} else {
				String locationString = om.writeValueAsString(l);
				pw.write(locationString);
			}
		} else {
			List<Location> locations = ldi.getLocations();
			
			String locationString = om.writeValueAsString(locations);
			
			locationString = "{\"locations\":"+locationString+"}";
			
			pw.println(locationString);
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
