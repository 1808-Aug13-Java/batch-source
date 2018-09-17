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
package org.apache.tomcat.request;

import org.apache.tomcat.core.*;
import org.apache.tomcat.core.Constants;
import org.apache.tomcat.request.*;
import org.apache.tomcat.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.security.*;
import javax.servlet.ServletOutputStream;

import org.apache.tomcat.logging.*;


/**
 * Handler for static files.
 *
 * In order to take advantage of doing searches for file-localized
 * versions of the static files, the "localization" property needs to
 * be set to "file" in the StaticInterceptor entry in the server.xml file.
 *
 * @author costin@dnt.ro
 * @author Arieh Markel [arieh.markel@sun.com]
 */
public class StaticInterceptor extends BaseInterceptor {
    int realFileNote=-1;

    //  values for localization
    //
    int NONE_LOC = 0;
    int FILE_LOC = 1;
    int DOCB_LOC = 2;

    int localization = NONE_LOC;

    public static final String  FILE_LOCALIZATION = "file";
    public static final String  DOCBASE_LOCALIZATION = "docbase";
    public static final String  NO_LOCALIZATION = "none";
    public static final String  LOCALIZATION_PROPERTY = "localization";

    public StaticInterceptor() {
    }

    /**  set the mode of localization for resource lookups
     *
     *  The following are possible localization modes:
     *<UL>
     *<LI>file</LI>
     *<LI>docbase</LI>
     *<LI>none</LI>
     *</UL>
     *
     *  @param s the localization mode
     */
    public void setLocalization (String s) {
	if (FILE_LOCALIZATION.equals(s))
	    localization = FILE_LOC;
	else if (DOCBASE_LOCALIZATION.equals(s))
	    localization = DOCB_LOC;
    }

    /**  return the mode of localization for resource lookups
     *
     *  @return the localization mode
     */
    public String getLocalization() {
	return (localization == FILE_LOC)
	            ? FILE_LOCALIZATION
	            : (localization == DOCB_LOC)
	                        ? DOCBASE_LOCALIZATION
	                        : NO_LOCALIZATION;
    }

    /**
     * The "suppress directory listings" flag.
     */
    private boolean suppress = false;

    public boolean getSuppress() {
        return (this.suppress);
    }

    public void setSuppress(boolean suppress) {
        this.suppress = suppress;
    }

    public void engineInit(ContextManager cm) throws TomcatException {
	super.engineInit( cm );
	
	try {
	    realFileNote = cm.getNoteId( ContextManager.REQUEST_NOTE,
				       "static.realFile");
	} catch( TomcatException ex ) {
	    ex.printStackTrace();
	    throw new RuntimeException( "Invalid state ");
	}
    }
    
    public void contextInit( Context ctx)
	throws TomcatException
    {
	FileHandler fileHandler=new FileHandler();
	DirHandler dirHandler=new DirHandler();
	fileHandler.setNoteId( realFileNote );
	dirHandler.setNoteId( realFileNote );
	debug=0;
	ctx.addServlet( fileHandler );
	ctx.addServlet( dirHandler);
	fileHandler.setDebug( debug );
	dirHandler.setDebug( debug );
    }

