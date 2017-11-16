package com.ss13.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class PostgresDAOFactory extends DAOFactory {

    private final String DB_RESOURCE = "java:comp/env/jdbc/ps_13_forum_database";

    public Connection createConnection() throws SQLException {
        InitialContext initContext = null;
        try {
            initContext = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        DataSource ds = null;
        try {
            ds = (DataSource) initContext.lookup(DB_RESOURCE);
        } catch (NamingException e1) {
            e1.printStackTrace();
        }
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return createConnection();
    }

    @Override
    public UserDAO getUserDAO() {
        return new PostgresUserDAO();
    }
}
