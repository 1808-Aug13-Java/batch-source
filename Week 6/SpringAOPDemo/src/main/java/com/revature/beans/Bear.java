package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Bear { 
	private boolean isFull = true;
	private boolean isAwake= true;
	private static boolean isWinter=false;
	public Bear() {
		super();
	}
	public Bear(boolean ifFull, boolean isAwake) {
		super();
		this.isFull = ifFull;
		this.isAwake = isAwake;
	}
	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean ifFull) {
		this.isFull = ifFull;
	}
	@Override
	public String toString() {
		return "Bear [isFull=" + isFull + ", isAwake=" + isAwake + "]";
	}
	public boolean isAwake() {
		return isAwake;
	}
	public void setAwake(boolean isAwake) {
		this.isAwake = isAwake;
	}
	public static boolean isWinter() {
		return isWinter;
	}
	public static void setWinter(boolean isWinter) {
		Bear.isWinter = isWinter;
	}
	public void wakeUpBear() {
		this.isAwake=true;
	}
	public void bearSleeps() {
		this.isAwake=false;
	}
	public void bearHibernates() {
		if (Bear.isWinter) {
			this.bearSleeps();
		}else {
//			System.out.println("bears hibernate in the winter");
			throw new RuntimeException ("bears hibernate in the winter");
		}
	}

}