    public int requestMap(Request req) {
	if( req.getWrapper() != null )
	    return 0;

	Context ctx=req.getContext();

	// will call getRealPath(), all path normalization
	// and a number of checks
	String pathInfo=req.getServletPath();
	if( pathInfo==null ) pathInfo="";

	String absPath = null;

	if (localization == FILE_LOC)
	    absPath = ctx.getRealPath (pathInfo,
				       RequestUtil.getLocale(req),
				       Locale.getDefault(), "file");
	else if (localization == DOCB_LOC)
	    absPath = ctx.getRealPath (pathInfo,
				       RequestUtil.getLocale(req),
				       Locale.getDefault(), "docbase");

	else
	    absPath = ctx.getRealPath( pathInfo );

	if( absPath == null ) return 0;
	String requestURI=req.getRequestURI();

	if( debug > 0 )
	    log( "Requested: "  + absPath );

	File file=new File( absPath );

	if( file.isFile() ) {
	    if( debug > 0 ) log( "Setting handler to file " + absPath);
	    req.setNote( realFileNote, absPath );
	    req.setWrapper(  ctx.getServletByName( "tomcat.fileHandler"));
	    return 0;
	}

	if( ! file.isDirectory() ) {
	    // we support only files and dirs
	    if( debug > 0) log( "No file and no directory");
	    return 0; // no handler is set - will end up as 404
	}

	// Directory, check if we have a welcome file
	String welcomeFile = null;

	//
	if (localization == FILE_LOC || localization == DOCB_LOC)
	    welcomeFile = getWelcomeFile(ctx, pathInfo,
					 RequestUtil.getLocale(req),
				         Locale.getDefault());
	else
	    welcomeFile = getWelcomeFile(ctx, file);

	if( debug > 0 )
	    log( "DefaultServlet: welcome file: "  + welcomeFile);

	// Doesn't matter if we are or not in include
	if( welcomeFile == null  ) {
	    if (suppress)
                return 404;  // Not found
	    // normal dir, no welcome. 
	    req.setWrapper( ctx.getServletByName( "tomcat.dirHandler"));
	    if( debug > 0) log( "Dir handler");
	    return 0;
	}

	// Send redirect to the welcome file.
	// This is consistent with other web servers and avoids
	// gray areas in the spec - if the welcome file is a jsp,
	// what will be the requestPath - if it's the dir, then
	// jasper will not work. The original code created a
	// RequestDispatcher and the JSP will see an included
	// request, but that's not a specified behavior
	String redirectURI=null;
	redirectURI=concatPath( requestURI, welcomeFile);
	req.setAttribute("javax.servlet.error.message",
			 redirectURI);
	if( debug > 0) log( "Redirect " + redirectURI );
	return 302;
    }

    private static String concatPath( String s1, String s2 ) {
	if( s1.endsWith( "/" ) ) {
	    if( s2.startsWith( "/" ))
		return s1 + s2.substring(1);
	    else
		return s1 + s2;
	} else {
	    if( s2.startsWith("/"))
		return s1 + s2;
	    else
		return s1 + "/" + s2;
	}
    }
    
    private String getWelcomeFile(Context context, File dir) {
	Enumeration enum = context.getWelcomeFiles();

	while (enum.hasMoreElements()) {
	    String fileName = (String)enum.nextElement();
	    File f = new File(dir, fileName);
	    if (f.exists()) {
		return fileName;
	    }
	}
	return null;
    }

    private String getWelcomeFile(Context context, String path,
				  Locale loc, Locale fbloc) {
	Enumeration enum = context.getWelcomeFiles();

	String docBase = context.getAbsolutePath();
	StringBuffer sb = new StringBuffer(docBase);

	while (enum.hasMoreElements()) {
	    String fileName = (String)enum.nextElement();

	    String retPath = null;
	    
	    if (localization == FILE_LOC)
	    {
		//  preserve the basename of the path, so that the
		//  localized version can be identified following
		//  the lookup
		//
		int  ftype = fileName.lastIndexOf ('.');
		String fbasen = (ftype != -1) 
				    ? fileName.substring (0, ftype)
				    : fileName;

		String fp = concatPath(docBase, fileName);

		retPath = FileUtil.getLocalizedResource (
						 	 fp,
						    	 loc,
							 fbloc);
		if (new File(retPath).exists())
		{
		    int pathPos = retPath.lastIndexOf (fbasen);
		    return retPath.substring (pathPos);
		}
	    }
	    else 	// localize according to DOCBASE
	    {
		//  make sure there is a File.separator between the
		//  passed path and the welcome file
		//
		if (null != path)
		{
		    sb = new StringBuffer(path);
		    if (! path.endsWith(File.separator))
			sb.append(File.separatorChar);
		}
		else
		    sb = new StringBuffer();

		sb.append (fileName);

		retPath = FileUtil.getDocBaseLocalizedResource (
							docBase,
							sb.toString(),
							loc,
							fbloc);
		if ((new File(retPath)).exists())
		{
		    return retPath.substring(docBase.length());
		}
	    }
	}

	return null;
    }
}

