package com.revature.daos;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.revature.models.Bear;
import com.revature.models.Cave;
import com.revature.util.HibernateUtil;

public class BearDaoImpl implements BearDao {

	@Override
	public List<Bear> getBears() {
		Session s=HibernateUtil.getSession();
		String hql="from Bear";
		Query q= s.createQuery(hql);
		List<Bear> bears=q.list();
		s.close();
		
		return bears;
	}
	public Bear getBearById (int id) {
		Session s = HibernateUtil.getSession();
		Bear b=(Bear) s.load(Bear.class, id);
		System.out.println(b);
		s.close();
		
		return b;
	}
	
	public int createBear(Bear b) {
		Session s= HibernateUtil.getSession();
		Transaction tx= s.beginTransaction();
		int bPK=(int) s.save(b);
		tx.commit();
		s.close();
		return bPK;
	}
	public List<Bear> getBearsByCave(int caveId){
		Session s= HibernateUtil.getSession();
		String hql="from Bear where cave = :caveVar";
		Query q =s.createQuery(hql);
		q.setInteger("caveVar",caveId);
		List <Bear> bears = q.list();
		return bears;
		
		
	}
	
	public List<Bear> getBearsByName(String name){
		Session s= HibernateUtil.getSession(); 
		Query q =s.createSQLQuery("SELECT * FROM BEAR WHERE BEAR_NAME= ? ").addEntity(Bear.class);
		q.setString(0, name);
		List<Bear> bears=q.list();
		return bears;
				
	}
	
	public List<Bear> getSBears(){
		Session s= HibernateUtil.getSession(); 
		Criteria c= s.createCriteria(Bear.class);
		c.add(Restrictions.like("name", "S%*")).addOrder(Order.asc("name"));
		List<Bear> bears =c.list();
		
		return bears;
	}
	
	public int getBearCount() {
		Session s= HibernateUtil.getSession();
		Criteria c= s.createCriteria(Bear.class);
		c.setProjection(Projections.rowCount()); //for dealing with aggregate values
		List rows= c.list();
		s.close();
		return (Integer) rows.get(0);
		
	}
	@Override
	public List<Bear> get90andGBears() {
		
		Session s= HibernateUtil.getSession();
		Criteria c= s.createCriteria(Bear.class);
		Criterion cr1=Restrictions.between("birthday",Date.valueOf("1990-01-01"),Date.valueOf("1990-12-31"));
		Criterion cr2= Restrictions.like("name","G%");
		c.add(Restrictions.and(cr1,cr2));
		c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List <Bear> bears= c.list();
				s.close();
				
		return bears;
	}
	@Override
	
	public List<Bear> get90orGBears() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
