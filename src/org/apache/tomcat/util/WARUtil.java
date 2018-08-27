/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/WARUtil.java,v 1.3 2000/01/12 06:35:21 costin Exp $
 * $Revision: 1.3 $
 * $Date: 2000/01/12 06:35:21 $
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
 * [Additional notices, if required by prior licensing conditions]
 *
 */ 


package org.apache.tomcat.util;

import org.apache.tomcat.core.*;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;

/**
 *
 * @author James Todd [gonzo@eng.sun.com
 */

public class WARUtil {
    /** Expand a WAR/Jar file in a directory.
     *  @param dir destination directory
     *  @param war URL for the source WAR/JAR/ZIP file. Starting
     *         and ending "/" will be removed
     *
     */ 
    public static void expand(File dir, URL war)
    throws MalformedURLException, IOException {
        String s = trim(war.getFile(), "/");
	URL u = new URL(s);
	ZipInputStream zis = new ZipInputStream(u.openStream());
	ZipEntry ze = null;

	while ((ze = zis.getNextEntry()) != null) {
            try {
		File f = new File(dir, ze.getName());

		if (ze.isDirectory()) {
		    f.mkdirs(); 
		} else {
		    byte[] buffer = new byte[1024];
		    int length = 0;
		    FileOutputStream fos = new FileOutputStream(f);
		    
		    while ((length = zis.read(buffer)) >= 0) {
			fos.write(buffer, 0, length);
		    }
		    
		    fos.close();
		}
	    } catch( FileNotFoundException ex ) {
		// XXX replace with a call to log() when available
		System.out.println("WARUtil: FileNotFoundException: " +  ze.getName() + " / " + s );
	    }
	}

	zis.close();
    }

    private static String trim(String s, String t) {
	if (s.startsWith(t)) {
	    s = s.substring(t.length());
	}
	
	if (s.endsWith(t)) {
	    s = s.substring(0, s.length() - t.length());
	}

        return s;
    }

    
    /** Construct a URL from a base and a relative path
     */
    public static URL createURL( Context ctx, String mappedPath )
	throws MalformedURLException
    {
	// XXX need rework, we should remove war until we know better
	// or have an architecture for it
	URL docBase = ctx.getDocumentBase();
	URL url=null;
	if (ctx.isWARExpanded()) {
	    File f = new File(ctx.getWARDir().toString());
	    String absPath = f.getAbsolutePath();
	    
	    // take care of File.getAbsolutePath() troubles
	    // on jdk1.1.x/win
	    
	    absPath = FileUtil.patch(absPath);
	    
	    if (! absPath.startsWith("/")) {
		absPath = "/" + absPath;
	    }
	    
	    url = new URL("file://localhost" + absPath + "/" +
			  mappedPath);
	} else {
	    String documentBase = docBase.toString();
	    
	    if (documentBase.endsWith("/")) {
		documentBase = documentBase.substring(0,
						      documentBase.length() - 1);
	    }
	    
	    url = new URL(documentBase + "!" + mappedPath);
	}
	return url;
    }
    
}
