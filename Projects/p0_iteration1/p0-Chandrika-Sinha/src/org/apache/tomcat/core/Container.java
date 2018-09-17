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


package org.apache.tomcat.core;

import org.apache.tomcat.context.*;
import org.apache.tomcat.util.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A group of resources, with some common properties.
 * Container is similar with Apache "dir_conf" structue.
 *
 * A container will be selected by best-matching using the
 * alghoritms described in the servlet API.

 * The matching container will determine the handler and the
 * security properties associated with the request.
 * It will also have a chance to add a number of per/container
 * interceptors.
 *
 * In Servlet terminology there are many types of containers:
 * virtual host, context, prefix map, extension map, security
 * prefix and extension maps.
 * 
 * It is possible to add/remove containers at runtime (usefull
 * for Invoker or Jsp servlet - if they want to avoid double
 * servlet call overhead ). To make this possible for Jsps we
 * also need to factor out the dependency check and reloading.
 */
public class Container implements Cloneable {
    /* It is not yet finalized - it is possible to use more
     * "rules" for matching ( if future APIs will define that ).
     * You can use notes or attributes to extend the model -
     * the attributes that are defined and have get/set methods
     * are the one defined in the API and with wide use.
     */

    // The "controler"
    private ContextManager contextM;

    // The webapp including this container, if any
    Context context;

    // The type of the mapping
    public static final int UNKNOWN_MAP=0;
    public static final int PATH_MAP=1;
    public static final int PREFIX_MAP=2;
    public static final int EXTENSION_MAP=3;
    public static final int DEFAULT_MAP=4;
    int mapType=0;

    
    // Common map parameters ( path prefix, ext, etc)
    String transport;
    String path;
    String proto;
    String vhosts[];
    
    // Container attributes - it's better to use
    // notes, as the access time is much smaller
    private Hashtable attributes = new Hashtable();

    /** Per/container interceptors.
	XXX Not implemented, it's easy to wire it in ( 2-3 h).
     */
    Vector contextInterceptors=new Vector();
    Vector requestInterceptors=new Vector();
    // interceptor cache - avoid Vector enumeration
    ContextInterceptor cInterceptors[];
    RequestInterceptor rInterceptors[];

    /** The handler associated with this container.
     */
    ServletWrapper handler;
    
    /** Security constraints associated with this Container
     */
    String roles[]=null;

    String methods[]=null;
    
    public Container() {
    }

    /** Get the context manager
     */
    public ContextManager getContextManager() {
	if( contextM==null ) {
	    /* assert */
	    throw new RuntimeException( "Assert: container.contextM==null" );
	}
	return contextM;
    }

    public void setContextManager(ContextManager cm) {
	contextM=cm;
    }

    /** Set the context, if this container is part of a web application.
     *  Right now all container in use have a context.
     */
    public void setContext( Context ctx ) {
	this.context=ctx;
    }

    /** The parent web application, if any. 
     */
    public Context getContext() {
	return context;
    }
    
    // -------------------- Mapping LHS --------------------
       
    
    /** Return the type of the mapping ( extension, prefix, default, etc)
     */
    public int getMapType() {
	if( mapType!=0) return mapType;
	// What happens with "" or null ?
	// XXX Which one is default servlet ? API doesn't say,
	// but people expect it to work.
	if( path==null ||
	    path.equals("" ) ||
	    path.equals( "/")) {
	    mapType=DEFAULT_MAP;
	} else if (path.startsWith("/") &&
	    path.endsWith("/*")) {
	    mapType=PREFIX_MAP;
	} else if (path.startsWith("*.")) {
	    mapType=EXTENSION_MAP;
	} else {
	    mapType=PATH_MAP;
	}
	return mapType;
    }

    /** The mapping string that creates this Container.
     *  Not that this is an un-parsed string, like a regexp.
     */
    public void setPath( String path ) {
	// XXX use a better name - setMapping for example
	if( path==null)
	    this.path=""; // default mapping
	else
	    this.path=path.trim();
    }

    /** Return the path
     */
    public String getPath() {
	return path;
    }

    /** Set the protocol - if it's set it will be used
     *  in mapping
     */
    public void setProtocol( String protocol ) {
	this.proto=protocol;
    }

    /** Protocol matching. With Servlet 2.2 the protocol
     * can be used only with security mappings, not with
     * handler ( servlet ) maps
    */
    public String getProtocol() {
	return proto;
    }

    /** The transport - another component of the matching.
     *  Defined only for security mappings.
     */
    public void setTransport(String transport ) {
	this.transport=transport;
    }

