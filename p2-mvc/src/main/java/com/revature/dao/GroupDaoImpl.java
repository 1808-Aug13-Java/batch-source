package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Group;
import com.revature.util.HibernateUtil;

public class GroupDaoImpl implements GroupDao {

	@Override
	public List<Group> getGroups() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("from Group");
		List<Group> groups = q.list();
		s.close();
		return groups;
	}

	@Override
	public Group getGroupById(int id) {
		Session s = HibernateUtil.getSession();
		Group g = (Group) s.get(Group.class, id);
		s.close();
		return g;
	}

	@Override
	public int createGroup(Group group) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int groupPK = (int) s.save(group);
		tx.commit();
		return groupPK;
	}

}