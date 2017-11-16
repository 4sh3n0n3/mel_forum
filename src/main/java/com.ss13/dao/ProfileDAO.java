package com.ss13.dao;

import com.ss13.pojo.Profile;
import com.ss13.pojo.User;

/**
 * Методы для ОБНОВЛЕНИЯ профайла. Создание и удаление профайла
 * уже привязаны к созданию/удалению пользователя
 */
public interface ProfileDAO {
    Profile read(int key);
    Profile read(User user);
    void update(Profile profile);
}
