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
import org.apache.tomcat.facade.*;
import org.apache.tomcat.util.*;
import java.security.*;
import java.lang.reflect.*;
import org.apache.tomcat.logging.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;


/* Right now we have all the properties defined in web.xml.
   The interceptors will  go into Container ( every request will
   be associated with the final container, which will point back to the
   context). That will allow us to use a simpler and more "targeted"
   object model.

   The only "hard" part is moving getResource() and getRealPath() in
   a different class, using a filesystem independent abstraction. 
   
*/
   

/**
 * Context represent a Web Application as specified by Servlet Specs.
 * The implementation is a repository for all the properties
 * defined in web.xml and tomcat specific properties.
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 * @author Jason Hunter [jch@eng.sun.com]
 * @author Harish Prabandham
 * @author costin@dnt.ro
 * @author Gal Shachor shachor@il.ibm.com
 * @author Arieh Markel [arieh.markel@sun.com]
 */
public class Context {
    private static StringManager sm =StringManager.getManager("org.apache.tomcat.core");

    // -------------------- internal properties
    // context "id"
    private String path = "";
    private String docBase;

    // Absolute path to docBase if file-system based
    private String absPath; 
    // internal state / related objects
    private ContextManager contextM;
    private ServletContext contextFacade;
    private boolean crossContext = true;
    private ServletLoader servletL;
    boolean reloadable=true; // XXX change default to false after testing

    private Hashtable attributes = new Hashtable();

    private File workDir;

    // Security Permissions for webapps and jsp for this context
    Object perms = null;
    Object protectionDomain;
 
    //    private RequestSecurityProvider rsProvider;

    // Servlets loaded by this context( String->ServletWrapper )
    private Hashtable servlets = new Hashtable();

    // -------------------- from web.xml
    private Hashtable initializationParameters = new Hashtable();
    // all welcome files that are added are treated as "system default"
    private boolean expectUserWelcomeFiles=false;
    private Vector welcomeFiles = new Vector();
    private Hashtable errorPages = new Hashtable();
    private String description = null;
    private boolean isDistributable = false;
    private MimeMap mimeTypes = new MimeMap();
    private int sessionTimeOut = -1;

    // taglibs
    Hashtable tagLibs=new Hashtable();
    // Env entries
    Hashtable envEntryTypes=new Hashtable();
    Hashtable envEntryValues=new Hashtable();

    // Maps specified in web.xml ( String url -> ServletWrapper  )
    private Hashtable mappings = new Hashtable();
    Hashtable constraints=new Hashtable();

    Hashtable containers=new Hashtable();

    Container defaultContainer = null; // generalization, will replace most of the
    // functionality. By using a default container we avoid a lot of checkings
    // and speed up searching, and we can get rid of special properties.
    private ServletWrapper defaultServlet = null;

    // Authentication properties
    String authMethod;
    String realmName;
    String formLoginPage;
    String formErrorPage;

    int debug=0;
    // are servlets allowed to access internal objects? 
    boolean trusted=false;
    String vhost=null;
    Vector vhostAliases=new Vector();
    FacadeManager facadeM;

    public Context() {
	defaultContainer=new Container();
	defaultContainer.setContext( this );
	defaultContainer.setPath( null ); // default container
    }

    /** Every context is associated with a facade
     */
    public ServletContext getFacade() {
        if(contextFacade==null )
	    contextFacade = getFacadeManager().createServletContextFacade( this );
	return contextFacade;
    }


    // -------------------- Settable context properties --------------------
    // -------------------- Required properties
    public ContextManager getContextManager() {
	return contextM;
    }

    public void setContextManager(ContextManager cm) {
	contextM=cm;
    }

    public boolean getCrossContext() {
        return (this.crossContext);
    }

    public void setCrossContext(boolean crossContext) {
        this.crossContext = crossContext;
    }

    public FacadeManager getFacadeManager() {
	if( facadeM==null ) {
	    /* XXX make it configurable
	     */
	    facadeM=new SimpleFacadeManager( this );
	}
	return facadeM;
    }

    /** Base URL for this context
     */
    public String getPath() {
	return path;
    }

