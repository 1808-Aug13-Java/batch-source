package ProjectDao;

		import java.sql.Blob;
		import java.io.IOException;
		import java.sql.Connection;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.util.ArrayList;
		import java.util.List;
		

		import ProjectModel.UserBanking;
		import ProjectUtil.ConnectionUtill;

		/**
		 * Implementation for the BookDAO, responsible for querying the database for Book objects.
		 */
		public class UserBankingDaoImpl implements UserBankingDao {

			Connection connection = null;	
			PreparedStatement stmt = null;
			/*------------------------------------------------------------------------------------------------*/
			
			
			@Override
			public List<UserBanking> getAllUserBanking() {
				
				List<UserBanking> userAccounts = new ArrayList<>();

				try {
					connection = ConnectionUtill.getConnection();	
					String sql = "SELECT * FROM USER_BANKING";		
					stmt = connection.prepareStatement(sql);	
					
					ResultSet rs = stmt.executeQuery();			

					
					while (rs.next()) {
					
						UserBanking userbanking = new UserBanking();

						userbanking.setBankingId(rs.getInt("BANKING_ID"));
						userbanking.setUserName(rs.getString("USERNAME"));
						userbanking.setUserBalance(rs.getDouble("USER_BALANCE"));
						
						userAccounts.add(userbanking);
						
					}
					
					rs.close();
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				} finally {
					closeResources();
				}
				
				return userAccounts;
			}

			
			/*------------------------------------------------------------------------------------------------*/

			
			@Override
			public List<UserBanking> getUsername(String username) {
				
				List<UserBanking> userAccounts = new ArrayList<>();

				try {
					connection = ConnectionUtill.getConnection();
					String sql = "SELECT * FROM USER_BANKING WHERE USERNAME LIKE ?";	// Note the ? in the query
					stmt = connection.prepareStatement(sql);
					
					stmt.setString(1, "%" + username + "%");	
					
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						UserBanking userbanking = new UserBanking();

						userbanking.setBankingId(rs.getInt("BANKING_ID"));
						userbanking.setUserName(rs.getString("USERNAME"));
						userbanking.setUserBalance(rs.getDouble("USER_BALANCE"));
						
						userAccounts.add(userbanking);
					}
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				} finally {
					closeResources();
				}
				
				return userAccounts;
			}

			
			/*------------------------------------------------------------------------------------------------*/

			
			@Override
			public List<UserBanking> getUserBalance(double userbalance) {
				List<UserBanking> userAccounts = new ArrayList<>();

				try {
					connection = ConnectionUtill.getConnection();
					String sql = "SELECT * FROM USER_BANKING WHERE USER_BALANCE  = ?";
					stmt = connection.prepareStatement(sql);
					
					stmt.setDouble(1, userbalance);;
					
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) {
						UserBanking userbanking = new UserBanking();
						
						userbanking.setBankingId(rs.getInt("BANKING_ID"));
						userbanking.setUserName(rs.getString("USERNAME"));
						userbanking.setUserBalance(rs.getDouble("USER_BALANCE"));
						
						userAccounts.add(userbanking);
					}
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				} finally {
					closeResources();
				}
				
				return userAccounts;
			}

			
			/*------------------------------------------------------------------------------------------------*/

			
			@Override
			public UserBanking getUserBankingByName(String name) {
				UserBanking userbanking = new UserBanking();
				try {
					connection = ConnectionUtill.getConnection();
					String sql = "SELECT * FROM USER_BANKING WHERE USERNAME = ?";
					stmt = connection.prepareStatement(sql);
					stmt.setString(1, name);
					
					ResultSet rs = stmt.executeQuery();

					if (rs.next()) {
						userbanking.setUserName(rs.getString("USERNAME"));
						return userbanking;
					}
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				} finally {
					closeResources();
				}
				return userbanking;
			}


			/*------------------------------------------------------------------------------------------------*/

			
			@Override
			public boolean addUserBanking(UserBanking userbanking) {
				
				try {
					connection = ConnectionUtill.getConnection();
					String sql = "INSERT INTO USER_BANKING VALUES (?, ?, ?)"; // Were using a lot of ?'s here...
					stmt = connection.prepareStatement(sql);
				
					stmt.setInt(1, userbanking.getBankingId());
					stmt.setString(2, userbanking.getUserName());
					stmt.setDouble(3, userbanking.getUserBalance());
					
					if (stmt.executeUpdate() != 0)
						return true;
					else
						return false;
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
					return false;
				} finally {
					closeResources();
				}
			}

			
			/*------------------------------------------------------------------------------------------------*/

			
			@Override
			public boolean updateUserBanking(UserBanking userbanking) {
				try {
					connection = ConnectionUtill.getConnection();
					String sql = "UPDATE USER_BANKING SET USER_BALANCE=? WHERE USERNAME=?";
					stmt = connection.prepareStatement(sql);
					
					stmt.setString(2, userbanking.getUserName());
					stmt.setDouble(1, userbanking.getUserBalance());
				
					System.out.println(stmt);
					
					if (stmt.executeUpdate() != 0)
						return true;
					else
						return false;
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
					return false;
				} finally {
					closeResources();
				}
				
			}

			
			/*------------------------------------------------------------------------------------------------*/

			
			@Override
			public boolean deleteUserBankingById(String id) {
				try {
					connection = ConnectionUtill.getConnection();
					String sql = "DELETE USER_BANKING WHERE BANKING_ID=?";
					stmt = connection.prepareStatement(sql);
					
					stmt.setString(1, id);

					if (stmt.executeUpdate() != 0)
						return true;
					else
						return false;
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
					return false;
				} finally {
					closeResources();
				}
			}

			
			/*------------------------------------------------------------------------------------------------*/

			private void closeResources() {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException e) {
					System.out.println("cannot close statement!");
					e.printStackTrace();
				}
				
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					System.out.println("cannot close connection!");
					e.printStackTrace();
				}
			}
			
		}