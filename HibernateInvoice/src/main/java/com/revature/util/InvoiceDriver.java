package com.revature.util;

import org.hibernate.Session;

public class InvoiceDriver {

	public static void main(String[] args) {
		Session darthSessions = InvoiceUtil.getSession();
		darthSessions.close();

	}

}
