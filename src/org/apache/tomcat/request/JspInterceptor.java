/*
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
 */ 
package org.apache.tomcat.request;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.jsp.HttpJspPage;
import javax.servlet.jsp.JspFactory;

import java.util.*;
import java.io.*;

import org.apache.jasper.*;
import org.apache.jasper.Constants;
import org.apache.jasper.runtime.*;
import org.apache.jasper.compiler.*;
import org.apache.tomcat.logging.Logger;
import org.apache.jasper.compiler.Compiler;
import org.apache.tomcat.core.*;

/**
 * Plug in the JSP engine (a.k.a Jasper)! 
 *
 * @author Anil K. Vijendran
 * @author Harish Prabandham
 * @author Costin Manolache
 */
public class JspInterceptor extends BaseInterceptor {
    int jspInfoNOTE;

    public void engineInit(ContextManager cm )
	throws TomcatException
    {
	super.engineInit(cm);
	jspInfoNOTE=cm.getNoteId( ContextManager.HANDLER_NOTE,
				  "tomcat.jspInfoNote");
    }
    
    public void contextInit(Context ctx)
	throws TomcatException 
    {
	JspFactory.setDefaultFactory(new JspFactoryImpl());
	ctx.getServletLoader().addRepository( ctx.getWorkDir(),
					      ctx.getProtectionDomain());
    }

    public void preServletInit( Context ctx, ServletWrapper sw )
	throws TomcatException
    {
	Servlet theServlet = sw.getServlet();
	if (theServlet instanceof HttpJspBase)  {
	    if( debug > 0 )
		log( "PreServletInit: HttpJspBase.setParentClassLoader" + sw );
	    HttpJspBase h = (HttpJspBase) theServlet;
	    h.setClassLoader(ctx.getServletLoader().getClassLoader());
	}
    }

    public int requestMap( Request req ) {
	ServletWrapper wrapper=req.getWrapper();
	if( wrapper!=null && ! "jsp".equals( wrapper.getName())
	    && wrapper.getPath() == null)
	    return 0;

	// XXX jsp handler is still needed
	if( wrapper==null )
	    return 0;
	
	Context ctx= req.getContext();

	// If this Wrapper was already used, we have all the info
	JspInfo jspInfo=(JspInfo)wrapper.getNote( jspInfoNOTE );
	if( jspInfo == null ) {
	    if( debug > 0 ) log("New jsp page - no jspInfo ");
	    jspInfo=new JspInfo(req);
	    mapJspPage( req, jspInfo, jspInfo.uri, jspInfo.fullClassN);
	}

	if( jspInfo.jspSource.lastModified() 
	    > jspInfo.compileTime ) {
	    //XXX 	    destroy();
	    
	    // jump version number - the file needs to
	    // be recompiled, and we don't want a reload
	    jspInfo.nextVersion();
	    compile( req, jspInfo );
	    mapJspPage( req , jspInfo, jspInfo.uri, jspInfo.fullClassN);
	}

	return 0;
    }

    /** Add an exact map that will avoid *.jsp mapping and intermediate
     *  steps
     */
    void mapJspPage( Request req, JspInfo jspInfo,
		     String servletName, String classN )
    {
	Context ctx=req.getContext();
	ServletWrapper wrapper=null;
	String servletPath=servletName;
	// add the mapping - it's a "invoker" map ( i.e. it
	// can be removed to keep memory under control.
	// The memory usage is smaller than JspSerlvet anyway, but
	// can be further improved.
	try {
	    wrapper=ctx.getServletByName( servletName );
	    // We may want to replace the class and reset it if changed
	    
	    if( wrapper==null ) {
		wrapper=ctx.addServlet( servletName, classN );
		wrapper.setPath( servletName );
		wrapper.setOrigin( ServletWrapper.ORIGIN_INVOKER );
		
		ctx.addServletMapping( servletPath ,
				       servletPath );
		log( "Added mapping " + servletPath +
		     " path=" + servletPath );
	    }
	    wrapper.setServletClass( classN );
	    
	    wrapper.setNote( jspInfoNOTE, jspInfo );
	} catch( TomcatException ex ) {
	    ex.printStackTrace();
	    return ;
	}
	req.setWrapper( wrapper );
	if( debug>0) log("Wrapper " + wrapper);
    }

