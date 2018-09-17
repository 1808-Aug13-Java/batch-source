/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/jasper/compiler/Compiler.java,v 1.19.2.6 2001/03/09 23:35:25 marcsaeg Exp $
 * $Revision: 1.19.2.6 $
 * $Date: 2001/03/09 23:35:25 $
 *
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
 */ 
package org.apache.jasper.compiler;

import java.util.Hashtable;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.Constants;
import org.apache.jasper.JasperException;
import org.apache.jasper.compiler.ParseException;

import org.apache.tomcat.logging.Logger;

/**
 * If you want to customize JSP compilation aspects, this class is
 * something you should take a look at. 
 * 
 * Hope is that people can just extend Compiler and override things
 * like isOutDated() but inherit things like compile(). This might
 * change. 
 *
 * @author Anil K. Vijendran
 * @author Mandar Raje
 */
public class Compiler {
    protected JavaCompiler javac;
    protected Mangler mangler;
    protected JspCompilationContext ctxt;

    public Compiler(JspCompilationContext ctxt) {
        this.ctxt = ctxt;
    }
    
    /** 
     * Compile the jsp file from the current engine context
     *
     * @return true if the class file was outdated the jsp file
     *         was recompiled. 
     */
    public boolean compile()
        throws FileNotFoundException, JasperException, Exception 
    {
        String pkgName = mangler.getPackageName();
        String classFileName = mangler.getClassFileName();

        ctxt.setServletPackageName(pkgName);
        Constants.message("jsp.message.package_name_is",
                          new Object[] { (pkgName==null)?
                                          "[default package]":pkgName },
                          Logger.DEBUG);
        Constants.message("jsp.message.class_file_name_is",
                          new Object[] { classFileName },
                          Logger.DEBUG);

	if (!isOutDated())
            return false;

	// Hack to avoid readign the class file every time -
	// getClassName() is an _expensive_ operation, and it's needed only
	// if isOutDated() return true. 
        String javaFileName = mangler.getJavaFileName();
        ctxt.setServletJavaFileName(javaFileName);

        Constants.message("jsp.message.java_file_name_is",
                          new Object[] { javaFileName },
                          Logger.DEBUG);

	String className = mangler.getClassName();
        ctxt.setServletClassName(className);
        Constants.message("jsp.message.class_name_is",
                          new Object[] { className },
                          Logger.DEBUG);

        
        
        // Need the encoding specified in the JSP 'page' directive for
        //  - reading the JSP page
        //  - writing the JSP servlet source
        //  - compiling the generated servlets (pass -encoding to javac).
        // XXX - There are really three encodings of interest.

        String jspEncoding = "ISO-8859-1";          // default per JSP spec

	// We try UTF8 by default. If it fails, we use the java encoding 
	// specified for JspServlet init parameter "javaEncoding".
        String javaEncoding = "UTF8";

	// This seems to be a reasonable point to scan the JSP file
	// for a 'contentType' directive. If it found then the set
	// the value of 'jspEncoding to reflect the value specified.
	// Note: if (true) is convenience programming. It can be
	// taken out once we have a more efficient method.

	if (true) {
	    JspReader tmpReader = JspReader.createJspReader(
							    ctxt.getJspFile(),
							    ctxt,
							    jspEncoding);
	    String newEncode = changeEncodingIfNecessary(tmpReader);
	    if (newEncode != null) jspEncoding = newEncode;
	}

        JspReader reader = JspReader.createJspReader(
            ctxt.getJspFile(),
            ctxt,
            jspEncoding
        );

	OutputStreamWriter osw; 
	try {
	    osw = new OutputStreamWriter(
		      new FileOutputStream(javaFileName),javaEncoding);
	} catch (java.io.UnsupportedEncodingException ex) {
	    // Try to get the java encoding from the "javaEncoding"
	    // init parameter for JspServlet.
	    javaEncoding = ctxt.getOptions().getJavaEncoding();
	    if (javaEncoding != null) {
		try {
		    osw = new OutputStreamWriter(
			      new FileOutputStream(javaFileName),javaEncoding);
		} catch (java.io.UnsupportedEncodingException ex2) {
		    // no luck :-(
		    throw new JasperException(
			Constants.getString("jsp.error.invalid.javaEncoding",
					    new Object[] { 
						"UTF8", 
						javaEncoding,
					    }));
		}
	    } else {
		throw new JasperException(
		    Constants.getString("jsp.error.needAlternateJavaEncoding",
					new Object[] { "UTF8" }));		
	    }
	}
	ServletWriter writer = new ServletWriter(new PrintWriter(osw));

        ctxt.setReader(reader);
        ctxt.setWriter(writer);

        ParseEventListener listener = new JspParseEventListener(ctxt);
        
        Parser p = new Parser(reader, listener);
        listener.beginPageProcessing();
        p.parse();
        listener.endPageProcessing();
        writer.close();

        String classpath = ctxt.getClassPath(); 

        // I'm nuking
        //          System.getProperty("jsp.class.path", ".") 
        // business. If anyone badly needs this we can talk. -akv

        // I'm adding tc_path_add because it solves a real problem
        // and nobody has yet to come up with a better alternative.
        // Note: this is in two places.  Search for tc_path_add below.
        // If you have one, please let me know.  -Sam Ruby

        String sep = System.getProperty("path.separator");
        String[] argv = new String[] 
        {
            "-encoding",
            javaEncoding,
            "-classpath",
	    System.getProperty("java.class.path")+ sep + classpath + sep +
                System.getProperty("tc_path_add") + sep + ctxt.getOutputDir(),
            "-d", ctxt.getOutputDir(),
            javaFileName
        };

        StringBuffer b = new StringBuffer();
        for(int i = 0; i < argv.length; i++) {
            b.append(argv[i]);
            b.append(" ");
        }

        Constants.message("jsp.message.compiling_with",
                          new Object[] { b.toString() },
                          Logger.DEBUG);

        /**
         * 256 chosen randomly. The default is 32 if you don't pass
         * anything to the constructor which will be less. 
         */
        ByteArrayOutputStream out = new ByteArrayOutputStream (256);

        // if no compiler was set we can kick out now

        if (javac == null) {
            return true;
        }

        /**
         * Configure the compiler object
         * See comment above: re tc_path_add
         */
        javac.setEncoding(javaEncoding);
        javac.setClasspath( System.getProperty("java.class.path")+ sep + 
                            System.getProperty("tc_path_add") + sep +
                            classpath + sep + ctxt.getOutputDir());
        javac.setOutputDir(ctxt.getOutputDir());
        javac.setMsgOutput(out);
        javac.setClassDebugInfo(ctxt.getOptions().getClassDebugInfo());

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
            throw new JasperException(Constants.getString("jsp.error.unable.compile")
                                      + msg);
        }

