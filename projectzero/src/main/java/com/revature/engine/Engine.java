package com.revature.engine;

import java.sql.*;

import org.apache.log4j.Logger;

import java.math.*;

public class Engine {
	
	final static Logger logger = Logger.getLogger(Engine.class);
	private String url;
	private String conName;
	private String conPassword;

	public Engine() {
		super();
	}
	
	public Engine(String url, String conName, String conPassword) {
		this.url = url;
		this.conName = conName;
		this.conPassword= conPassword;
	}
	
	public void start() {
		
	}
	
	
		
	

}