// -------------------- Handlers --------------------

/** Serve the content of a file ( and nothing more !).
 *
 */
class FileHandler extends ServletWrapper  {
    int realFileNote;
    
    FileHandler() {
	initialized=true;
	internal=true;
	name="tomcat.fileHandler";
    }

    public void setNoteId( int n ) {
	realFileNote=n;
    }

    public void doService(Request req, Response res)
	throws Exception
    {
	// if we are in include, with req==original request
	// - just use the current sub-request
	Request subReq=req;
	if(req.getChild()!=null) 
	    subReq=req.getChild();

	Context ctx=subReq.getContext();
    // If this file is being included, use javax.servlet.include.servlet_path.
    String pathInfo = (String)subReq.getAttribute("javax.servlet.include.servlet_path");
    if(pathInfo == null)
        pathInfo=subReq.getServletPath();
	String absPath = (String)subReq.getNote( realFileNote );
	if( absPath==null ) 
	    absPath=ctx.getRealPath( pathInfo );

	if( debug>0) log( "Requested file = " + absPath);
	String base = ctx.getAbsolutePath();
	absPath = extraCheck( base, absPath );
	if( absPath==null ) {
	    context.getContextManager().handleStatus( req, res, 404);
	    return;
	}

	File file = new File( absPath );
	if( debug>0) log( "After paranoic checks = " + absPath);
	
	String mimeType=ctx.getMimeMap().getContentTypeFor(absPath);

	if (mimeType == null) {
	    mimeType = "text/plain";
	}
	if( debug>0) log( "Serving  " + absPath);
	
	res.setContentType(mimeType);
	res.setContentLength((int)file.length());

	setDateHeader(res, "Last-Modified", file.lastModified());

	FileInputStream in=null;
	try {
	    in = new FileInputStream(file);

	    if( res.isUsingWriter() ) {
		InputStreamReader r = new InputStreamReader(in);
		PrintWriter out=res.getWriter();
		char[] buf = new char[1024];
		int read = 0;
		
		while ((read = r.read(buf)) != -1) {
		    out.write(buf, 0, read);
		}
	    } else {
		OutputStream out=res.getOutputStream();
		byte[] buf = new byte[1024];
		int read = 0;
		
		while ((read = in.read(buf)) != -1) {
		    out.write(buf, 0, read);
		}
	    } 
	} catch (FileNotFoundException e) {
	    // Figure out what we're serving
	    context.getContextManager().handleStatus( req, res, 404);
	} finally {
	    if (in != null) {
		in.close();
	    }
	}
    }

    static void setDateHeader( Response res, String name, long value ) {
	MimeHeaders headers=res.getMimeHeaders();
	MimeHeaderField headerF=headers.find( name );
	if( headerF == null )
	    headerF=headers.putHeader();
	headerF.setName( name );
	headerF.setDateValue( value );
    }

    /** All path checks that were part of DefaultServlet
     */
    String extraCheck( String base, String absPath ) {
	// Extra safe 
	if (absPath.endsWith("/") ||
	    absPath.endsWith("\\") ||
	    absPath.endsWith(".")) {
	    log("Ends with \\/. " + absPath);
	    return null;
	}
    if (absPath.length() > base.length())
	{
		String relPath=absPath.substring( base.length() + 1);
		if( debug>0) log( "RelPath = " + relPath );

		String relPathU=relPath.toUpperCase();
		if ( relPathU.startsWith("WEB-INF") ||
                     relPathU.startsWith("META-INF") ||
                    (relPathU.indexOf("/WEB-INF/") >= 0) ||
                    (relPathU.indexOf("/META-INF/") >= 0) ) {
			return null;
		}
	}
	return absPath;
    }


}