    /** The transport - another component of the matching.
     *  Defined only for security mappings.
     */
    public String getTransport() {
	return transport;
    }

    /** Any alias that can match a particular vhost
     */
    public String[] getVhosts() {
	return vhosts;
    }

    /** Any alias that can match a particular vhost
     */
    public void setVhosts(String vhosts[]) {
	this.vhosts=vhosts;
    }
    
    /** If not null, this container can only be accessed by users
     *  in roles.
     */
    public String []getMethods() {
	return methods;
    }

    /** If not null, this container can only be accessed by users
	in roles.
    */
    public void setMethods( String m[] ) {
	this.methods=m;
    }

    // -------------------- Mapping RHS --------------------
    
    /** The handler for this container
     */
    public ServletWrapper getHandler() {
	return handler;
    }

    /** The handler ( servlet ) for this container
     */
    public void setHandler(ServletWrapper h) {
	handler=h;
    }

    /** If not null, this container can only be accessed by users
     *  in roles.
     */
    public String []getRoles() {
	return roles;
    }

    /** If not null, this container can only be accessed by users
	in roles.
    */
    public void setRoles( String roles[] ) {
	this.roles=roles;
    }

    // -------------------- Per-container interceptors
    
    /** Add a per/container context interceptor. It will be notified
     *  of all context events happening inside this container.
     *   XXX incomplete implementation
     */
    public void addContextInterceptor( ContextInterceptor ci) {
	contextInterceptors.addElement( ci );
    }

    /** Return the context interceptors as an array.
     *	For performance reasons we use an array instead of
     *  returning the vector - the interceptors will not change at
     *	runtime and array access is faster and easier than vector
     *	access
     */
    public ContextInterceptor[] getContextInterceptors() {
	if( cInterceptors == null ||
	    cInterceptors.length != contextInterceptors.size()) {
	    cInterceptors=new ContextInterceptor[contextInterceptors.size()];
	    for( int i=0; i<cInterceptors.length; i++ ) {
		cInterceptors[i]=(ContextInterceptor)contextInterceptors.
		    elementAt(i);
	    }
	}
	return cInterceptors;
    }

    /** Add a per/container request interceptor. It will be called back for
     *  all operations for requests that are mapped to this container.
     *  Note that all global interceptors will be called first.
     *   XXX incomplete implementation.
     */
    public void addRequestInterceptor( RequestInterceptor ci) {
	requestInterceptors.addElement( ci );
    }

    /** Return the context interceptors as an array.
	For performance reasons we use an array instead of
	returning the vector - the interceptors will not change at
	runtime and array access is faster and easier than vector
	access
    */
    public RequestInterceptor[] getRequestInterceptors() {
	if( rInterceptors == null ||
	    rInterceptors.length != requestInterceptors.size())
	{
	    rInterceptors=new RequestInterceptor[requestInterceptors.size()];
	    for( int i=0; i<rInterceptors.length; i++ ) {
		rInterceptors[i]=(RequestInterceptor)requestInterceptors.
		    elementAt(i);
	    }
	}
	return rInterceptors;
    }

    /** Per container attributes. Not used - can be removed
     *  ( it's here for analogy with the other components )
     */
    public Object getAttribute(String name) {
            return attributes.get(name);
    }

    /** Per container attributes. Not used - can be removed
     *  ( it's here for analogy with the other components )
     */
    public Enumeration getAttributeNames() {
        return attributes.keys();
    }

    /** Per container attributes. Not used - can be removed
     *  ( it's here for analogy with the other components )
     */
    public void setAttribute(String name, Object object) {
        attributes.put(name, object);
    }

    /** Per container attributes. Not used - can be removed
     *  ( it's here for analogy with the other components )
     */
    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    // -------------------- Utils --------------------
    /** Print a short string describing the mapping
     */
    public String toString() {
	StringBuffer sb=new StringBuffer();
	sb.append( "Ct (" );
	if( handler!= null) sb.append( handler.toString() );
	if( roles!=null) {
	    	sb.append(" Roles: ");
		for( int i=0; i< roles.length; i++ )
		    sb.append(" ").append( roles[i]);
	}
	sb.append( " )");
	return sb.toString();
    }

    public Container getClone() {
	try {
	    return (Container)this.clone();
	} catch( CloneNotSupportedException ex ) {
	    return this;
	}
    }

    // -------------------- Per-Container "notes"
    Object notes[]=new Object[ContextManager.MAX_NOTES];

    /** See ContextManager comments.
     */
    public void setNote( int pos, Object value ) {
	notes[pos]=value;
    }

    public Object getNote( int pos ) {
	return notes[pos];
    }
}