    /** Convert the .jsp file to a java file, then compile it to class
     */
    void compile(Request req, JspInfo jspInfo ) {
	log( "Compiling " + jspInfo.realClassPath);
	try {
	    // make sure we have the directories
	    File dir=new File( jspInfo.outputDir + "/" + jspInfo.pkgDir);
	    dir.mkdirs();
	    
	    JspMangler mangler= new JspMangler(jspInfo);
	    TomcatOptions options=new TomcatOptions();
	    JspEngineContext1 ctxt = new JspEngineContext1(req, mangler);
	    ctxt.setOptions( options );
	    
	    Compiler compiler=new Compiler(ctxt);
	    compiler.setMangler( mangler );
		
	    // we will compile ourself
	    compiler.setJavaCompiler( null );
	    
	    synchronized ( this ) {
		compiler.compile();
	    }
	    
	    javac( createJavaCompiler( options ), ctxt, mangler );
	    
	    if(debug>0)log( "Compiled to " + jspInfo.realClassPath );
	    jspInfo.touch();
	} catch( Exception ex ) {
	    ex.printStackTrace();
	}
    }
    
    String javaEncoding = "UTF8";           // perhaps debatable?
    static String sep = System.getProperty("path.separator");
    
    /** Compile a java to class. This should be moved to util, togheter
	with JavaCompiler - it's a general purpose code, no need to
	keep it part of jasper
    */
    public void javac(JavaCompiler javac, JspEngineContext1 ctxt,
		      Mangler mangler)
	throws JasperException
    {

        javac.setEncoding(javaEncoding);
	String cp=System.getProperty("java.class.path")+ sep + 
	    ctxt.getClassPath() + sep + ctxt.getOutputDir();
        javac.setClasspath( cp );
	if( debug>0) log( "ClassPath " + cp);
	
	ByteArrayOutputStream out = new ByteArrayOutputStream (256);
	javac.setOutputDir(ctxt.getOutputDir());
        javac.setMsgOutput(out);

	String javaFileName = mangler.getJavaFileName();
	/**
         * Execute the compiler
         */
        boolean status = javac.compile(javaFileName);

        if (!ctxt.keepGenerated()) {
            File javaFile = new File(javaFileName);
            javaFile.delete();
        }
    
        if (status == false) {
            String msg = out.toString ();
            throw new JasperException("Unable to compile "
                                      + msg);
        }
    }

    /** tool for customizing javac
     */
    public JavaCompiler createJavaCompiler(Options options)
	throws JasperException
    {
	String compilerPath = options.getJspCompilerPath();
	Class jspCompilerPlugin = options.getJspCompilerPlugin();
        JavaCompiler javac;

	if (jspCompilerPlugin != null) {
            try {
                javac = (JavaCompiler) jspCompilerPlugin.newInstance();
            } catch (Exception ex) {
		Constants.message("jsp.warning.compiler.class.cantcreate",
				  new Object[] { jspCompilerPlugin, ex }, 
				  Logger.FATAL);
                javac = new SunJavaCompiler();
	    }
	} else {
            javac = new SunJavaCompiler();
	}

        if (compilerPath != null)
            javac.setCompilerPath(compilerPath);

	return javac;
    }

    // XXX need to implement precompile
    private void precompile() {
	//         String qString = request.getQueryString();
	//          if (qString != null &&
	// 		 (qString.startsWith(Constants.PRECOMPILE) ||
	// 		  qString.indexOf("&" + Constants.PRECOMPILE)
	// 		  != -1))
	//             precompile = true;
    }

}

/** Given a URL, generate pkg, class name, etc.
    This is an internal ( private ) object, we'll add get/set
    later ( after we pass the experimental stage) 
 */
class JspInfo {
    String uri; // path 

    int version; // version

    String pkg;
    String pkgDir;
    String baseClassN;
    String fullClassN; // package.classN
    String classN; // no package
    String ext;

    String outputDir;
    String javaFilePath; // full path to the generated java file
    String realClassPath; // full path to the compiled java class
    String mapPath; // In even of server reload, keep last version

