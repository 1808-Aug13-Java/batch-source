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
 */
package org.apache.tomcat.logging;

import java.io.PrintStream;

/**
 * Wrapper for Logger. It has a preferred log name to write to; if it
 * can't find a log with that name, it outputs to either System.out or
 * System.err depending on the message's warning level.  Also prepends
 * a descriptive name to each message (usually the toString() of the
 * calling object), so it's easier to identify the source.<p>
 *
 * Intended for use by client classes to make it easy to do reliable,
 * consistent logging behavior, even if you don't necessarily have a
 * context, or if you haven't registered any log files yet, or if
 * you're in a non-Tomcat application.  Not intended to supplant
 * Logger, but to allow client objects a consistent bit of code that
 * prepares log messages before they reach logger (and does the right
 * thing if there is no logger).
 *
 * @author Alex Chaffee [alex@jguru.com]
 **/

public class LogHelper {

    private String logname;
    private Object prepend;
    private Logger logger;

    /**
     * @param logname name of log to use
     * @param prepend string, or an object to call toString() on, to prepend to each message
     **/
    public LogHelper(String logname, Object prepend) 
    {
	this.logname = logname;
	this.prepend = prepend;
    }

    public Logger getLogger() {
	return logger;
    }

    /**
     * Set a logger explicitly. (Note that setLogger(null) will not
     * necessarily redirect log output to System.out; if there is a
     * logger named logname it will fall back to using it. )
     **/
    public void setLogger(Logger logger) {
	this.logger = logger;
    }

    /**
     * Logs the message with level INFORMATION
     **/
    public void log(String msg) 
    {
	log(msg, null, Logger.INFORMATION);
    }

    /**
     * Logs the Throwable with level ERROR (assumes an exception is
     * trouble; if it's not, use log(msg, t, level))
     **/
    public void log(String msg, Throwable t) 
    {
	log(msg, t, Logger.ERROR);
    }

    /**
     * Logs the message and Throwable to its logger or, if logger not
     * found, to System.err or System.out (depending on level).
     **/
    public void log(String msg, Throwable t, int level)
    {
	if (prepend != null) {
	    msg = prepend.toString() + ": " + msg;
	}

	if (logger == null) {
	    if (logname != null)
		logger = Logger.getLogger(logname);
	}

	if (logger == null) {
	    // if it's still null, send to stderr or stdout
	    // ??? maybe should just send everything to one or the other
	    PrintStream out = (level <= Logger.WARNING) ? System.err : System.out;
	    out.println(msg);
	    if (t != null)
		out.print(TomcatLogger.throwableToString(t));
	}
	else {
	    logger.log(msg, t, level);
	}
    }
}
