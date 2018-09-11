package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.dao.LocationDao;
import com.revature.dao.LocationDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Location;
import com.revature.models.Reimbursement;

public class Tests {
	ReimbursementDao rdi = new ReimbursementDaoImpl();
	Reimbursement r = rdi.getReimbursementByRId(1);
	LocationDao ldi = new LocationDaoImpl();
	Location l = ldi.getLocationById(1);
	
	@Test
	public void createReimTestValid() {
		r.setEmpId(2);
		assertEquals(1, rdi.createReimbursement(r));
	}

	@Test
	public void updateReimTest() {
		r.setDescription("Travel reimbursement $250");
		assertEquals(1, rdi.updateReimbursement(r));
	}
	
	@Test
	public void updateLocationTest() {
		l.setCity("Huge City");
		assertEquals(1, ldi.updateLocation(l));
	}
	
	@Test
	public void createLocationTest() {
		l.setCity("New City");
		l.setState("CA");
		assertEquals(1, ldi.createLocation(l));
	}
}