    File jspSource; // used to avoid File allocation for lastModified
    long compileTime;// tstamp - avoid one extra access

    JspInfo( Request req ) {
	init( req );
    }

    public String toString() {
	return uri +" " + version;
    }  

    /** Update compile time
     */
    public void touch() {
	compileTime=System.currentTimeMillis();
    }

    /** A change was detected, move to a new class name
     */
    public void nextVersion() {
	version++;
	updateVersionedPaths();
    }

    /** Update all paths that contain version number
     */
    void updateVersionedPaths() {
	classN = baseClassN + "_" + version;
	realClassPath = outputDir + "/" + pkgDir + "/" +
	    classN + ".class";
	javaFilePath = outputDir + "/" + pkgDir + "/" +
	    classN + ".java";
	fullClassN = pkg +"." + classN;
	
// 	System.out.println("ClassN=" + classN +
// 			   " realClassPath=" + realClassPath +
// 			   " javaFilePath=" + javaFilePath +
// 			   " fullClassN =" + fullClassN);
	writeVersion();
	// save to mapFile 
    }

    /** Compute various names used
     */
    void init(Request req ) {
	// 	String includeUri 
	// 	    = (String) req.getAttribute(Constants.INC_SERVLET_PATH);
	uri=req.getServletPath();
	Context ctx=req.getContext();
	outputDir = ctx.getWorkDir().getAbsolutePath();
	String jspFilePath=ctx.getRealPath( uri );
	jspSource = new File(jspFilePath);
	
	// extension
	int lastComp=uri.lastIndexOf(  "/" );
	String endUnproc=null;
	if( lastComp > 0 ) {
	    // has package
	    pkgDir=uri.substring( 1, lastComp );
	    endUnproc=uri.substring( lastComp+1 );
	} else {
	    endUnproc=uri.substring( 1 );
	}

	if( pkgDir!=null ) {
	    pkgDir=pkgDir.replace('.', '_');
	    pkg=pkgDir.replace('/', '.');
	    //	    pkgDir=pkgDir.replace('/', File.separator );
	}
	
	int extIdx=endUnproc.lastIndexOf( "." );

	if( extIdx>=0 ) {
	    baseClassN=endUnproc.substring( 0, extIdx );
	    ext=endUnproc.substring( extIdx );
	} else {
	    baseClassN=endUnproc;
	}
	// XXX insert "mangle" to make names safer

	mapPath = outputDir + "/" + pkgDir + "/" + baseClassN + ".ver";
	File mapFile=new File(mapPath);
	if( mapFile.exists() ) {
	    // read version from file
	    readVersion();
	    updateVersionedPaths();
	    updateCompileTime();
	} else {
	    version=0;
	    updateVersionedPaths();
	}

// 	System.out.println("uri=" + uri +
// 			   //" outputDir=" + outputDir +
// 			   //" jspSource=" + jspSource +
// 			   " pkgDir=" + pkgDir +
// 			   " baseClassN=" + baseClassN +
// 			   " ext=" + ext +
// 			   " mapPath=" + mapPath +
// 			   " version=" + version);
	
	
    }
    
    /** After startup we try to find if the file was precompiled
	before
    */
    void readVersion() {
	File mapFile=new File(mapPath);
	version=0;
	compileTime=0;
	try {
	    FileInputStream fis=new FileInputStream( mapFile );
	    version=(int)fis.read();
// 	    System.out.println("Version=" + version );
	    fis.close();
	} catch( Exception ex ) {
	    ex.printStackTrace();
	}
    }

    /** After we compile a page, we save the version in a
	file with known name, so we can restore the state when we
	restart. Note that this should move to a general-purpose
	persist repository ( on my plans for next version of tomcat )
    */
    void writeVersion() {
	File mapFile=new File(mapPath);
	try {
	    FileOutputStream fis=new FileOutputStream( mapFile );
	    fis.write(version);
// 	    System.out.println("WVersion=" + version );
	    fis.close();
	} catch( Exception ex ) {
	    ex.printStackTrace();
	}
    }

    /** After a startup we read the compile time from the class
	file lastModified. No further access to that file is done.
    */
    void updateCompileTime() {
	File f=new File( realClassPath );
	compileTime=0;
	if( ! f.exists() ) return;
	compileTime=f.lastModified();
    }
}

