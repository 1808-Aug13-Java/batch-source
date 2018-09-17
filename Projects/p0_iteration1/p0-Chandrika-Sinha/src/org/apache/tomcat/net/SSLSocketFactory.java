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
package org.apache.tomcat.net;

import java.io.*;
import java.net.*;

import java.security.KeyStore;

import java.security.Security;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HandshakeCompletedEvent;

/*
  1. Add Jsse's jars into jre/lib/ext
  2. Edit java.security, add
       security.provider.2=com.sun.net.ssl.internal.ssl.Provider
  3. keytool -genkey -alias tomcat -keyalg RSA
     Use "changeit" as password ( this is the default we use )
 */

/**
 * SSL server socket factory. It _requires_ a valid RSA key and
 * JSSE. 
 *
 * @author Harish Prabandham
 * @author Costin Manolache
 * @author Stefan Freyr Stefansson
 */
public class SSLSocketFactory
    extends org.apache.tomcat.net.ServerSocketFactory
{
    private boolean clientAuth = false;
    private SSLServerSocketFactory sslProxy = null;
    
    // defaults
    static String defaultKeystoreFile=System.getProperty("user.home") +
	File.separator + ".keystore";
    static String defaultKeyPass="changeit";

    
    public SSLSocketFactory () {
    }

    public ServerSocket createSocket (int port)
	throws IOException
    {
	if( sslProxy == null ) initProxy();
	ServerSocket socket = 
	    sslProxy.createServerSocket(port);
	initServerSocket(socket);
	return socket;
    }
    
    public ServerSocket createSocket (int port, int backlog)
	throws IOException
    {
	if( sslProxy == null ) initProxy();
	ServerSocket socket = 
	    sslProxy.createServerSocket(port, backlog);
	initServerSocket(socket);
	return socket;
    }
    
    public ServerSocket createSocket (int port, int backlog,
				      InetAddress ifAddress)
	throws IOException
    {	
	if( sslProxy == null ) initProxy();
	ServerSocket socket = 
	    sslProxy.createServerSocket(port, backlog, ifAddress);
	initServerSocket(socket);
	return socket;
    }
    
    
    // -------------------- Internal methods
    /** Read the keystore, init the SSL socket factory
     */
    private void initProxy() throws IOException {
	try {
	    /** Should client authentication be performed?
	     */
	    clientAuth = "true".equals(attributes.get("clientAuth"));

	    /** You should have this in java.security, but
		can't hurt to double check
	    */
	    Security.addProvider (new sun.security.provider.Sun());
	    Security.addProvider (new com.sun.net.ssl.internal.ssl.Provider());

	    String keystoreFile=(String)attributes.get("keystore");
	    if( keystoreFile==null) keystoreFile=defaultKeystoreFile;

	    String keyPass=(String)attributes.get("keypass");
	    if( keyPass==null) keyPass=defaultKeyPass;
	    
	    // You can't use ssl without a server certificate.
	    // Create a KeyStore ( to get server certs )
	    KeyStore kstore = initKeyStore( keystoreFile, keyPass );
	    
	    // Create a SSLContext ( to create the ssl factory )
	    // This is the only way to use server sockets with JSSE 1.0.1
	    com.sun.net.ssl.SSLContext context = 
		com.sun.net.ssl.SSLContext.getInstance("TLS"); //SSL

	    // Key manager will extract the server key
	    com.sun.net.ssl.KeyManagerFactory kmf = 
		com.sun.net.ssl.KeyManagerFactory.getInstance("SunX509");
	    kmf.init( kstore, keyPass.toCharArray());

	    // If client authentication is needed, set up TrustManager
	    com.sun.net.ssl.TrustManager[] tm = null;
	    if( clientAuth) {
		com.sun.net.ssl.TrustManagerFactory tmf =
                    com.sun.net.ssl.TrustManagerFactory.getInstance("SunX509");
		tmf.init(kstore);
		tm = tmf.getTrustManagers();
	    }

	    // init context with the key managers
	    context.init(kmf.getKeyManagers(), tm, null);

	    // create proxy
	    sslProxy = context.getServerSocketFactory();

	    return;
	} catch(Exception e) {
	    if( e instanceof IOException )
		throw (IOException)e;
	    throw new IOException(e.getMessage());
	}
    }

    /** Set server socket properties ( accepted cipher suites, etc)
     */
    private void initServerSocket(ServerSocket ssocket) {
	SSLServerSocket socket=(SSLServerSocket)ssocket;

	// We enable all cipher suites when the socket is
	// connected - XXX make this configurable 
	String cipherSuites[] = socket.getSupportedCipherSuites();
	socket.setEnabledCipherSuites(cipherSuites);

// 	if(clientAuth) {
// 	}
    
	// we don't know if client auth is needed -
	// after parsing the request we may re-handshake
	socket.setNeedClientAuth(clientAuth);
    }

    private KeyStore initKeyStore( String keystoreFile,
				   String keyPass)
	throws IOException
    {
	InputStream istream = null;
	try {
	    KeyStore kstore=KeyStore.getInstance( "JKS" );
	    istream = new FileInputStream(keystoreFile);
	    kstore.load(istream, keyPass.toCharArray());
	    return kstore;
	}
	catch (FileNotFoundException fnfe) {
	    throw fnfe;
	}
	catch (IOException ioe) {
	    throw ioe;	    
	}
	catch(Exception ex) {
	    throw new IOException( "Exception trying to load keystore " + keystoreFile + ": " + ex.getMessage() );
	}
    }

    /** 3.2-specific hack - allow the socket factory to manipulate the
	request. This will be replaced with a clean, interceptor
	based mechanism in 3.3
    */
    public void preProcessRequest( Socket socket,
				   org.apache.tomcat.core.Request reqA )
    {
	try {
	    //Set the client certificate attribute if appropriate
	    javax.net.ssl.SSLSocket sslSocket = (javax.net.ssl.SSLSocket)socket;
	    javax.security.cert.X509Certificate[] certChain = sslSocket.
		getSession().getPeerCertificateChain();
	    
	    if( certChain != null && certChain.length > 0 ) {
		reqA.setAttribute("tomcat.request.X509CertificateChain",
				  certChain);
		reqA.setAttribute("javax.servlet.request.X509Certificate",
				  certChain[0]);
	    }
	} catch( Exception ex ) {
	}
	// this is a  ssl socket
	reqA.setScheme( "https" );
    }
}
