import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.dao.BalanceDao;
import com.revature.dao.BalanceDaoImpl;
import com.revature.model.Balance;

public class BalanceDaoImplTest {
	BalanceDao bdi = new BalanceDaoImpl();

	@Test
	public void getBalancesTest() {
		List<Balance> listBalances = new ArrayList<>();
		Balance b = new Balance("user", new BigDecimal("200.03"));
		listBalances.add(b);
		b = new Balance("josh.pen", new BigDecimal("15.3"));
		listBalances.add(b);
		b = new Balance("user@email.com", new BigDecimal("6000.15"));
		listBalances.add(b);
		
		assertEquals(listBalances, bdi.getBalances());
	}
	
	@Test
	public void getBalanceByIdTest() {
		Balance b = new Balance("user", new BigDecimal("200.03"));
		
		assertEquals(b, bdi.getBalanceById("user"));
	}

}