// XXX add code to set the options
class TomcatOptions implements Options {
    public boolean keepGenerated = true;
    public boolean largeFile = false;
    public boolean mappedFile = false;
    public boolean sendErrorToClient = false;
    public boolean classDebugInfo = false;
    public String ieClassId = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93";
    public Class jspCompilerPlugin = null;
    public String jspCompilerPath = null;

    public File scratchDir;
    private Object protectionDomain;
    public String classpath = null;

    public boolean getKeepGenerated() {
        return keepGenerated;
    }

    public boolean getLargeFile() {
        return largeFile;
    }

    public boolean getMappedFile() {
        return mappedFile;
    }
    
    public boolean getSendErrorToClient() {
        return sendErrorToClient;
    }
 
    public boolean getClassDebugInfo() {
        return classDebugInfo;
    }

    public String getIeClassId() {
        return ieClassId;
    }

    public void setScratchDir( File f ) {
	scratchDir=f;
    }
    
    public File getScratchDir() {
	System.out.println("Options: getScratchDir " + scratchDir);
        return scratchDir;
    }

    public final Object getProtectionDomain() {
	System.out.println("Options: GetPD" );
	return protectionDomain;
    }

    public String getClassPath() {
	System.out.println("Options: GetCP " + classpath  );
        return classpath;
    }

    public Class getJspCompilerPlugin() {
        return jspCompilerPlugin;
    }

    public String getJspCompilerPath() {
        return jspCompilerPath;
    }

    /**
     * FIXME: see how compiler.Compiler handles javaEncoding
     * and implement that behavior in this class when it 
     * is 'enabled' (pierred). This has been added only so the
     * code compiles.
     */
    public String getJavaEncoding() {
	return "FIXME: NOT IMPLEMENTED";
    }
}


class JspEngineContext1 implements JspCompilationContext {
    JspReader reader;
    ServletWriter writer;
    ServletContext context;
    JspLoader loader;
    String classpath; // for compiling JSPs.
    boolean isErrPage;
    String jspFile;
    String servletClassName;
    String servletPackageName;
    String servletJavaFileName;
    String contentType;
    Options options;

    Request req;
    Mangler m;
    
    public JspEngineContext1(Request req, Mangler m)
    {
	this.req=req;
	this.m=m;
    }

    public HttpServletRequest getRequest() {
	System.out.println("JspEngineContext1: getRequest " + req );
        return req.getFacade();
    }
    

    /**
     * Get the http response we are using now...
     */
    public HttpServletResponse getResponse() {
	System.out.println("JspEngineContext1: getResponse " );
        return req.getResponse().getFacade();
    }

    /**
     * The classpath that is passed off to the Java compiler. 
     */
    public String getClassPath() {
	System.out.println("JspEngineContext1: getClassPath " +
			   req.getContext().getServletLoader().getClassPath());
	return req.getContext().getServletLoader().getClassPath();
    }
    
    /**
     * Get the input reader for the JSP text. 
     */
    public JspReader getReader() {
	System.out.println("JspEngineContext1: getReader " + reader );
        return reader;
    }
    
    /**
     * Where is the servlet being generated?
     */
    public ServletWriter getWriter() {
	System.out.println("JspEngineContext1: getWriter " + writer );
        return writer;
    }
    
    /**
     * Get the ServletContext for the JSP we're processing now. 
     */
    public ServletContext getServletContext() {
	System.out.println("JspEngineContext1: getCtx " +
			   req.getContext().getFacade());
        return req.getContext().getFacade();
    }
    
    /**
     * What class loader to use for loading classes while compiling
     * this JSP? I don't think this is used right now -- akv. 
     */
    public ClassLoader getClassLoader() {
	System.out.println("JspEngineContext1: getLoader " + loader );
        return req.getContext().getServletLoader().getClassLoader();
    }

    public void addJar( String jar ) throws IOException {
	System.out.println("Add jar " + jar);
	//loader.addJar( jar );
    }

    /**
     * Are we processing something that has been declared as an
     * errorpage? 
     */
    public boolean isErrorPage() {
	System.out.println("JspEngineContext1: isErrorPage " + isErrPage );
        return isErrPage;
    }
    
