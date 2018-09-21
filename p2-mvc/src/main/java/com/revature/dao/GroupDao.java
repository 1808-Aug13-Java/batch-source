package com.revature.dao;

import java.util.List;

import com.revature.models.Group;

public interface GroupDao {
	List<Group> getGroups();
	Group getGroupById(int id);
	int createGroup(Group group);
}
