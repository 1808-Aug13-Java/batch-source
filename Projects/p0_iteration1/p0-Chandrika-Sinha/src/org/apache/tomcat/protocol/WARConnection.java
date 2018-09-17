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
 * [Additional notices, if required by prior licensing conditions]
 *
 */ 


package org.apache.tomcat.protocol;

import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.FileNameMap;

/**
 *
 * @author James Duncan Davidson [duncan@eng.sun.com]
 * @author James Todd [gonzo@eng.sun.com]
 */

public class WARConnection
extends URLConnection {
    private URL warURL = null;
    private String path = null;
    private byte[] buffer = null;
    private long size = 0;
    private long time = 0;

    public WARConnection (URL url)
    throws MalformedURLException {
        super(url);

	parseSpecs(url);
    }

    public void connect()
    throws IOException {
	// XXX
	// create and cache hashtable of ZipFiles
        // ZipFile.getEntry(String entry)

	ZipInputStream zis = new ZipInputStream(warURL.openStream());
	ZipEntry ze = null;

	while ((ze = zis.getNextEntry()) != null) {
	    if (path.equals(ze.getName())) {
                this.size = ze.getSize();
                this.time = ze.getTime();

	        int bufferSize = 128;
		byte[] buffer = new byte[bufferSize];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int length = 0;
		int totalLength = 0;

	        out:

		while ((length = zis.read(buffer)) >= 0) {
		    baos.write(buffer, 0, length);
		    totalLength += length;
		}

		this.buffer = baos.toByteArray();

                if (this.size < 0) {
                    this.size = totalLength;
                }

                break;
	    }
	}

	zis.close();
    }

    public InputStream getInputStream()
    throws IOException {
        if (! super.connected) {
	    connect();
	}

        if (this.buffer != null) {
	    return new ByteArrayInputStream(this.buffer);
	} else {
	    String msg = "error";

	    throw new IOException(msg);
	}
    }

    public String getContentType() {
        // XXX
        // hmmm .. what about the mimeTypes associated with
        // this context
        FileNameMap map = URLConnection.getFileNameMap();
	String type = null;

	// the following try/catch will guard against a
	// bug in some versions of URLConnection.java
	try {

	    // if there is a nameMapper, use it
            type=map.getContentTypeFor(path);

	} catch (NullPointerException e) {

	    // otherwise use a poor approximation which does the job
	    if (path!=null && path.length()>=1) {
		String name=path.toLowerCase();
		if (name.endsWith(".xml"))
		    type="application/xml";
		else if (name.endsWith(".dtd"))
		    type="text/dtd";
		else
		    type="application/unknown";
	    }

	}

	return type;
    }

    public int getContentLength() {
        Long l = new Long(this.size);

        return l.intValue();
    }

    public long getLastModified() {
        return this.time;
    }

    private void parseSpecs(URL url)
    throws MalformedURLException {
        String spec = url.getFile();

        if (spec.startsWith("/")) {
            spec = spec.substring(1);
        }

	int separator = spec.indexOf('!');

	if (separator == -1) {
	    String msg = "bad url: " + url;

	    throw new MalformedURLException(msg);
	}

	this.warURL = new URL(spec.substring(0, separator++));

	if (++separator != spec.length()) {
	    this.path = spec.substring(separator, spec.length());
	}
    }
}
