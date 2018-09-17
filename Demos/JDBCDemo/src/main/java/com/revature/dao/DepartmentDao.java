package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Department;

public interface DepartmentDao {
	public List<Department> getDepartments();
	public Department getDepartmentById(int id);
	public Department getDepartmentById(Connection con, int id);
	public int createDepartment(Department department);
	public int updateDepartment(Department department);
	public int deleteDepartment(Department department);
	int deleteDepartmentById(int id);
	public void increaseBudget(int deptId, float increaseAmount);
}
