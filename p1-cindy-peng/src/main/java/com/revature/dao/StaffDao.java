package com.revature.dao;

import java.util.List;

import com.revature.models.Staff;

public interface StaffDao {
	public List<Staff> listStaffMembers(String sql);
	public Staff getStaffByUserName(String userN);
	public int updateStaffProfile(Staff s);
}
