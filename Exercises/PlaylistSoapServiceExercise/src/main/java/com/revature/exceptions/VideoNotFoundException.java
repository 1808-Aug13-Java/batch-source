package com.revature.exceptions;

public class VideoNotFoundException extends Exception {
	private String message = "No video with that title exists in the playlist";
	
	public VideoNotFoundException() {
		
	}
	
	public VideoNotFoundException(String message) {
		super(message);
	}
}
