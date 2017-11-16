package com.ss13.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDAO extends DAOFactory{

    public Connection getConnection()
    {
        try {
            return DAOFactory.getDAOFactory(1).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDAO getUserDAO() {
        return new PostgresUserDAO();
    }
}
