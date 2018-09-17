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


package org.apache.tomcat.facade;

import org.apache.tomcat.util.*;
import org.apache.tomcat.core.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *   Control class for facades - this is the only "gate" between servlets
 *   and tomcat.
 *
 *   This is an important security component, shouldn't be used for
 *   anything else. Please keep all the code short and clean - and review
 *   everything very often.
 *  
 */
public final class SimpleFacadeManager implements FacadeManager {
    Context ctx;

    private SimpleFacadeManager() {
    }

    // XXX XXX XXX The key to open the gate - this is the first attempt,
    // we need a safer mechanism ! 
    // 
    /** The only way to construct a FacadeManager is if you have a valid Context.
     *  XXX make sure Context can't be constructed without right permission.
     *  ( all internal tomcat objects have to be revisited ).
     *
     * The FacadeManager will work only for the Context used at construction time.
     */
    public SimpleFacadeManager(Context ctx)  {
	//	checkAccess();
	this.ctx=ctx;
    }

    public ServletContext createServletContextFacade(Context ctx) {
	//if( ctx != this.ctx ) return null; // throw
	return new ServletContextFacade(ctx.getContextManager() , ctx);
    }

    public HttpServletRequest createHttpServletRequestFacade(Request req) {
	Context reqCtx=req.getContext();
	//	if( reqCtx != ctx && reqCtx != null ) return null; // throw
	return new HttpServletRequestFacade(req);
    }

    public HttpServletResponse createHttpServletResponseFacade(Response res) {
	Context resCtx=res.getRequest().getContext();
	//if( resCtx != ctx && resCtx != null ) return null; // throw
	return new HttpServletResponseFacade(res);
    }

    public void recycle( Request rreq ) {
	//if( rreq.getContext() != ctx ) return; // throw
	
	HttpServletRequest req=rreq.getFacade();
	if( ! (req instanceof HttpServletRequestFacade))
	    return;
	
	((HttpServletRequestFacade)req).recycle();

	// recycle response
	Response rres=rreq.getResponse();
	if( rres== null )
	    return;
	
	HttpServletResponse res=rres.getFacade();
	if( res!=null) ((HttpServletResponseFacade)res).recycle();
	
	// recycle output stream
	// XXX XXX implement it
    }

    public Request getRealRequest( HttpServletRequest req ) {
	Request rreq=((HttpServletRequestFacade)req).getRealRequest();
	if( rreq==null ) {
	    System.out.println("XXX XXX null req " + ctx + " " + req );
	}
	if( rreq.getContext() == null ) {
	    // we are in "error" case
	    // A user can't create HttpServletRequest objects ( I hope )
	    return rreq;
	}
	// 	if( rreq.getContext() != ctx ) {
	// 	    System.out.println("XXX " + ctx.getPath() + " " + rreq.getContext() + " " + rreq);
	// 	    ctx.log( "Attempt to get the real request from wrong context");
	// 	    /*DEBUG*/ try {throw new Exception(); } catch(Exception ex) {ex.printStackTrace();}
	// 	    return null;
	// 	}
	return rreq;
    }

    public Context getRealContext( ServletContext sctx ) {
	Context realSctx=((ServletContextFacade)sctx).getRealContext();
	//	if( realSctx != ctx ) return null;
	return realSctx;
    }


    public ServletConfig createServletConfig(ServletWrapper sw) {
	//	if( sw.getContext() != ctx ) return null;
	return new ServletConfigImpl(sw);
    }


}
    
