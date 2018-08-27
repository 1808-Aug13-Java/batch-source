package com.revature.server.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/** This interface is used for defining behavior to bind variables to a 
 * prepared query for accounts. */
public interface PreparedExecutor<T> {
	
	/** Take a prepared statement and bind the information provided in bindInfo
	 * to the query. Then execute the query and return the resulting object. 
	 * @param ps - The Prepared statement that needs binding. 
	 * @param bindInfo - The information to be used in the binding. */
	public Object bindAndExecute(PreparedStatement ps, T bindInfo) 
														throws SQLException;
} // end of PreparedExecutor
