package com.revature.test;

import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		HibernateUtil.getSession().close();
	}

}
