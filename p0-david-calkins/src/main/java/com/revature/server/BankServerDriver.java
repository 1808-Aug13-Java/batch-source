package com.revature.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;


/**
 * A class with a main that launches a server with a back door. 
 * @author David
 *
 */
public class BankServerDriver {

	
	private static Logger log = Logger.getRootLogger();

    /** The port number to launch the server on. */
    private static final int _PORT = 8888;
    
    
    public static void main(String[] args) {
        //The server socket that listens for and accepts incoming requests.
        ServerSocket serverSocket = null;
        
        //A socket to hold the incoming connection.
        Socket socket = null;
        
        //An object to handle any incoming connection in another thread. 
        ConnectionThread connection = null;
        
        //A boolean to specify if the server should stay up. True by default. 
        boolean keepAlive = true;
        
        //Attempt to open the server listener. Print error and exit if fails.
        try {
            serverSocket = new ServerSocket(_PORT);
        } catch (IOException e) {
            log.error("Failed to launch the server on port " + _PORT);
            
            // Only added to get rid of a "bug" according to solarlint.
            // It thinks i need to close the server, but if we're here, 
            // it means it never opened!
            try {if (serverSocket != null) serverSocket.close();} 
            										catch (IOException e2) {}
            
            System.exit(1);
        }
        
        log.info("Server Listening on Port: " + _PORT);
        
        //Enter a listening state. Keep going until the process is killed,
        // since there isn't a good way to kill it from command line in java.
        //
        //Will implement a remote closer later.
        while (keepAlive) {
            try {
                //Listen for a connection. If we get a connection, pass it 
                // to a connection handler. 
                socket = serverSocket.accept();
                
                //Make a new connection thread to handle the incoming connection
                connection = new ConnectionThread();
                
                //Set this thread to be a background thread. That way, it won't 
                // impede closing the server
//                connection.setDaemon(true);
                
                //Pass the connection socket to our connection handler thread
//                connection.setSocket(socket);
                log.info("");
                log.info("Connection recieved from: " 
                            + socket.getLocalAddress());
                
                //Start the connection thread
//                connection.start();
                
            } catch (IOException e) {
                //If there is a problem accepting a connection, print error
                log.error("Problem with incomming connection: " +
                        e.getMessage());
            }
        } // while
        
        // Attempt to close the server
        try {
        	serverSocket.close();
        } catch (IOException e) {
        	// There is nothing we can do if this fails. 
        }
        
    } // main
    
    
} // Server //














