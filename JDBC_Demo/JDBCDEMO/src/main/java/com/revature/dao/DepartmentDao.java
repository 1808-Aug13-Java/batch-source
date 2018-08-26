package com.revature.dao;

import java.util.List;

import com.revature.model.Department;

public interface DepartmentDao {
//define all of our CRUD operations here
	public List<Department> getDepartments();
	public Department getDepartmentById(int id);
	public int createDepartment(Department department);
	public int updateDepartment(Department department);
	public int deleteDepartmentById(int id);
}