    /** Base URL for this context
     */
    public void setPath(String path) {
	// config believes that the root path is called "/",
	//
	if( "/".equals(path) )
	    path="";
	this.path = path;
    }

    /** DocBase points to the web application files.
     *
     *  There is no restriction on the syntax and content of DocBase,
     *  it's up to the various modules to interpret this and use it.
     *  For example, to serve from a war file you can use war: protocol,
     *  and set up War interceptors.
     *
     *  "Basic" tomcat treats it as a file ( either absolute or relative to
     *  the CM home ).
     *
     *  If docBase is relative assume it is relative  to the context manager home.
     */
    public void setDocBase( String docB ) {
	this.docBase=docB;
    }


    public String getDocBase() {
	return docBase;
    }

    /** Return the absolute path for the docBase, if we are file-system
     *  based, null otherwise.
    */
    public String getAbsolutePath() {
	if( absPath!=null) return absPath;

	if (FileUtil.isAbsolute( docBase ) )
	    absPath=docBase;
	else
	    absPath = contextM.getHome() + File.separator + docBase;
	try {
	    absPath = new File(absPath).getCanonicalPath();
	} catch (IOException npe) {
	}
	return absPath;
    }

    // -------------------- Tomcat specific properties
    // workaround for XmlMapper unable to set anything but strings
    public void setReloadable( String s ) {
	reloadable=new Boolean( s ).booleanValue();
    }

    public void setReloadable( boolean b ) {
	reloadable=b;
    }

    /** Should we reload servlets ?
     */
    public boolean getReloadable() {
	return reloadable;
    }

    // -------------------- Web.xml properties --------------------

    public Enumeration getWelcomeFiles() {
	return welcomeFiles.elements();
    }

    /** @deprecated It is used as a hack to allow web.xml override default
	 welcome files.
	 Tomcat will first load the "default" web.xml and then this file.
    */
    public void removeWelcomeFiles() {
	if( ! this.welcomeFiles.isEmpty() )
	    this.welcomeFiles.removeAllElements();
    }

    /** If any new welcome file is added, remove the old list of
     *  welcome files and start a new one. This is used as a hack to
     *  allow a default web.xml file to specifiy welcome files.
     *  We should use a better mechanism! 
     */
    public void expectUserWelcomeFiles() {
	expectUserWelcomeFiles = true;
    }
    

    public void addWelcomeFile( String s) {
	// user specified at least one user welcome file, remove the system
	// files
	if (s == null ) return;
	s=s.trim();
	if(s.length() == 0)
	    return;
	if(  expectUserWelcomeFiles  ) {
	    removeWelcomeFiles();
	    expectUserWelcomeFiles=false;
	} 
	welcomeFiles.addElement( s );
    }

    /** Add a taglib declaration for this context
     */
    public void addTaglib( String uri, String location ) {
	//	System.out.println("Add taglib " + uri + "  " + location );
	tagLibs.put( uri, location );
    }

    public String getTaglibLocation( String uri ) {
	return (String)tagLibs.get(uri );
    }

    public Enumeration getTaglibs() {
	return tagLibs.keys();
    }

    /** Add Env-entry to this context
     */
    public void addEnvEntry( String name,String type, String value, String description ) {
	System.out.println("Add env-entry " + name + "  " + type + " " + value + " " +description );
	if( name==null || type==null) throw new IllegalArgumentException();
	envEntryTypes.put( name, type );
	if( value!=null)
	    envEntryValues.put( name, value );
    }

    public String getEnvEntryType(String name) {
	return (String)envEntryTypes.get(name);
    }

    public String getEnvEntryValue(String name) {
	return (String)envEntryValues.get(name);
    }

    public Enumeration getEnvEntries() {
	return envEntryTypes.keys();
    }


    public String getInitParameter(String name) {
        return (String)initializationParameters.get(name);
    }

    /** @deprecated use addInitParameter
     */
    public void setInitParameter( String name, String value ) {
	initializationParameters.put(name, value );
    }

    public void addInitParameter( String name, String value ) {
	initializationParameters.put(name, value );
    }

    public Enumeration getInitParameterNames() {
        return initializationParameters.keys();
    }

