package com.reimbursement.dao;

import java.sql.Connection;

public class ReimbursementDAOConn {
    public static ReimbursementDAO getInstance(Connection connection) {
        return new ReimbursementDAOImpl(connection);
    }
}

