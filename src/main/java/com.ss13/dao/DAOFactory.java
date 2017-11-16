package com.ss13.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Фабрика объектов для работы с базой данных
 */
public abstract class DAOFactory {

    public static final int POSTGRES = 1;

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case POSTGRES:
                return new PostgresDAOFactory();
            default           :
                return null;
        }
    }
    /**
     * Возвращает подключение к базе данных
     */
    public abstract Connection getConnection() throws SQLException;
    public abstract UserDAO getUserDAO();
}
