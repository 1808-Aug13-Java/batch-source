import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.reim.dao.EmployeeDao;
import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.dao.ManagerDao;
import com.revature.reim.dao.ManagerDaoImpl;

public class EmployeeDaoImpleTest {

	@Test
	public void incorrectLogInTest() {
		EmployeeDao ed= new EmployeeDaoImpl();
		int result = ed.logIn("tedison@gmail.com", "sdff");
		assertEquals(0,result);
	}
	@Test
	public void correctLogInTest() {
		EmployeeDao ed= new EmployeeDaoImpl();
		int result = ed.logIn("tedison@gmail.com", "asdf");
		assertEquals(1,result);
	}
	
	@Test  
	public void createEmployee() {
		ManagerDao ed= new ManagerDaoImpl();
		int result=ed.createEmployee("Peter", "Parker", "parker@gmail.com", "spidey", 1);
		assertEquals(0,result);
	}
	
	
	@Test
	public void Update() {
		ManagerDao ed= new ManagerDaoImpl();
		int result = ed.resolveRequest(7, 6, 1);
		assertEquals(1,result);
	}
	@Test
	public void badUpdate() {
		ManagerDao ed= new ManagerDaoImpl();
		int result = ed.resolveRequest(60, 6, 1);
		assertEquals(0,result);
	}
	
	@Test
	public void sameEmployee() {
		ManagerDao ed= new ManagerDaoImpl();
		int result = ed.createEmployee("Peter", "Parker", "parker@gmail.com", "adf", 1);
		assertEquals(0,result);
	}
	
	
	
	
}