    public Object getAttribute(String name) {
        if (name.startsWith("org.apache.tomcat")) {
	    // XXX XXX XXX XXX Security - servlets may get too much access !!!
	    // right now we don't check because we need JspServlet to
	    // be able to access classloader and classpath
	    
	    if (name.equals("org.apache.tomcat.jsp_classpath")) {
		String cp= getServletLoader().getClassPath();
		return cp;
	    }
	    if( name.equals( "org.apache.tomcat.protection_domain") ) {
		return getProtectionDomain();
	    }
	    if(name.equals("org.apache.tomcat.classloader")) {
		return this.getServletLoader().getClassLoader();
	    }
	    if( name.equals(FacadeManager.FACADE_ATTRIBUTE)) {
		if( ! allowAttribute(name) ) return null;
		return this.getFacadeManager();
	    }
	    return null; // org.apache.tomcat namespace is reserved in tomcat
	} else {
            Object o = attributes.get(name);
            return attributes.get(name);
        }
    }

    public Enumeration getAttributeNames() {
        return attributes.keys();
    }

    public void setAttribute(String name, Object object) {
        attributes.put(name, object);
    }

    public void removeAttribute(String name) {
        attributes.remove(name);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon( String icon ) {

    }

    public boolean isDistributable() {
        return this.isDistributable;
    }

    public void setDistributable(boolean isDistributable) {
        this.isDistributable = isDistributable;
    }

    public void setDistributable(String s) {
	// XXX
    }

    public int getSessionTimeOut() {
        return this.sessionTimeOut;
    }

    public void setSessionTimeOut(int sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public FileNameMap getMimeMap() {
        return mimeTypes;
    }

    public void addContentType( String ext, String type) {
	mimeTypes.addContentType( ext, type );
    }

    public String getErrorPage(int errorCode) {
        return getErrorPage(String.valueOf(errorCode));
    }

    public void addErrorPage( String errorType, String value ) {
	this.errorPages.put( errorType, value );
    }

    public String getErrorPage(String errorCode) {
        return (String)errorPages.get(errorCode);
    }


    /** Authentication method, if any specified
     */
    public String getAuthMethod() {
	return authMethod;
    }

    /** Realm to be used
     */
    public String getRealmName() {
	return realmName;
    }

    public String getFormLoginPage() {
	return formLoginPage;
    }

    public String getFormErrorPage() {
	return formErrorPage;
    }

    public void setFormLoginPage( String page ) {
	formLoginPage=page;
    }
    
    public void setFormErrorPage( String page ) {
	formErrorPage=page;
    }

    public void setLoginConfig( String authMethod, String realmName,
				String formLoginPage, String formErrorPage)
    {
	// 	System.out.println("Login config: " + authMethod + " " + realmName + " " +
	// 			   formLoginPage + " " + formErrorPage);
	this.authMethod=authMethod;
	this.realmName=realmName;
	this.formLoginPage=formLoginPage;
	this.formErrorPage=formErrorPage;
    }

    // -------------------- Mappings --------------------

    /**
     * Maps a named servlet to a particular path or extension.
     * If the named servlet is unregistered, it will be added
     * and subsequently mapped.
     *
     * Note that the order of resolution to handle a request is:
     *
     *    exact mapped servlet (eg /catalog)
     *    prefix mapped servlets (eg /foo/bar/*)
     *    extension mapped servlets (eg *jsp)
     *    default servlet
     *
     */
    public void addServletMapping(String path, String servletName)
	throws TomcatException
    {
	if( mappings.get( path )!= null) {
	    log( "Removing duplicate " + path + " -> " + mappings.get(path) );
	    mappings.remove( path );
	    Container ct=(Container)containers.get( path );
	    removeContainer( ct );
	}
        ServletWrapper sw = (ServletWrapper)servlets.get(servletName);
	if (sw == null) {
	    // Workaround for frequent "bug" in web.xmls
	    // Declare a default mapping
	    log("Mapping with unregistered servlet " + servletName );
	    sw = addServlet( servletName, servletName );
	}
	if( "/".equals(path) )
	    defaultServlet = sw;

	mappings.put( path, sw );

	Container map=new Container();
	map.setContext( this );
	map.setHandler( sw );
	map.setPath( path );
	contextM.addContainer( map );
	containers.put( path, map );
    }

    /** Will add a new security constraint:
	For all paths:
	if( match(path) && match(method) && match( transport ) )
	then require("roles")

	This is equivalent with adding a Container with the path,
	method and transport. If the container will be matched,
	the request will have to pass the security constraints.
	
    */
    public void addSecurityConstraint( String path[], String methods[],
				       String roles[], String transport)
	throws TomcatException
    {
	for( int i=0; i< path.length; i++ ) {
	    Container ct=new Container();
	    ct.setContext( this );
	    ct.setTransport( transport );
	    ct.setRoles( roles );
	    ct.setPath( path[i] );
	    ct.setMethods( methods );

	    // XXX check if exists, merge if true.
	    constraints.put( path[i], ct );
	    //contextM.addSecurityConstraint( this, path[i], ct);
	    contextM.addContainer(  ct );
	}
    }

    public Enumeration getContainers() {
	return containers.elements();
    }

    public Enumeration getContainerLocations() {
	return containers.keys();
    }

    public Container getContainer( String path ) {
	return (Container)containers.get(path);
    }

    // return the container associated with this context -
    // which is also the default container
    public Container getContainer() {
	return defaultContainer;
    }

    public void removeContainer( Container ct ) {
	containers.remove(ct.getPath());
    }

//     public ServletWrapper getDefaultServlet() {
// 	if( defaultServlet==null)
// 	    defaultServlet=getServletByName(Constants.DEFAULT_SERVLET_NAME );
// 	return defaultServlet;
//     }

    // -------------------- Servlets management --------------------

    // XXX do we need that ??
    /** Remove the servlet with a specific name
     */
    public void removeServletByName(String servletName)
	throws TomcatException
    {
	servlets.remove( servletName );
    }

    public ServletWrapper getServletByName(String servletName) {
	return (ServletWrapper)servlets.get(servletName);
    }

    /**
     * Add a servlet with the given name to the container. The
     * servlet will be loaded by the container's class loader
     * and instantiated using the given class name.
     *
     * Called to add a new servlet from web.xml
     */
    public void addServlet(ServletWrapper wrapper)
    	throws TomcatException
    {
	wrapper.setContext( this );
	String name=wrapper.getServletName();
	//	System.out.println("Adding servlet " + name  + " " + wrapper);
        // check for duplicates
        if (servlets.get(name) != null) {
	    log("Removing duplicate servlet " + name  + " " + wrapper);
            removeServletByName(name);
	    //	    getServletByName(name).destroy();
        }
	servlets.put(name, wrapper);
    }

    public ServletWrapper addServlet(String name, String classN)
	throws TomcatException
    {
	ServletWrapper sw = new ServletWrapper();
	sw.setContext(this);
	
	sw.setServletName(name);
	if ( classN.startsWith("/")) {
	    sw.setPath(classN);
	} else {
	    sw.setServletClass(classN);
	}
	addServlet( sw );
	return sw;
    }

    public Enumeration getServletNames() {
	return servlets.keys();
    }

    // -------------------- Loading and sessions --------------------
    public void setServletLoader(ServletLoader loader ) {
	this.servletL=loader;
    }

    public ServletLoader getServletLoader() {
	return servletL;
    }

    /* -------------------- Utils  -------------------- */
    public void setDebug( int level ) {
	if(level>0) log( "Set debug to " + level );
	debug=level;
    }

    public void setDebug( String level ) {
	try {
	    setDebug( Integer.parseInt(level) );
	} catch (Exception e) {
	    log("Trying to set debug to '" + level + "':", e, Logger.ERROR);
	}
    }

    public int getDebug( ) {
	return debug;
    }

    // ------------------- Logging ---------------

    LogHelper loghelper = new LogHelper("tc_log", this);
    LogHelper loghelperServlet = new LogHelper("servlet_log", null);

    /** Internal log method
     */
    public final void log(String msg) {
	loghelper.log(msg);
    }

    /** Internal log method
     */
    public void log(String msg, Throwable t) {
	loghelper.log(msg, t);
    }

    /** Internal log method
     */
    public void log(String msg, Throwable t, int level) {
	loghelper.log(msg, t, level);
    }

    /** User-level log method ( called from a servlet)
     */
    public void logServlet( String msg , Throwable t ) {
	msg = ("path=\"" + path  + "\" :" + msg);
	loghelperServlet.log(msg, t);
    }

    public String toString() {
	return "Ctx( " + (vhost==null ? "" : vhost + ":" )  +  path +  " )";
    }

    // -------------------- Facade methods --------------------

    public Context getContext(String path) {
	if (! path.startsWith("/")) {
	    return null; // according to spec, null is returned
	    // if we can't  return a servlet, so it's more probable
	    // servlets will check for null than IllegalArgument
	}
        // Return null if cross context lookups are not allowed
        if (!crossContext)
            return null;
	// absolute path
	Request lr=contextM.createRequest( path );
	if( vhost != null ) lr.setServerName( vhost );
	getContextManager().processRequest(lr);
        return lr.getContext();
    }

    /** Implements getResource()
     *  See getRealPath(), it have to be local to the current Context -
     *  and can't go to a sub-context. That means we don't need any overhead.
     */
    public URL getResource(String rpath) throws MalformedURLException {
        if (rpath == null) return null;

        if(URLUtil.hasEscape(rpath))
            return null;

        URL url = null;
	String absPath=getAbsolutePath();

	if ("".equals(rpath))
	    return new URL( "file", null, 0, absPath );

	if ( ! rpath.startsWith("/")) 
	    rpath="/" + rpath;

	String realPath=FileUtil.safePath( absPath, rpath);
	if( realPath==null ) {
	    log( "Unsafe path " + absPath + " " + rpath );
	    return null;
	}
	
	try {
        url=new URL("file", null, 0,realPath );
	    if( debug>9) log( "getResourceURL=" + url + " request=" + rpath );
        return url;
	} catch( IOException ex ) {
	    ex.printStackTrace();
	    return null;
	}
    }


    /**   According to Servlet 2.2 the real path is interpreted as
     *    relative to the current web app and _cannot_ go outside the 
     *    box. If your intention is different or want the "other" behavior 
     *    you'll have to first call getContext(path) and call getRealPath()
     *    on the result context ( if any - the server may disable that from
     *    security reasons !).
     *    XXX find out how can we find the context path in order to remove it
     *    from the path - that's the only way a user can do that unless he have
     *    prior knowledge of the mappings !
     */
    public String getRealPath( String path) {
	String base=getAbsolutePath();
	if( path==null ) path="";

	String realPath=FileUtil.safePath( base, path );
	// No need for a sub-request, that's a great simplification
	// in servlet space.

	// Important: that's different from what some people might
	// expect and how other server APIs work, but that's how it's
	// specified in 2.2. From a security point of view that's very
	// good, it keeps inter-webapp communication under control.
	
	if( debug>5) {
	    log("Get real path " + path + " " + realPath + " " + base );
	}
	return realPath;
    }

    /**  method to return the Localized version of the file whose
     *   name is passed as an argument. This corresponds to "file" type
     *   localization resource lookup mechanism.
     *
     *  The method performs a resource lookup in a manner similar to the
     *  one specified by java.util.ResourceBundle.
     *
     *  In the case of 'typed' files (files whose name is [file].[ftype])
     *  search for localized versions of the file are looked for:
     *
     *   file + "_" + language1 + "_" + country1 + "_" + variant1 + "." + ftype
     *   file + "_" + language1 + "_" + country1 + "." + ftype
     *   file + "_" + language1 + "." + ftype
     *   file + "_" + language2 + "_" + country2 + "_" + variant2 "." + ftype
     *   file + "_" + language2 + "_" + country2 "." + ftype
     *   file + "_" + language2 + "." + ftype
     *   file + "." + ftype
     *
     *  Where language1, country1, variant1 are associated with the Locale
     *  passed as an argument and language2, country2, variant are associated
     *  with the default Locale passed as argument.
     *
     *  For example, if the preferred Locale is <CODE>es_AR_POSIX</CODE> and 
     *  the default Locale passed is <CODE>fr_CA_WIN</CODE>, and the requested
     *  pathname is <CODE>/foo/bar/index.html</CODE>, then a search for
     *  the following localized versions of that file will be done, in order:
     *<UL>
     *<LI>/foo/bar/index_es_AR_POSIX.html</LI>
     *<LI>/foo/bar/index_es_AR.html</LI>
     *<LI>/foo/bar/index_es.html</LI>
     *<LI>/foo/bar/index_fr_CA_WIN.html</LI>
     *<LI>/foo/bar/index_fr.html</LI>
     *<LI>/foo/bar/index.html</LI>
     *</UL>
     *
     *  If the resource passed has no 'ftype' component, then the same
     *  rules above apply, with the exception that '.' + ftype are not
     *  concatenated.
     *
     *  @param path the pathname for the resource whose localized version
     *          we are seeking
     *  @param loc the Locale we are interested in.
     *  @param fbLoc the fallback Locale to use if unsuccessful
     *
     *  @return a String with the path of the "best localized match" for
     *          the file whose path has been passed as argument.
     */
    public String getRealPath (String path, Locale reqLocale, Locale fbLocale)
    {
	return getRealPath (path, reqLocale, fbLocale, "file");
    }

    /**  method to return the Localized version of the file whose
     *   name is passed as an argument. The localization is done based
     *   on localization subdirectories under the docBase.
     *
     *  The method performs a resource lookup in a manner similar to the
     *  one used for JavaHelp resources.
     *
     *  Search for localized versions of the file are looked for:
     *
     *   <docBase> + "/" + language1 + "_" + country1 + "_" + variant1 + file
     *   <docBase> + "/" + language1 + "_" + country1 + file
     *   <docBase> + "/" + language1 + file
     *   <docBase> + "/" + language2 + "_" + country2 + "_" + variant1 + file
     *   <docBase> + "/" + language2 + "_" + country2 + file
     *   <docBase> + "/" + language2 + file
     *   <docBase> + file
     *
     *  Where language1, country1, variant1 are associated with the Locale
     *  passed as an argument and language2, country2, variant are associated
     *  with the fallback Locale passed as argument.
     *
     *
     *  @param path the pathname for the resource whose localized version
     *          we are seeking
     *  @param loc the Locale we are interested in.
     *  @param fbLoc the fallback Locale to use if unsuccessful
     *  @param locType the type of localization required "file", "docbase"
     *
     *  @return a String with the path of the "best localized match" for
     *          the file whose path has been passed as argument.
     */
    public String getRealPath (String path, Locale reqLocale, Locale fbLocale,
			       String locType)
    {
        String base = getAbsolutePath();
        if (path == null) path = "";

        String realPath = null;

	if ("file".equals (locType))
	    realPath = FileUtil.getLocalizedFile (base, path,
						  reqLocale, fbLocale);
	else if ("docbase".equals (locType))
	    realPath = FileUtil.getDocBaseLocalizedFile (base, path,
						  reqLocale, fbLocale);

	if( debug>5) {
	    log("Get real path " + path + " " + realPath + " " + base
		 + reqLocale.toString() + " " + fbLocale.toString() );
	}

        return realPath;
    }

    // -------------------- Deprecated
    // tomcat specific properties
    private boolean isWorkDirPersistent = false;
    private String engineHeader = null;
    private URL documentBase;
    private URL servletBase = null;
    private boolean isInvokerEnabled = false;
    // for serving WARs directly
    private File warDir = null;
    private boolean isWARExpanded = false;
    private boolean isWARValidated = false;



    /**  @deprecated
     */
    public boolean isInvokerEnabled() {
        return isInvokerEnabled;
    }

    /**  @deprecated
     */
    public void setInvokerEnabled(boolean isInvokerEnabled) {
        this.isInvokerEnabled = isInvokerEnabled;
    }

    /**  @deprecated
     */
    public boolean isWorkDirPersistent() {
        return this.isWorkDirPersistent;
    }

    /**  @deprecated
     */
    public void setWorkDirPersistent( boolean b ) {
	isWorkDirPersistent=b;
    }

    /**  @deprecated
     */
    public File getWorkDir() {
	return workDir;
    }

    /**  @deprecated
     */
    public void setWorkDir(File workDir) {
	this.workDir = workDir;
    }

    /** Set work dir using a String property
     *  @deprecated
     */
    public void setWorkDirPath(String workDir) {
	this.workDir=new File(workDir);
    }

    /**  @deprecated
     */
    public String getEngineHeader() {
	return engineHeader;
    }

    /**  @deprecated
     */
    public void setEngineHeader(String s) {
        engineHeader=s;
    }

//     /**  @deprecated
//      */
//     public void setRequestSecurityProvider(RequestSecurityProvider rsProvider) {
// 	this.rsProvider = rsProvider;
//     }

//     /**  @deprecated
//      */
//     public RequestSecurityProvider getRequestSecurityProvider() {
// 	return this.rsProvider;
//     }

    /**  @deprecated
     */
    public File getWARDir() {
        return this.warDir;
    }

    /**  @deprecated
     */
    public void setWARDir( File f ) {
	warDir=f;
    }

    /**  @deprecated
     */
    public boolean isWARExpanded() {
        return this.isWARExpanded;
    }

    /**  @deprecated
     */
    public void setIsWARExpanded(boolean isWARExpanded) {
        this.isWARExpanded = isWARExpanded;
    }

    /**  @deprecated
     */
    public boolean isWARValidated() {
        return this.isWARValidated;
    }

    /**  @deprecated
     */
    public void setIsWARValidated(boolean isWARValidated) {
        this.isWARValidated = isWARValidated;
    }

    /**  @deprecated
     */
    public void addContextInterceptor( ContextInterceptor ci) {
	getContainer().addContextInterceptor(ci);
    }

    /** @deprecated
     */
     public ContextInterceptor[] getContextInterceptors() {
	 return getContainer().getContextInterceptors();
     }

    /**  @deprecated
     */
    public void addRequestInterceptor( RequestInterceptor ci) {
	getContainer().addRequestInterceptor(ci);
    }

    /** @deprecated
     */
    public RequestInterceptor[] getRequestInterceptors() {
	return getContainer().getRequestInterceptors();
    }
 
     /**
      * Get the SecurityManager Permissions for this Context.
      */
    public Object getPermissions() {
	return perms;
    }

    public void setPermissions( Object o ) {
	perms=o;
    }
    
    public Object getProtectionDomain() {
	return protectionDomain;
    }

    public void setProtectionDomain(Object o) {
	protectionDomain=o;
    }


    /** @deprecated - use getDocBase and URLUtil if you need it as URL
     *  NOT USED INSIDE TOMCAT - ONLY IN OLD J2EE CONNECTORS !
     */
    public URL getDocumentBase() {
	if( documentBase == null ) {
	    if( docBase == null)
		return null;
	    try {
		String absPath=docBase;

		// detect absolute path ( use the same logic in all tomcat )
		if (FileUtil.isAbsolute( docBase ) )
		    absPath=docBase;
	        else
		    absPath = contextM.getHome() + File.separator + docBase;

		try {
		    absPath = new File(absPath).getCanonicalPath();
		} catch (IOException npe) {
		}

		documentBase = new URL("file", "", absPath);

	    } catch( MalformedURLException ex ) {
		ex.printStackTrace();
	    }
	}
        return documentBase;
    }

    /** @deprecated - use setDocBase
     */
    public void setDocumentBase(URL s) {
	// Used only by startup, will be removed
        this.documentBase=s;
    }

    // -------------------- Virtual host support --------------------

    /** Make this context visible as part of a virtual host
     */
    public void setHost( String h ) {
	vhost=h;
    }

    /** Return the virtual host name, or null if we are in the
	default context
    */
    public String getHost() {
	return vhost;
    }
    
    /** Virtual host support - this context will be part of 
     *  a virtual host with the specified name. You should
     *  set all the aliases. XXX Not implemented
     */
    public void addHostAlias( String alias ) {
	vhostAliases.addElement( alias );
    }

    public Enumeration getHostAliases() {
	return vhostAliases.elements();
    }
    // -------------------- Security - trusted code -------------------- 

    
    
    public void setTrusted( boolean t ) {
	trusted=t;
    }

    public boolean isTrusted() {
	return trusted;
    }

    public boolean allowAttribute( String name ) {
	// check if we can access this attribute.
	if( isTrusted() ) return true;
	log( "Illegal access to internal attribute ", null, Logger.ERROR);
	return false;
    }
}