    /**
     * What is the scratch directory we are generating code into?
     * FIXME: In some places this is called scratchDir and in some
     * other places it is called outputDir.
     */
    public String getOutputDir() {
	System.out.println("JspEngineContext1: getOutputDir " +
			   req.getContext().getWorkDir().getAbsolutePath());
        return req.getContext().getWorkDir().getAbsolutePath();
    }
    
    /**
     * Path of the JSP URI. Note that this is not a file name. This is
     * the context rooted URI of the JSP file. 
     */
    public String getJspFile() {
	String sP=req.getServletPath();
	Context ctx=req.getContext();
	System.out.println("JspEngineContext1: getJspFile " +
			   sP);//   ctx.getRealPath( sP ) );
	//        return ctx.getRealPath( sP );
	return sP;
    }
    
    /**
     * Just the class name (does not include package name) of the
     * generated class. 
     */
    public String getServletClassName() {
	System.out.println("JspEngineContext1: getServletClassName " +
			   m.getClassName());
        return m.getClassName();
    }
    
    /**
     * The package name into which the servlet class is generated. 
     */
    public String getServletPackageName() {
	System.out.println("JspEngineContext1: getServletPackageName " +
			   servletPackageName );
        return servletPackageName;
    }

    /**
     * Utility method to get the full class name from the package and
     * class name. 
     */
    public final String getFullClassName() {
	System.out.println("JspEngineContext1: getServletPackageName " +
			   servletPackageName + "." + servletClassName);
        if (servletPackageName == null)
            return servletClassName;
        return servletPackageName + "." + servletClassName;
    }

    /**
     * Full path name of the Java file into which the servlet is being
     * generated. 
     */
    public String getServletJavaFileName() {
	System.out.println("JspEngineContext1: getServletPackageName " +
			   servletPackageName + "." + servletClassName);
        return servletJavaFileName;
    }

    /**
     * Are we keeping generated code around?
     */
    public boolean keepGenerated() {
        return options.getKeepGenerated();
    }

    /**
     * What's the content type of this JSP? Content type includes
     * content type and encoding. 
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Get hold of the Options object for this context. 
     */
    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
	this.options=options;
    }
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setReader(JspReader reader) {
        this.reader = reader;
    }
    
    public void setWriter(ServletWriter writer) {
        this.writer = writer;
    }
    
    public void setServletClassName(String servletClassName) {
        this.servletClassName = servletClassName;
    }
    
    public void setServletPackageName(String servletPackageName) {
        this.servletPackageName = servletPackageName;
    }
    
    public void setServletJavaFileName(String servletJavaFileName) {
        this.servletJavaFileName = servletJavaFileName;
    }
    
    public void setErrorPage(boolean isErrPage) {
        this.isErrPage = isErrPage;
    }

    public Compiler createCompiler() throws JasperException {
	System.out.println("JspEngineContext1: createCompiler ");
	return null;
    }
    
    public String resolveRelativeUri(String uri)
    {
	System.out.println("JspEngineContext1: resolveRelativeUri " + uri);
	return null;
    };    

    public java.io.InputStream getResourceAsStream(String res)
    {
	System.out.println("JspEngineContext1: getRAS " + res);
        return req.getContext().getFacade().getResourceAsStream(res);
    };

    /** 
     * Gets the actual path of a URI relative to the context of
     * the compilation.
     */
    public String getRealPath(String path)
    {
	System.out.println("GetRP " + path + " " +
			   req.getContext().getRealPath(path));
	return req.getContext().getRealPath(path);
    };
}


class JspMangler implements Mangler{
    JspInfo jspInfo;
    
    public JspMangler(JspInfo info)  {
	this.jspInfo=info;
    }

    public final String getClassName() {
        return jspInfo.classN;
    }

    public final String getJavaFileName() {
	return jspInfo.javaFilePath;
    }

    public final String getPackageName() {
	return jspInfo.pkg;
	// It's not used, and shouldn't be used by compiler.
	// ( well, it's used to rename the file after compile
	// in JspServlet scheme - that must be out of compiler )
    }

    // Full path to the class file - without version.
    public final String getClassFileName() {
	return null; // see getPackageName comment
    }
}

