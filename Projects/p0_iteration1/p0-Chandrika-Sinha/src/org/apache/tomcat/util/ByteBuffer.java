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


package org.apache.tomcat.util;

import java.io.*;

/**
 * Un-synchronized byte buffer. We have methods to write and read from the
 * buffer, and helpers can convert various data formats.
 *
 * The idea is to minimize the number of buffers and the amount of copy from
 * layer to layer. It's _not_ premature optimization - it's the way things
 * should work.
 *
 * The Request and Response will use several buffers, same for the protocol
 * adapters.
 *
 * Note that the Buffer owns his byte[], while the Chunk is just a light
 * cursor.
 *
 * 
 * @author Costin Manolache
 */
public class ByteBuffer {
    // everything happens inside one thread !!!

    BufferEvent bufferEvent=new BufferEvent(this);

    BufferListener listeners[]=new BufferListener[10];
    int listenerCount=0;
    
    protected static final int DEFAULT_BUFFER_SIZE = 8*1024;
    int defaultBufferSize = DEFAULT_BUFFER_SIZE;
    int bytesWritten = 0;

    /** The buffer
     */
    public byte buf[];

    public int start;
    public int end;
    
    /**
     * The index one greater than the index of the last valid byte in 
     * the buffer. 
     */
    public int count;
    // count==-1 for end of stream
    
    Object parent; // Who "owns" this buffer
    
    /**
     * The current position in the buffer. This is the index of the next 
     * character to be read from the buf. 
     */
    public int pos;

    final static int debug=0;
    
    public ByteBuffer() {
	buf=new byte[defaultBufferSize];
    }

    public void recycle() {
	bytesWritten=0;
	count=0;
	pos=0;
    }

    public Object getParent() {
	return parent;
    }

    public void setParent( Object o ) {
	parent=o;
    }
    
    public void addBufferListener( BufferListener l ) {
	listeners[listenerCount]=l;
	listenerCount++;
    }
    
    public void doWrite( byte buf[], int off, int count ) {
	bufferEvent.setByteBuffer( buf );
	bufferEvent.setOffset( off );
	bufferEvent.setLength( count );
	for( int i=0; i< listenerCount; i++ )
	    listeners[i].bufferFull( bufferEvent );
    }

    public int doRead( byte buf[], int off, int count ) {
	if( debug > 1 ) log("doRead " + off + " " + count);
	bufferEvent.setByteBuffer( buf );
	bufferEvent.setOffset( off );
	bufferEvent.setLength( count );
	for( int i=0; i< listenerCount; i++ )
	    listeners[i].bufferEmpty( bufferEvent );
	return bufferEvent.getLength();
    }
    
    // -------------------- Adding to the buffer -------------------- 
    // Like BufferedOutputStream, without sync

    public void write(int b) throws IOException {
	if( debug>0 ) log( "write(b)");
	if( debug>1 )System.out.write( b );
	if (count >= buf.length) {
	    flush();
	}
	buf[count++] = (byte)b;
	bytesWritten++;
    }

    public void write(byte b[], int off, int len) throws IOException {
	if( debug>0 ) log( "write(b[])" );
	if( debug>1 ) System.out.write( b, off, len );
	int avail=buf.length - count;

	// fit in buffer, great.
	if( len <= avail ) {
	    System.arraycopy(b, off, buf, count, len);
	    count += len;
	    bytesWritten += len;
	    return;
	}

	// Optimization:
	// If len-avail < length ( i.e. after we fill the buffer with
	// what we can, the remaining will fit in the buffer ) we'll just
	// copy the first part, flush, then copy the second part - 1 write
	// and still have some space for more. We'll still have 2 writes, but
	// we write more on the first.

	if (len - avail < buf.length) {
	    /* If the request length exceeds the size of the output buffer,
    	       flush the output buffer and then write the data directly.
	       We can't avoid 2 writes, but we can write more on the second
	    */
	    System.arraycopy(b, off, buf, count, avail);
	    count += avail;
	    flush(); // count will be 0

	    System.arraycopy(b, off+avail, buf, count, len - avail);
	    count+= len - avail;
	    bytesWritten += len - avail;
	    return;
	}

	// len > buf.length + avail
	flush();
	doWrite( b, off, len );

	return;
    }

    public void flush() {
	if( debug > 0 ) log("Flush");
	if (count > 0) {
	    doWrite(buf, 0, count);
	    count = 0;
        }
    }

//     public void close() {
// 	// a write with count=0 will make sure
// 	// the listeners are at least once called.
// 	// we need to add a close() notification
// 	System.out.println("Buffer.close()");
// 	doWrite( buf, 0, count);
// 	count=0;
//     }
    
    // -------------------- Extracting from buffer --------------------
    // Like BufferedInputStream, without sync and without mark
    
    public int read() {
	if( count == -1 ) return -1;
	if (pos >= count) {
	    fill();
	    if (count <0 )
		return -1;
	}
	return buf[pos++] & 0xff;
    }

    public int read(byte b[], int off, int len)
	throws IOException
    {
	if (len == 0) {
	    return 0;
	}
	int n=0; // how many bytes we copy
	int avail = count - pos;

	// copy from our buffer to the result
	if( avail > 0 ) {
	    int cnt = (avail < len) ? avail : len;
	    System.arraycopy(buf, pos, b, off, cnt);
	    pos += cnt;
	    n=cnt;
	}

	if( n >= len ) return n;
	
	// now our buffer is empty
	/* If the requested length is at least as large as the buffer
	   do not bother to copy the bytes into the local buffer.
	*/
	if (len - n >= buf.length ) {
	    return n + doRead(b, off+n, len-n);
	}
	
	// fill the buffer, copy the remaining

	fill();
	avail = count - pos;

	// EOF, we may have copied something from the buff
	if (avail <= 0) return n;
	
	// copy the remaining
	int cnt = (avail < len - n ) ? avail : len - n ;
	System.arraycopy(buf, pos, b, off+n, cnt);
	pos += cnt;
	n+=cnt;

	return n;
    }

    private  void fill() {
	pos=0;
	count = doRead( buf, 0, buf.length );
	if( count==0) count=-1; // end of stream
    }


    // --------------------  BufferedOutputStream compatibility

    public boolean isContentWritten() {
	return bytesWritten!=0;
    }
    
    public void setBufferSize(int size) {
	if( size > buf.length ) {
	    buf=new byte[size];
	}
    }

    public int getBufferSize() {
	return buf.length;
    }


    // -------------------- Utils

    void log( String s ) {
	System.out.println("ByteBuffer: " + s );
    }
    
}
