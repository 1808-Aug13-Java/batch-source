/*
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */ 


package org.apache.tomcat.context;

import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

/** Log all Context events
 */
public class LogEvents extends BaseInterceptor {
    
    public LogEvents() {
    }

    // -------------------- Request notifications --------------------
    public int requestMap(Request request ) {
	request.getContext().log( "Request map " + request);
	return 0;
    }

    public int contextMap( Request request ) {
	return 0;
    }

    public int preService(Request request, Response response) {
	request.getContext().log( "Pre service " + request);
	return 0;
    }

    public int beforeBody( Request request, Response response ) {
	request.getContext().log( "Before body " + request);
	return 0;
    }

    public int beforeCommit( Request request, Response response) {
	request.getContext().log( "Before commit " + request);
	return 0;
    }


    public int afterBody( Request request, Response response) {
	request.getContext().log( "After Body " + request);
	return 0;
    }

    public int postService(Request request, Response response) {
	request.getContext().log( "Post service " + request);
	return 0;
    }

    // -------------------- Context notifications --------------------
    public void contextInit(Context ctx) throws TomcatException {
	ctx.log( "Context Init ");
    }

    public void contextShutdown(Context ctx) throws TomcatException {
	ctx.log( "Context Shutdown ");
    }

    /** Notify when a new servlet is added
     */
    public void addServlet( Context ctx, ServletWrapper sw) throws TomcatException {
	ctx.log( "Add servlet " + sw);
    }
    
    /** Notify when a servlet is removed from context
     */
    public void removeServlet( Context ctx, ServletWrapper sw) throws TomcatException {
	ctx.log( "Remove servlet " + sw);
    }

    public void addMapping( Context ctx, String path, ServletWrapper servlet) throws TomcatException {
	ctx.log( "Add mapping " + path + "->" + servlet);
    }


    public void removeMapping( Context ctx, String path ) throws TomcatException {
	ctx.log( "Remove mapping ");
    }

    /** 
     */
    public void addSecurityConstraint( Context ctx, String path[], String methods[],
				       String transport, String roles[] )
	throws TomcatException
    {
	StringBuffer sb=new StringBuffer();
	sb.append("Add security constraint ");
	if( methods!=null ) {
	    sb.append("Methods: ");
	    for( int i=0; i< methods.length; i++ ) {
		sb.append(" " + methods[i]);
	    }
	}
	if( path!=null) {
	    sb.append(" Paths: ");
	    for( int i=0; i< path.length; i++ ) {
		sb.append(" " + path[i]);
	    }
	}
	if( roles!=null) {
	    sb.append(" Roles: ");
	    for( int i=0; i< roles.length; i++ ) {
		sb.append(" " + roles[i]);
	    }
	}
	sb.append(" Transport " + transport );
	ctx.log(sb.toString());
    }

    /** Called when the ContextManger is started
     */
    public void engineInit(ContextManager cm) throws TomcatException {
	cm.log( "Engine init");
    }

    /** Called before the ContextManager is stoped.
     *  You need to stop any threads and remove any resources.
     */
    public void engineShutdown(ContextManager cm) throws TomcatException {
	cm.log( "Engine shutdown");
    }


    /** Called when a context is added to a CM
     */
    public void addContext( ContextManager cm, Context ctx ) throws TomcatException {
	ctx.log( "Add context");
    }

    /** Called when a context is removed from a CM
     */
    public void removeContext( ContextManager cm, Context ctx ) throws TomcatException {
	ctx.log( "Remove context");
    }

    /** Servlet Init  notification
     */
    public void preServletInit( Context ctx, ServletWrapper sw ) throws TomcatException {
	ctx.log( "Pre servlet init " + sw);
    }

    
    public void postServletInit( Context ctx, ServletWrapper sw ) throws TomcatException {
	ctx.log( "Post servlet init " + sw);
    }

    /** Servlet Destroy  notification
     */
    public void preServletDestroy( Context ctx, ServletWrapper sw ) throws TomcatException {
	ctx.log( "Pre servlet destroy " + sw);
    }

    
    public void postServletDestroy( Context ctx, ServletWrapper sw ) throws TomcatException {
	ctx.log( "Post servlet destroy " + sw);
    }

}
