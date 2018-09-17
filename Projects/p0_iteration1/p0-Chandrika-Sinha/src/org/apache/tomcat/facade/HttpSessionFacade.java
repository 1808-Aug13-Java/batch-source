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

import org.apache.tomcat.core.*;
import org.apache.tomcat.session.*;
import org.apache.tomcat.util.StringManager;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Facade for http session. Used to prevent servlets to access
 * internal tomcat objects.
 *
 * This is a "special" facade - since session management is
 * (more or less) orthogonal to request processing, it is
 * indpendent of tomcat architecture. It will provide a
 * HttpSession implementation ( but it's not guaranteed
 * in any way it is "safe" ), and HttpSessionFacade will
 * act as a "guard" to make sure only servlet API public
 * methods are exposed.
 *
 * Another thing to note is that this object will be recycled
 * and will allways be set in a request. The "real" session
 * object will determine if the request is part of a session.
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author Jason Hunter [jch@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @author costin@eng.sun.com
 */
final class HttpSessionFacade implements HttpSession {
    HttpSession realSession;
    
    HttpSessionFacade() {
    }

    /** Package-level method - accessible only by core
     */
    void setRealSession(HttpSession s) {
 	realSession=s;
     }

    /** Package-level method - accessible only by core
     */
    void recycle() {
	realSession=null;
    }

    // -------------------- public facade --------------------

    public String getId() {
	return realSession.getId();
    }

    public long getCreationTime() {
	return realSession.getCreationTime();
    }
    
    /**
     * We return our own "disabled" SessionContext -
     * regardless of what the real session returns.
     *
     * @deprecated
     */
    public HttpSessionContext getSessionContext() {
	return new SessionContextImpl();
    }
    
    public long getLastAccessedTime() {
	return realSession.getLastAccessedTime();
    }

    public void invalidate() {
	realSession.invalidate();
    }

    public boolean isNew() {
	return realSession.isNew();
    }
    
    /**
     * @deprecated
     */
    public void putValue(String name, Object value) {
	realSession.putValue(name, value);
    }

    public void setAttribute(String name, Object value) {
	realSession.setAttribute( name, value );
    }

    /**
     * @deprecated
     */
    public Object getValue(String name) {
	return realSession.getValue(name);
    }

    public Object getAttribute(String name) {
	return realSession.getAttribute(name);
    }
    
    /**
     * @deprecated
     */
    public String[] getValueNames() {
	return realSession.getValueNames();
    }

    public Enumeration getAttributeNames() {
	return realSession.getAttributeNames();
    }

    /**
     * @deprecated
     */
    public void removeValue(String name) {
	realSession.removeAttribute(name);
    }

    public void removeAttribute(String name) {
	realSession.removeAttribute(name);
    }

    public void setMaxInactiveInterval(int interval) {
	realSession.setMaxInactiveInterval( interval );
    }

    public int getMaxInactiveInterval() {
	return realSession.getMaxInactiveInterval();
    }
}
