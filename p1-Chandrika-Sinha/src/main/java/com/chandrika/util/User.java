package com.chandrika.util;

public class User {
	private static int userId;
	private static boolean isManager;
	
	public static void setUser(int userId, boolean isManager) {
		User.userId = userId;
		User.isManager = isManager;
	}
	public static void setUserNull() {
		userId = -1;
	}
	
	public static int getUserId() {
		return userId;
	}
	public static boolean isManager() {
		return isManager;
	}
	public static boolean isNull() {
		return userId < 0;
	}
}