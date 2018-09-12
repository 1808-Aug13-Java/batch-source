package com.revature.dao;

/** This class is for a convenient way to define a condition within an SQL 
 * statement. A condition has three parts, the left-hand argument, right-hand 
 * argument, and the condition operator. 
 * <br>
 * This object assumes that the right-hand argument might be user input, and 
 * therefore should be bound instead of concatenated. The left-hand argument
 * if assumed to be a column, or some pre-defined condition to test against.  */
public class SQLCondition {
	
	/** The left-hand argument. Assumed to be pre-defined, not in need of binding. */
	private String leftHand = null;
	
	/** The right-hand argument. This will be bound to the query before execution. */
	private Object rightHand = null;
	
	/** The condition operator of the condition object. */
	private String condition = null;

	public SQLCondition(String leftHand, Object rightHand, String condition) {
		super();
		this.leftHand = leftHand;
		this.rightHand = rightHand;
		this.condition = condition;
	}

	public String getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(String leftHand) {
		this.leftHand = leftHand;
	}

	public Object getRightHand() {
		return rightHand;
	}

	public void setRightHand(Object rightHand) {
		this.rightHand = rightHand;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return leftHand + " " + condition + " " + rightHand;
	}
	
	
}
