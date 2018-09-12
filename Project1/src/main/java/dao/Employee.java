package dao;

import java.sql.Date;

public class Employee 
{
	private String firstName;
	private String lastName;
	private String userName;
	private boolean isManager;
	private int id;
	private Date birthdate, hiredate;
	
	private Employee(int i, String fn, String ln, String un, boolean man) 
	{
		id = i;
		firstName = fn;
		lastName = ln;
		userName = un;
		isManager = man;
	}
	
	protected Employee()
	{
		id = -1;
		firstName = lastName = userName = null;
		isManager = false;
	}
	
	protected static Employee getNewEmployee(int i, String fn, String ln, String un)
	{
		return new Employee(i, fn, ln, un, false);
	}
	
	protected static Employee getNewManager(int i, String fn, String ln, String un)
	{
		return new Employee(i, fn, ln, un, true);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	protected void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isManager() {
		return isManager;
	}

	protected void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	
	public int getId()
	{
		return id;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	protected void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getHiredate() {
		return hiredate;
	}

	protected void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
}