// -------------------- Directory --------------------

/** HTML-display for directories ( and nothing more !).
 *  This is the handler for static resources of type "dir".
 */
class DirHandler extends ServletWrapper  {
    private static final String datePattern = "EEE, dd MMM yyyyy HH:mm z";
    int realFileNote;
    
    DirHandler() {
	initialized=true;
	internal=true;
	name="tomcat.dirHandler";
    }

    public void setNoteId( int n ) {
	realFileNote=n;
    }

    public void doService(Request req, Response res)
	throws Exception
    {
	// this is how get locale is implemented. Ugly, but it's in
	// the next round of optimizations
	Locale locale=RequestUtil.getLocale(req);
	StringManager sm=StringManager.
	    getManager("org.apache.tomcat.resources",locale);
	DateFormat dateFormat =
	    new SimpleDateFormat(datePattern,locale );

	boolean inInclude=req.getChild()!=null;
	Request subReq=req;
	if( inInclude ) subReq = req.getChild();
	Context ctx=req.getContext();
	String pathInfo=subReq.getServletPath();
	if( pathInfo == null ) pathInfo="";
	String absPath=ctx.getRealPath( pathInfo );
	File file = new File( absPath );
	String requestURI=subReq.getRequestURI();
	String base = ctx.getAbsolutePath();
	if (absPath.length() > base.length())
	{
		String relPath=absPath.substring( base.length() + 1);
		String relPathU=relPath.toUpperCase();
		if ( relPathU.startsWith("WEB-INF") ||
				relPathU.startsWith("META-INF")) {
			context.getContextManager().handleStatus( req, res, 404);
			return;
		}
	}

	StringBuffer buf = new StringBuffer();
	
	if (! inInclude) {
	    res.setContentType("text/html");
	    buf.append("<html>\r\n");
	    buf.append("<head>\r\n");
	    buf.append("<title>")
		.append(sm.getString("defaultservlet.directorylistingfor"))
		.append(requestURI);
	    buf.append("</title>\r\n</head><body bgcolor=white>\r\n");
	}

	buf.append("<table width=90% cellspacing=0 ");
	buf.append("cellpadding=5 align=center>");
	buf.append("<tr><td colspan=3><font size=+2><strong>");
	buf.append(sm.getString("defaultservlet.directorylistingfor"))
	    .append(requestURI);
	buf.append("</strong></td></tr>\r\n");

	if (! pathInfo.equals("/")) {
	    buf.append("<tr><td colspan=3 bgcolor=#ffffff>");
	    //buf.append("<a href=\"../\">Up one directory");
	    
	    String toPath = requestURI;

	    if (toPath.endsWith("/")) {
		toPath = toPath.substring(0, toPath.length() - 1);
	    }
	    
	    toPath = toPath.substring(0, toPath.lastIndexOf("/"));
	    
	    if (toPath.length() == 0) {
		toPath = "/";
	    }
	    
	    buf.append("<a href=\"" + toPath + "\"><tt>"+
		       sm.getString("defaultservlet.upto")+ toPath);
	    buf.append("</tt></a></td></tr>\r\n");
	}

	// Pre-calculate the request URI for efficiency

	// Make another URI that definitely ends with a /
	String slashedRequestURI = null;

	if (requestURI.endsWith("/")) {
	    slashedRequestURI = requestURI;
	} else {
	    slashedRequestURI = requestURI + "/";
	}

	String[] fileNames = file.list();
	boolean dirsHead=true;
	boolean shaderow = false;

	for (int i = 0; i < fileNames.length; i++) {
	    String fileName = fileNames[i];

	    // Don't display special dirs at top level
	    if( (pathInfo.length() == 0 || "/".equals(pathInfo)) &&
     		"WEB-INF".equalsIgnoreCase(fileName) ||
 	    	"META-INF".equalsIgnoreCase(fileName) )
    		continue;

	    File f = new File(file, fileName);

	    if (f.isDirectory()) {
		if( dirsHead ) {
		    dirsHead=false;
		    buf.append("<tr><td colspan=3 bgcolor=#cccccc>");
		    buf.append("<font size=+2><strong>").
			append( sm.getString("defaultservlet.subdirectories")).
			append( "</strong>\r\n");
		    buf.append("</font></td></tr>\r\n");
		}

	        String fileN = f.getName();

	        buf.append("<tr");

	        if (shaderow) buf.append(" bgcolor=#eeeeee");
		shaderow=!shaderow;
		
	        buf.append("><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	        buf.append("<tt><a href=\"")
		    .append(slashedRequestURI)
	            .append(fileN)
		    .append("\">")
		    .append(fileN)
	            .append("/</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
	            .append("</tt>\r\n");
	        buf.append("</td><td><tt>&nbsp;&nbsp;</tt></td>");
	        buf.append("<td align=right><tt>");
		buf.append(dateFormat.format(new Date(f.lastModified())));
	        buf.append("</tt></td></tr>\r\n");
	    }
	}

	shaderow = false;
	buf.append("<tr><td colspan=3 bgcolor=#ffffff>&nbsp;</td></tr>");
	boolean fileHead=true;
	
	for (int i = 0; i < fileNames.length; i++) {
	    File f = new File(file, fileNames[i]);

	    if (f.isFile()) {
		String fileN = f.getName();
		
		if( fileHead ) {
		    fileHead=false;
		    buf.append("<tr><td colspan=4 bgcolor=#cccccc>");
		    buf.append("<font size=+2><strong>")
			.append(sm.getString("defaultservlet.files"))
			.append("</strong></font></td></tr>");
		}

		buf.append("<tr");

		if (shaderow) buf.append(" bgcolor=#eeeeee");
		shaderow = ! shaderow;
		
		buf.append("><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");

		buf.append("<tt><a href=\"")
		    .append(slashedRequestURI)
		    .append(fileN).append("\">")
		    .append( fileN )
		    .append( "</a>");
		buf.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt>");
		buf.append("</td>\r\n");

		buf.append("<td align=right><tt>");
		displaySize( buf, (int)f.length());
		buf.append("</tt></td>");

		buf.append("<td align=right><tt>");
		buf.append(dateFormat.format(new Date(f.lastModified())));
		buf.append("</tt></td></tr>\r\n");
	    }
	    
	    buf.append("\r\n");
	}
	
	buf.append("<tr><td colspan=3 bgcolor=#ffffff>&nbsp;</td></tr>");
	buf.append("<tr><td colspan=3 bgcolor=#cccccc>");
	buf.append("<font size=-1>");
	buf.append(Constants.TOMCAT_NAME);
	buf.append(" v");
	buf.append(Constants.TOMCAT_VERSION);
	buf.append("</font></td></tr></table>");
	
	if (! inInclude)  buf.append("</body></html>\r\n");

	if( res.isUsingWriter() ) {
	    PrintWriter out=res.getWriter();
	    out.print(buf);
	} else {
	    ServletOutputStream out=res.getOutputStream();
	    out.print(buf.toString());
	}
    }

    void displaySize( StringBuffer buf, int filesize ) {
	int leftside = filesize / 1024;
	int rightside = (filesize % 1024) / 103;  // makes 1 digit
	// To avoid 0.0 for non-zero file, we bump to 0.1
	if (leftside == 0 && rightside == 0 && filesize != 0) 
	    rightside = 1;
	buf.append(leftside).append(".").append(rightside);
	buf.append(" KB");
    }
}
