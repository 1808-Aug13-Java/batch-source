/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/util/Attic/URLUtil.java,v 1.7.2.3 2001/07/17 04:25:55 marcsaeg Exp $
 * $Revision: 1.7.2.3 $
 * $Date: 2001/07/17 04:25:55 $
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

import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.IOException;

/**
 *
 * @author James Todd [gonzo@eng.sun.com]
 */

public class URLUtil {
    public static URL resolve(String s)
	throws MalformedURLException
    {
        URL resolve = null;
	// construct a URL via the following heuristics:
	//
	//    if arg contains ":/" then
	//        assume a valid uri
	//    else if url arg is specified
	//        construct uri
	//    else if an absolute path then
	//        construct file uri
	//    else
	//        construct file uri by prepending the
	//        working directory

	if (s.indexOf("://") > -1 ||
            s.indexOf("file:") > -1 ) {
	    resolve = new URL(s);
	    // 	} else if (url != null) {
	    // 	    resolve = new URL(url, s);
	} else if (s.startsWith(File.separator) ||
            s.startsWith("/") ||
	    (s.length() >= 2 &&
	     Character.isLetter(s.charAt(0)) &&
	     s.charAt(1) == ':')) {
            String fName = s;

            try {
                fName = (String)(new File(s)).getCanonicalPath();
            } catch (NullPointerException npe) {
            } catch (IOException npe) {
            }

	    resolve = new URL("file", "", fName);
	} else {
            String path = System.getProperty("user.dir") +
                File.separator + s;

	    resolve = new URL("file", "", path);
	}
	
        if (! resolve.getProtocol().equalsIgnoreCase("war") &&
            resolve.getFile().toLowerCase().endsWith(
						     "." + "war")) {
            URL u = new URL("war" + ":" +
			    resolve.toString());
	    
            resolve = u;
        }

        resolve = new URL(trim(resolve.toString(), ".", ".."));
        resolve = new URL(trim(resolve.toString(), "./"));
	
	return resolve;
    }

    private static String trim(String s, String t) {
        return trim(s, t, null);
    }

    private static String trim(String s, String r, String t) {
        while (s.endsWith(r) &&
            ((t == null) ? true : (! s.endsWith(t)))) {
            int i = s.length() - r.length();

            s = s.substring(0, i);
        }

        return s;
    }

    public static String removeLast( String s) {
	int i = s.lastIndexOf("/");
	
	if (i > 0) {
	    s = s.substring(0, i);
	} else if (i == 0 && ! s.equals("/")) {
	    s = "/";
	} else {
	    s = "";
	}
	return s;
    }

    public static String getFirst( String path ) {
	if (path.startsWith("/")) 
	    path = path.substring(1);
	
	int i = path.indexOf("/");
	if (i > -1) {
	    path = path.substring(0, i);
	}

	return  "/" + path;
    }
    
    public static String getExtension( String path ) {
        int i = path.lastIndexOf(".");
	int j = path.lastIndexOf("/");

	if ((i > 0) && (i > j))
	    return path.substring(i);
	else
	    return null;
    }

    public static boolean hasEscape(String url)
    {
        boolean hasEscape = false;

        int pctindex = -1;
        int index = 0;
        while((pctindex = url.indexOf('%', pctindex+1)) >= 0){
            try{
                String escape = url.substring(pctindex+1, pctindex+3);
                int code = Integer.parseInt(escape, 16);
                hasEscape = true;
                break;
            }catch(IndexOutOfBoundsException e){
                break;
            }catch(NumberFormatException e){
            }
        }

        return hasEscape;
    }

    // Based on Apache's path normalization code
    public static String normalizeURI(String URI)
    {
        int start=0;
        int end=URI.length();
        char buff[] = new char[URI.length()];
        URI.getChars(0, URI.length(), buff, 0);
        int i=0;
        int j=0;
    
        // remove //
        for( i=start, j=start; i<end-1; i++ ) {
            if( buff[i]== '/' && buff[i+1]=='/' ) {
                while( buff[i+1]=='/' ) i++;
            }
            buff[j++]=buff[i];
        }
        if( i!=j ) {
            buff[j++]=buff[end-1];
            end=j;
        }
    
        // remove /./
        for( i=start, j=start; i<end-1; i++ ) {
            if( buff[i]== '.' && buff[i+1]=='/' &&
                ( i==0 || buff[i-1]=='/' )) {
                // "/./"
                i+=1;
                if( i==end-1 ) j--; // cut the ending /
            } else {
                buff[j++]=buff[i];
            }
        }
        if( i!=j ) {
            buff[j++]=buff[end-1];
            end=j;
        }
    
        // remove  /. at the end
        j=end;
        if( end==start+1 && buff[start]== '.' )
            end--;
        else if( end > start+1 && buff[ end-1 ] == '.' &&
                 buff[end-2]=='/' ) {
            end=end-2;
        }
    
        // remove /../
        for( i=start, j=start; i<end-2; i++ ) {
            if( buff[i] == '.' &&
                buff[i+1] == '.' &&
                buff[i+2]== '/' &&
                ( i==0 || buff[ i-1 ] == '/' ) ) {
    
                i+=1;
                // look for the previous /
                j=j-2;
                while( j>0 && buff[j]!='/' ) {
                    j--;
                }
            } else {
                buff[j++]=buff[i];
            }
        }
        if( i!=j ) {
            buff[j++]=buff[end-2];
            buff[j++]=buff[end-1];
            end=j;
        }
    
    
        // remove trailing xx/..
        j=end;
        if( end>start + 3 &&
            buff[end-1]=='.' &&
            buff[end-2]=='.' &&
            buff[end-3]=='/' ) {
            end-=4;
            while( end>0 &&  buff[end]!='/' )
                end--; 
        }

        String result = new String(buff, 0, end);
        return result;
    }
    
}
