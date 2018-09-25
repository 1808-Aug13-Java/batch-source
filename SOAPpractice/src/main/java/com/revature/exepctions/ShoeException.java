package com.revature.exepctions;

public class ShoeException extends Exception {
		
		private String message = "too many SHOES!";
		
		public ShoeException() {
			super();
		}
		
		public ShoeException(String message) {
			super(message);
		}

}
