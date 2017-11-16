package com.ss13.dao;

import com.ss13.pojo.User;

import java.util.List;

/**
 * Создание/удаление пользователя. (подумать как "солить" пароль)
 */
public interface UserDAO {
    void create(User user);
    User read(int key);
    void update(User user);
    void delete(User user);
    void delete(int key);
    List<User> getAll();
    User read(String login, String password);
    User read(String login);
}
