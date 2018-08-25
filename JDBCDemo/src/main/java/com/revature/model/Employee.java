package com.revature.model;

import java.sql.Date;

public class Employee {

	private int id;
	private String name;
	private Date birthday;
	private float monthlySalary;
	private Date hireDate;
	private String position;
	private int managerId;
	private Location location;
	private Department department;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, String name, Date birthday, float monthlySalary, Date hireDate, String position,
			int managerId, Department department, Location l) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.monthlySalary = monthlySalary;
		this.hireDate = hireDate;
		this.position = position;
		this.managerId = managerId;
		this.department = department;
		this.location = l;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public float getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(float monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Location getlocation() {
		return location;
	}
	public void setlocation(Location location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + department.hashCode();
		result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
		result = prime * result + id;
		result = prime * result + location.hashCode();
		result = prime * result + managerId;
		result = prime * result + Float.floatToIntBits(monthlySalary);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (department != other.department)
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (id != other.id)
			return false;
		if (location != other.location)
			return false;
		if (managerId != other.managerId)
			return false;
		if (Float.floatToIntBits(monthlySalary) != Float.floatToIntBits(other.monthlySalary))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", birthday=" + birthday + ", monthlySalary=" + monthlySalary
				+ ", hireDate=" + hireDate + ", position=" + position + ", managerId=" + managerId + ", department="
				+ department + ", location=" + location + "]";
	}

	
}