        String classFile = ctxt.getOutputDir() + File.separatorChar;
        if (pkgName != null && !pkgName.equals(""))
            classFile = classFile + pkgName.replace('.', File.separatorChar) + 
                File.separatorChar;
        classFile = classFile + className + ".class";

        if (!classFile.equals(classFileName)) {
            File classFileObject = new File(classFile);
            File myClassFileObject = new File(classFileName);
            if (myClassFileObject.exists())
                myClassFileObject.delete();
            if (classFileObject.renameTo(myClassFileObject) == false)
                throw new JasperException(Constants.getString("jsp.error.unable.rename",
                                                              new Object[] { 
                                                                  classFileObject, 
                                                                  myClassFileObject
                                                              }));
        }

        return true;
    }

    public void computeServletClassName() {
	// Hack to avoid readign the class file every time -
	// getClassName() is an _expensive_ operation, and it's needed only
	// if isOutDated() return true. 
	String className = mangler.getClassName();
        ctxt.setServletClassName(className);
        Constants.message("jsp.message.class_name_is",
                          new Object[] { className },
                          Logger.DEBUG);
    }
    
    /**
     * This is a protected method intended to be overridden by 
     * subclasses of Compiler. This is used by the compile method
     * to do all the compilation. 
     */
    public boolean isOutDated() {
	return true;
    }
    
    /**
     * Set java compiler info
     */
    public void setJavaCompiler(JavaCompiler javac) {
        this.javac = javac;
    }

    /**
     * Set Mangler which will be used as part of compile().
     */
    public void setMangler(Mangler mangler) {
        this.mangler = mangler;
    }

    /**
     * Change the encoding for the reader if specified.
     */
    public String changeEncodingIfNecessary(JspReader tmpReader)
    throws ParseException {

	// A lot of code replicated from Parser.java
	// Main aim is to "get-it-to-work".
	while (tmpReader.skipUntil("<%@") != null) {

	    tmpReader.skipSpaces();

	    // check if it is a page directive.
	    if (tmpReader.matches("page")) {

		tmpReader.advance(4);
		tmpReader.skipSpaces();
		
		try {
		    Hashtable attrs = tmpReader.parseTagAttributes();
		    String ct = (String) attrs.get("contentType");
		    if (ct != null) {
			int loc = ct.indexOf("charset=");
			if (loc > 0) {
			    String encoding = ct.substring(loc + 8);
			    return encoding;
			}
		    }
		} catch (ParseException ex) {
		    // Ignore the exception here, it will be caught later.
		    return null;
		}
	    }
	}
	return null;
    }

	 /**
	  * Remove generated files
	  */
	 public void removeGeneratedFiles()
	 {
		 try{
			 // XXX Should we delete the generated .java file too?
			 String classFileName = mangler.getClassFileName();
			 if(classFileName != null){
				 File classFile = new File(classFileName);
				 classFile.delete();
			 }
		 }catch(Exception e){
		 }
	 }
}


