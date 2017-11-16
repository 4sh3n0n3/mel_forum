package com.ss13.dao;

import com.ss13.pojo.Reward;

import java.util.List;

public interface RewardsDAO {
    void create(Reward reward);
    Reward read(int key);
    void update(Reward reward);
    void delete(Reward reward);
    void delete(int key);
    List<Reward> getAll();
    List<Reward> getAllById(int key);
}
