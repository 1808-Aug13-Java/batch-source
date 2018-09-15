package com.revature.dao;

import java.util.List;

import com.revature.model.Department;
import com.revature.model.Employee;

public interface DepartmentDao {
	
	public List<Department> getDepartments();
	public Employee getEmployeeById(int id);
	public int createDepartment(Department department);
	public int updateDepartment(Department department);
	public int deletebDepartmentById(int id);
	public Department getDepartmentById(int id);
	public void increaseBudget(int deptId, int increaseAmount);
	
}
