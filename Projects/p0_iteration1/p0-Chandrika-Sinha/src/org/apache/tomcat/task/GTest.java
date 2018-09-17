/**
 * 
 *
 */
package org.apache.tomcat.task;

import java.net.*;
import java.io.*;
import java.util.*;
import java.net.*;


// derived from Jsp
public class GTest  {
    String prefix="http://localhost:8080/test";
    String host="localhost";
    int port=8080;
    int debug=0;

    String description="No description";

    String request;
    Hashtable requestHeaders;
    String content;
    
    // Expected response
    boolean magnitude=true;
    boolean exactMatch=false;
    // Match the body against a golden file
    String goldenFile;
    // Match the body against a string
    String responseMatch;
    // the response should include the following headers
    Hashtable expectHeaders;
    // Match request line
    String returnCode="";

    // Actual response
    String responseLine;
    String responseBody;
    Hashtable headers;

    public GTest() {
    }
    
    
    public void setPrefix(String prefix) {
	this.prefix=prefix;
    }
    
    public void setHost(String h) {
	this.host=h;
    }
    
    public void setPort(String portS) {
	this.port=Integer.valueOf( portS).intValue();
    }

    public void setExactMatch(String exact) {
	exactMatch=Boolean.valueOf( exact ).booleanValue();
    }

    /** Set the port as int - different name to avoid confusing ant
     */
    public void setPortInt(int i) {
	this.port=i;
    }

