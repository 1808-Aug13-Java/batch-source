package com.revature.servlets.helpers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.LogUtil;

public class ResourceRequestHelper {
	
	
	/**
	 * This string represents the expected parameter of a resource request
	 */
	public static final String RESOURCE_REQUEST = "resourceRequest";
	
	
	public static boolean isResourceRequest(HttpServletRequest request) {
		return request.getParameter(RESOURCE_REQUEST) != null;
	}
	
	
	public static void routeResource(HttpServletRequest request, 
			HttpServletResponse response) throws IOException 
	{
		// If the request doesn't have a session, throw a 403 as there shouldn't 
		// be any access to the resources. 
		if (request.getSession(false) == null) {
			LogUtil.logDebug("Attempted Forbidden Access");
			response.sendError(403);
			return;
		}
		
		// Get the string that represents the resource the user is requesting
		String resourceReq = request.getParameter(RESOURCE_REQUEST) ;
		LogUtil.logInfo("Requested Resource: " + resourceReq);
	} // end of routeResource
	
} // end of class ResourceRequestHelper
