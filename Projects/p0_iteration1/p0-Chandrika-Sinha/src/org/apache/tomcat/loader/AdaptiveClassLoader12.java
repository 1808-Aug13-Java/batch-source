/*
 * Copyright (c) 1997-1999 The Java Apache Project.  All rights reserved.
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
 * 3. All advertising materials mentioning features or use of this
 *    software must display the following acknowledgment:
 *    "This product includes software developed by the Java Apache 
 *    Project for use in the Apache JServ servlet engine project
 *    <http://java.apache.org/>."
 *
 * 4. The names "Apache JServ", "Apache JServ Servlet Engine" and 
 *    "Java Apache Project" must not be used to endorse or promote products 
 *    derived from this software without prior written permission.
 *
 * 5. Products derived from this software may not be called "Apache JServ"
 *    nor may "Apache" nor "Apache JServ" appear in their names without 
 *    prior written permission of the Java Apache Project.
 *
 * 6. Redistributions of any form whatsoever must retain the following
 *    acknowledgment:
 *    "This product includes software developed by the Java Apache 
 *    Project for use in the Apache JServ servlet engine project
 *    <http://java.apache.org/>."
 *    
 * THIS SOFTWARE IS PROVIDED BY THE JAVA APACHE PROJECT "AS IS" AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE JAVA APACHE PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Java Apache Group. For more information
 * on the Java Apache Project and the Apache JServ Servlet Engine project,
 * please see <http://java.apache.org/>.
 *
 */

package org.apache.tomcat.loader;

import java.io.*;
import java.lang.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.zip.*;
import java.security.*;

/** Fixes for 1.2
 */
public class AdaptiveClassLoader12 extends AdaptiveClassLoader {
    public static int debug=0;
    
    public AdaptiveClassLoader12()
    {
	super();
    }
    
    public AdaptiveClassLoader reinstantiate() {
        AdaptiveClassLoader cl=new AdaptiveClassLoader12();
	cl.setRepository(repository);
	cl.setParent( parent );
	return cl;
    }

    void log( String s ) {
	System.out.println("AdaptiveClassLoader12: "  + s );
    }
    
    protected Class doDefineClass(String name, byte classData[], Object pd )
    {
	//	System.out.println("XXX defineClass12 " + pd );
	// 	if( sm != null ) {
	if( debug > 0) log( name + " " +
			    ((ProtectionDomain)pd).getCodeSource() );
	Class c=defineClass(name, classData, 0, classData.length,
			    (ProtectionDomain)pd);
	return c;
	// } else {
	// 	    return  defineClass(name, classData, 0, classData.length);
	// 	}
    }

    protected URL findResource(String name)
    {
        return findResourceInternal(name);
    }

    protected Enumeration findResources(String name) throws IOException
    {
      return findResourcesInternal(name, false).elements();
    }

    public boolean shouldReload( String classname ) {
	final String classnameF=classname;
	Boolean b = (Boolean)AccessController.doPrivileged(new
	    PrivilegedAction() {
		public Object run() 
		{
		    return new Boolean( checkExpired( classnameF ));
		} 
	    });
	return b.booleanValue();

    }

}