    /** Description should be in <test description=""/>
     */
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description=description;
    }

    public void setContent(String s) {
	this.content=s;
    }

    public void setDebug( String debugS ) {
	debug=Integer.valueOf( debugS).intValue();
    }

    public void setMagnitude( String magnitudeS ) {
        magnitude = Boolean.valueOf(magnitudeS).booleanValue();   
    }

    public void setGoldenFile( String s ) {
	this.goldenFile=s;
    }

    public void setExpectHeaders( String s ) {
	this.expectHeaders=new Hashtable();
	parseHeader( s, expectHeaders );
    }

    public void setResponseMatch( String s ) {
	this.responseMatch=s;
    }

    public void setRequest( String s ) {
	this.request=s;
    }
    
   public void setReturnCode( String s ) {
	this.returnCode=s;
    }

   public void setHeaders( String s ) {
       requestHeaders=new Hashtable();
       parseHeader( s, requestHeaders );
    }

    
    public void execute() throws Exception {
	
	try {
	    dispatch(request, null);

	    boolean result=checkResponse( magnitude );
	    if(result) {
		if(  "No description".equals( description )) {
		    System.out.println("OK " + request );
		}
		else
		    System.out.println("OK " + description + " (" + request + ")");
	    } else {
		if(  "No description".equals( description )) {
		    System.out.println("FAIL " + request );
		} else
		    System.out.println("FAIL " + description + " (" + request + ")" );
	    }
	} catch(Exception ex ) {
	    if(  "No description".equals( description )) {
		System.out.println("FAIL " + request );
	    } else
		System.out.println("FAIL " + description + " (" + request + ")" );
	    ex.printStackTrace();
	}
    }

    private boolean checkResponse(boolean testCondition)
	throws Exception
    {
        boolean responseStatus = true;
	
	// If returnCode doesn't match
	if( request.indexOf( "HTTP/1." ) > -1) {
	    boolean match= ( responseLine!=null && responseLine.indexOf(returnCode) > -1);
	    if( match != testCondition ) {
		responseStatus = false;
		System.out.println("ERROR in: " + request);
		System.out.println("    Expecting: " + returnCode );
		System.out.println("    Got      : " + responseLine);
	    }
	}

	if( expectHeaders != null ) {
	    // Check if we got the expected headers
	    if(headers==null) {
		System.out.println("ERROR no response header, expecting header");
	    }
	    Enumeration e=expectHeaders.keys();
	    while( e.hasMoreElements()) {
		String key=(String)e.nextElement();
		String value=(String)expectHeaders.get(key);
		String respValue=(String)headers.get(key);
		if( respValue==null || respValue.indexOf( value ) <0 ) {
		    System.out.println("ERROR expecting header " + key + ":" +
				       value + " GOT: " + respValue+ " HEADERS(" + headers + ")");
		    
		    return false;
		}
	    }

	}
	
	if( responseMatch != null ) {
	    // check if we got the string we wanted
	    if( responseBody == null ) {
		System.out.println("ERROR: got no response, expecting " + responseMatch);
		return false;
	    }
	    if( responseBody.indexOf( responseMatch ) < 0) {
		responseStatus = false;
		System.out.println("ERROR: expecting match on " + responseMatch);
		System.out.println("GOT: " );
		System.out.println(responseBody );
	    }
	}

	// compare the body
	if( goldenFile==null) return responseStatus;
	// Get the expected result from the "golden" file.
	StringBuffer expResult = getExpectedResult();
	
	// Compare the results and set the status
	boolean cmp=true;
	
	if(exactMatch)
	    cmp=compare(responseBody, expResult.toString() );
	else
	    cmp=compareWeek( responseBody, expResult.toString());
	
	if( cmp  != testCondition ) {
	    responseStatus = false;
	    System.out.println("ERROR (" + cmp + "," + testCondition + ")in : " + request);
	    System.out.println("====================Expecting: ");
	    System.out.println(expResult);
	    System.out.println("====================Got:");
	    System.out.println(responseBody);
	    System.out.println("====================");
	}	    
	
	return responseStatus;
    }
    
    /** Invoke a request, set headers, responseLine, body
     */
    private void dispatch(String request, Hashtable requestHeaders)
	throws Exception
    {
	// XXX headers are ignored
	Socket s = null;

	s = new Socket( host, port);
	InputStream is=	s.getInputStream();

	// Write the request
	s.setSoLinger( true, 1000);
	OutputStream os=s.getOutputStream();
	OutputStreamWriter out=new OutputStreamWriter(os);
	PrintWriter pw = new PrintWriter(out);

	try {
	    pw.println(request);
	    
	    if( content != null) {
		pw.println("Content-Length: " + content.length());
	    }
	    
	    if( request.indexOf( "HTTP/1." ) > -1) 
		pw.println("");
	    
	    if( content != null) {
		pw.print(content);
		// XXX no /n at the end -see HTTP specs!
	    }
	    
	    pw.flush();
	} catch (Exception ex1 ) {
	    System.out.println("Error writing request " + ex1);
	}
	
	try {
	    // http 0.9
	    if( request.indexOf( "HTTP/1." ) > -1) {
		responseLine = read( is );
		
		if( debug>0) System.out.println("RESPONSE: " + responseLine );
		headers=parseHeaders( is );
	    }

	    // else do content matching as well
	    StringBuffer result =  readBody( is );
	    if(result!=null)
		responseBody=result.toString();

	    if(debug>0) System.out.println("BODY: " + responseBody );
	} catch( SocketException ex ) {
	    System.out.println("Socket Exception: " + ex);
	    ex.printStackTrace();
	    s.close();
	    return;
	}
	s.close();
    }

    // Parse a file into a String.
    private StringBuffer getExpectedResult()
	throws IOException
    {
        StringBuffer expResult = new StringBuffer("NONE");

        try {
	    //	    InputStream in = this.getClass().getResourceAsStream(goldenFile);
	    InputStream in = new FileInputStream( goldenFile );
	    return readBody ( in );
        } catch (Exception ex) {
            System.out.println("\tGolden file not found: " + goldenFile);
            return expResult;
        }
    }


    // Compare the actual result and the expected result.
    private boolean compare(String str1, String str2) {
	if ( str1==null || str2==null) return false;
	if ( str1.length() != str2.length() ) {
	    System.out.println("Wrong size " + str1.length() +" " + str2.length() );
	    return false;
	}
	
        for(int i=0; i<str1.length() ; i++ ) {
            if (str1.charAt( i ) != str2.charAt( i ) ) {
		System.out.println("Error at " + i  + " " + str1.charAt(1) +
				   str2.charAt(i));
                return false;
            }
        }
	return true;
    }

    // Compare the actual result and the expected result.
    // Original compare - ignores spaces ( because most
    // golden files are wrong !)
    private boolean compareWeek(String str1, String str2) {
 	if ( str1==null || str2==null) return false;
	
        StringTokenizer st1=new StringTokenizer(str1);
        StringTokenizer st2=new StringTokenizer(str2);

        while (st1.hasMoreTokens() && st2.hasMoreTokens()) {
            String tok1 = st1.nextToken();
            String tok2 = st2.nextToken();
            if (!tok1.equals(tok2)) {
		System.out.println("\tFAIL*** : Rtok1 = " + tok1 
                        + ", Etok2 = " + tok2);
                return false;
            }
        }

        if (st1.hasMoreTokens() || st2.hasMoreTokens()) {
            return false;
        } else {
            return true;
        }
    }


    // XXX return byte [], fix the reading !!!!!
    StringBuffer readBody( InputStream input )
    {
	StringBuffer sb = new StringBuffer();
	while (true) {
	    try {
		int ch = input.read();
		if (ch < 0) {
		    if (sb.length() == 0) {
			return (null);
		    } else {
			break;
		    }
		}
		sb.append((char) ch);
	    } catch(IOException ex ) {
		return sb;
	    }
	}
        return sb;
    }

    // ==================== Code from JSERV !!! ====================
    /**
     * Parse the incoming HTTP request headers, and set the corresponding
     * request properties.
     *
     *
     * @exception IOException if an input/output error occurs
     */
    private Hashtable parseHeaders(InputStream is) throws IOException {
	Hashtable headers=new Hashtable();
	while (true) {
	    // Read the next header line
	    String line = read(is);
	    if ((line == null) || (line.length() < 1)) {
		break;
	    }

	    parseHeader( line, headers);
	    if( debug>0) System.out.println("HEADER: " +line +"X" );

	}

	return headers;
    }

    private void parseHeader(String line, Hashtable headers) {
	// Parse the header name and value
	int colon = line.indexOf(":");
	if (colon < 0) {
	    System.out.println("ERROR: Wrong Header Line: " +  line );
	    return;
	}
	String name = line.substring(0, colon).trim();
	String value = line.substring(colon + 1).trim();

	//	System.out.println("HEADER: " +name + " " + value);
	headers.put(name, value);
    }

    /**
     * Read a line from the specified servlet input stream, and strip off
     * the trailing carriage return and newline (if any).  Return the remaining
     * characters that were read as a string.
     *
     * @returns The line that was read, or <code>null</code> if end of file
     *  was encountered
     *
     * @exception IOException if an input/output error occurred
     */
    private String read(InputStream input) throws IOException {
	// Read the next line from the input stream
	StringBuffer sb = new StringBuffer();
	while (true) {
	    try {
		int ch = input.read();
		//		System.out.println("XXX " + (char)ch );
		if (ch < 0) {
		    if (sb.length() == 0) {
			if(debug>0) System.out.println("Error reading line " + ch + " " + sb.toString() );
			return "";
		    } else {
			break;
		    }
		} else if (ch == '\n') {
		    break;
		}
		sb.append((char) ch);
	    } catch( IOException ex ) {
		System.out.println("Error reading : " + ex );
		debug=1;
		if(debug>0) System.out.println("Partial read: " + sb.toString());
		ex.printStackTrace();
		//break;
	    }
	}

	// Strip any trailing carriage return
	int n = sb.length();
	if ((n > 0) && (sb.charAt(n - 1) == '\r')) {
	    sb.setLength(n - 1);
	}

	// Convert the line to a String and return it
	return (sb.toString());
    }

}
