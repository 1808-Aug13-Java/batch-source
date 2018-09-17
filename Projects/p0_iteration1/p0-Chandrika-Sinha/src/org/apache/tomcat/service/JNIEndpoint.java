/*
 * $Header: /home/cvs/jakarta-tomcat/src/share/org/apache/tomcat/service/Attic/JNIEndpoint.java,v 1.3.2.1 2000/11/10 06:42:50 craigmcc Exp $
 * $Revision: 1.3.2.1 $
 * $Date: 2000/11/10 06:42:50 $
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


package org.apache.tomcat.service;

import java.util.*;
import java.io.*;
import org.apache.tomcat.core.*;
import org.apache.tomcat.util.*;
import org.apache.tomcat.service.connector.*;

/**
 * Handle incoming JNI connections.
 *
 * <br>
 * Based on <code>TcpEndpoint</code>
 *
 * @author Gal Shachor <shachor@il.ibm.com>
 */
public class JNIEndpoint {

    private static StringManager sm =StringManager.getManager("org.apache.tomcat.service");

    JNIConnectionHandler handler;

    boolean running = false;

    public JNIEndpoint() {
    }

    // -------------------- Configuration --------------------

    public void setConnectionHandler(JNIConnectionHandler handler ) {
	    this.handler=handler;
    }

    public JNIConnectionHandler getConnectionHandler() {
    	return handler;
    }

    // -------------------- Public methods --------------------

    public synchronized void startEndpoint() {
	System.out.println("Running ...");
	running=true;
        notify();
    }


    public synchronized void stopEndpoint() {
    	running=false;
        notify();
    }

    // -------------------- JNI Entry points

    public int startup(String cmdLine,
                       String stdout,
                       String stderr)
    {
        try {
            if(null != stdout) {
                System.setOut(new PrintStream(new FileOutputStream(stdout)));
            }
        } catch(Throwable t) {
        }

        try {
            if(null != stderr) {
                System.setErr(new PrintStream(new FileOutputStream(stderr)));
            }
        } catch(Throwable t) {
        }

        try {
            JNIEndpointConnector.setEndpoint(this);
            StartupThread startup = new StartupThread(cmdLine,
                                                      this);
            startup.start();
	    System.out.println("Starting up StartupThread");
            synchronized (this) {
                wait(60*1000);
            }
	    System.out.println("End waiting");
        } catch(Throwable t) {
        }

        if(running) {
	    System.out.println("Running fine ");
            return 1;
        }
	System.out.println("Error - why doesn't run ??");
        return 0;
    }

    public int service(long s, long l)
    {
        if(running) {
            try {
            	JNIConnectionHandler handler = getConnectionHandler();
                handler.processConnection(s, l);

                return 1;
            } catch(Throwable t) {
                // Throwables are not allowed into the native code !!!
            }
        }
        return 0;
    }

    public void shutdown()
    {
        System.out.println("JNI In shutdown");
    }
}

class StartupThread extends Thread {
    String []cmdLine = null;
    JNIEndpoint jniEp = null;

    public StartupThread(String cmdLine,
                         JNIEndpoint jniEp) {
        this.jniEp = jniEp;

        if(null == cmdLine) {
        	this.cmdLine = new String[0];
        } else {
            Vector v = new Vector();
            StringTokenizer st = new StringTokenizer(cmdLine);
            while (st.hasMoreTokens()) {
                v.addElement(st.nextToken());
            }
            this.cmdLine = new String[v.size()];
            v.copyInto(this.cmdLine);
        }
    }

    public void run() {
        boolean failed = true;
        try {
            org.apache.tomcat.startup.Tomcat.main(cmdLine);
            failed = false;
        } catch(Throwable t) {
            t.printStackTrace();
        } finally {
            if(failed) {
		System.out.println("Failed ??");
		jniEp.stopEndpoint();
            }
        }
    }
}
