package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class EmployeeDaoImp implements EmployeeDao
{
	private static Logger loggy = Logger.getRootLogger();
	
	@Override
	public List<Employee> GetEmployeeList(Connection con) 
	{
		return getEmployees(con, "SELECT * FROM PROJ_1_EMPLOYEE");
	}

	@Override
	public List<Employee> GetManagerList(Connection con) 
	{
		return getEmployees(con, "SELECT * FROM PROJ_1_EMPLOYEE WHERE IS_MANAGER > 0");
	}

	@Override
	public List<Employee> GeNonManagerList(Connection con) 
	{
		return getEmployees(con, "SELECT * FROM PROJ_1_EMPLOYEE WHERE IS_MANAGER = 0");
	}
	
	private List<Employee> getEmployees(Connection con, String str)
	{
		List<Employee> ret = new ArrayList<>();
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(str);)
		{
			while(rs.next())
			{
				int id = rs.getInt("EMP_ID");
				String un = rs.getString("USER_NAME");
				String fn = rs.getString("FIRST_NAME");
				String ln = rs.getString("LAST_NAME");
				Date bd = rs.getDate("BIRTHDATE");
				Date hd = rs.getDate("HIREDATE");
				boolean im = rs.getInt("IS_MANAGER") > 0;
				
				Employee emp = null;
				if(im)
				{
					emp = Employee.getNewManager(id, fn, ln, un);
				}
				else
				{
					emp = Employee.getNewEmployee(id, fn, ln, un);
				}
				emp.setBirthdate(bd);
				emp.setHiredate(hd);
				ret.add(emp);
			}
		} 
		catch (SQLException e) 
		{
			loggy.error(e);
		}
		
		
		return ret; 
	}
	
	public int GetAvalableId(Connection con)
	{
		String sql = "{call GET_AVAILABLE_EMP_ID(?)}";
		int ret = -1;
		
		try(CallableStatement cs = con.prepareCall(sql))
		{
			cs.registerOutParameter(1, java.sql.Types.NUMERIC);
			cs.execute();
			ret = cs.getInt(1);
		} 
		catch (SQLException e) 
		{
			loggy.error(e);
			ret = -2;
		}
		return ret;
	}
	
	public Employee GetEmployeeByUserName(String str, Connection con) 
	{
		Employee ret = null;
		try(PreparedStatement st = con.prepareStatement("SELECT * FROM PROJ_1_EMPLOYEE WHERE USER_NAME = ?"))
		{
			st.setString(1, str);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				String un = rs.getString("USER_NAME");
				String fn = rs.getString("FIRST_NAME");
				String ln = rs.getString("LAST_NAME");
				Date bd = rs.getDate("BIRTHDATE");
				Date hd = rs.getDate("HIREDATE");
				int id = rs.getInt("EMP_ID");
				boolean im = rs.getInt("IS_MANAGER") > 0;
				
				if(im)
				{
					ret = Employee.getNewManager(id, fn, ln, un);
				}
				else
				{
					ret = Employee.getNewEmployee(id, fn, ln, un);
				}
				ret.setBirthdate(bd);
				ret.setHiredate(hd);
			}
			rs.close();
		}
		catch(SQLException e)
		{
			loggy.info("Error Retrieving employee");
			loggy.error(e);
		}
		return ret;
	}

	@Override
	public Employee GetEmployeeById(int id, Connection con) 
	{
		Employee ret = null;
		try(PreparedStatement st = con.prepareStatement("SELECT * FROM PROJ_1_EMPLOYEE WHERE EMP_ID = ?"))
		{
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				String un = rs.getString("USER_NAME");
				String fn = rs.getString("FIRST_NAME");
				String ln = rs.getString("LAST_NAME");
				Date bd = rs.getDate("BIRTHDATE");
				Date hd = rs.getDate("HIREDATE");
				boolean im = rs.getInt("IS_MANAGER") > 0;
				
				if(im)
				{
					ret = Employee.getNewManager(id, fn, ln, un);
				}
				else
				{
					ret = Employee.getNewEmployee(id, fn, ln, un);
				}
				ret.setBirthdate(bd);
				ret.setHiredate(hd);
			}
			rs.close();
		}
		catch(SQLException e)
		{
			loggy.info("Error Retrieving employee");
			loggy.error(e);
		}
		return ret;
	}

	@Override
	public boolean checkUserName(String name, Connection con)
	{
		boolean ret = false;
		try(PreparedStatement st = con.prepareStatement("SELECT * FROM PROJ_1_EMPLOYEE WHERE USER_NAME = ?"))
		{
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			ret = rs.next();
			rs.close();
		}
		catch(SQLException e)
		{
			loggy.info("Error checking the name");
			loggy.error(e);
		}
		return ret;
	}

	@Override
	public int createEmployee(Employee emp, String pw, Connection con) 
	{
		String sql = "INSERT INTO PROJ_1_EMPLOYEE VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int ret = 0;
		try (PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setInt(1, emp.getId());
			ps.setString(2, emp.getUserName());
			ps.setString(3, pw);
			ps.setString(4, emp.getFirstName());
			ps.setString(5, emp.getLastName());
			ps.setDate(6, emp.getBirthdate());
			ps.setDate(7, emp.getHiredate());
			ps.setInt(8, (emp.isManager()? 1 : 0));
			ret = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			loggy.info("Error Creating employee");
			loggy.error(e);
		}
		return ret;
	}

	@Override
	public int updateEmployee(Employee emp, Connection con) 
	{
		int ret = 0;
		
		String sql = "UPDATE PROJ_1_EMPLOYEE SET USER_NAME=?, FIRST_NAME=?, LAST_NAME=?, BIRTHDATE=?, HIREDATE=?, IS_MANAGER=? WHERE EMP_ID = ?";
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, emp.getUserName());
			ps.setString(2, emp.getFirstName());
			ps.setString(3, emp.getLastName());
			ps.setDate(4, emp.getBirthdate());
			ps.setDate(5, emp.getHiredate());
			ps.setInt(6, (emp.isManager()? 1 : 0));
			ps.setInt(7, emp.getId());
			ret = ps.executeUpdate();
			ps.close();
		}
		catch(SQLException e)
		{
			loggy.info("Error Updating employee\n");
			loggy.error(e);
		}
		
		return ret;
	}
	
	public int updateEmployee(Employee emp, Connection con, String oldPass, String newPass) 
	{
		int ret = 0;
		
		String sql = "UPDATE PROJ_1_EMPLOYEE SET USER_NAME=?, PASSWORD_=?, FIRST_NAME=?, LAST_NAME=?, BIRTHDATE=?, HIREDATE=?, IS_MANAGER=? WHERE EMP_ID = ? AND PASSWORD_ = ?";
		try(PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, emp.getUserName());
			ps.setString(2, newPass);
			ps.setString(3, emp.getFirstName());
			ps.setString(4, emp.getLastName());
			ps.setDate(5, emp.getBirthdate());
			ps.setDate(6, emp.getHiredate());
			ps.setInt(7, (emp.isManager()? 1 : 0));
			ps.setInt(8, emp.getId());
			ps.setString(9, oldPass);
			ret = ps.executeUpdate();
			ps.close();
		}
		catch(SQLException e)
		{
			loggy.info("Error Updating employee");
			loggy.error(e);
		}
		
		return ret;
	}

	@Override
	public int deleteEmployee(Employee emp, Connection con) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee GetEmployeeByCredentials(String user, String pass, Connection con)
	{
		Employee ret = null;
		try(PreparedStatement st = con.prepareStatement("SELECT * FROM PROJ_1_EMPLOYEE WHERE USER_NAME = ?  AND PASSWORD_ = ?"))
		{
			st.setString(1, user);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				String un = rs.getString("USER_NAME");
				String fn = rs.getString("FIRST_NAME");
				String ln = rs.getString("LAST_NAME");
				Date bd = rs.getDate("BIRTHDATE");
				Date hd = rs.getDate("HIREDATE");
				int id = rs.getInt("EMP_ID");
				boolean im = rs.getInt("IS_MANAGER") > 0;
				
				if(im)
				{
					ret = Employee.getNewManager(id, fn, ln, un);
				}
				else
				{
					ret = Employee.getNewEmployee(id, fn, ln, un);
				}
				
//				System.out.println("Birthday: " + bd);
//				System.out.println("Hiredate: " + hd);
				
				ret.setBirthdate(bd);
				ret.setHiredate(hd);
			}
			rs.close();
		}
		catch(SQLException e)
		{
			loggy.info("Error Retrieving employees");
			loggy.error(e);
		}
		return ret;
	}

}
