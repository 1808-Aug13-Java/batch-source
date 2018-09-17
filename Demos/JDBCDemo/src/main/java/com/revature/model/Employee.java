package com.revature.model;

import java.sql.Date;

public class Employee {
	private int id;
	private String name;
	private Date birthday;
	private float monthlySalary;
	private Date hiredate;
	private String position;
	private int managerId;
	// private int departmentId;
	private Department department;
	// private int locationId;
	private Location location;
	public Employee() {
		super();
		
	}
	
	public Employee(int id, String name, Date birthday, float monthlySalary, Date hiredate, String position,
			int managerId, Department department, Location location) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.monthlySalary = monthlySalary;
		this.hiredate = hiredate;
		this.position = position;
		this.managerId = managerId;
		this.department = department;
		this.location = location;
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
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((hiredate == null) ? 0 : hiredate.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (hiredate == null) {
			if (other.hiredate != null)
				return false;
		} else if (!hiredate.equals(other.hiredate))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
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
				+ ", hiredate=" + hiredate + ", position=" + position + ", managerId=" + managerId + ", department="
				+ department + ", location=" + location + "]";
	}
}
