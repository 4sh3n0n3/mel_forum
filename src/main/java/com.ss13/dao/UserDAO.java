package com.ss13.dao;

import com.ss13.pojo.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Создание/удаление пользователя. (подумать как "солить" пароль)
 */
public interface UserDAO {
    void create(User user) throws SQLException;
    User read(int key) throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws SQLException;
    void delete(int key) throws SQLException;
    List<User> getAll() throws SQLException;
    User readByUUID(String uuid) throws SQLException;
    User read(String login, String password) throws SQLException;
    User read(String login) throws SQLException;
    void setUUID(String login,String uuid) throws SQLException;
    void deleteUUID(String login) throws SQLException;
}
