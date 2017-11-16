package com.ss13.dao;

import com.ss13.pojo.Reward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PostgresRewardsDAO extends PostgresDAO implements RewardsDAO {
    public void create(Reward reward) {
        String sql = "INSERT INTO rewards (name) VALUES (?)";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reward.getName());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Reward read(int key) {
        String sql = "SELECT * FROM rewards WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        Reward reward = new Reward();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            reward.setId(rs.getInt("id"));
            reward.setName(rs.getString("name"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reward;
    }

    public void update(Reward reward) {
        String sql = "UPDATE rewards SET name = ? WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reward.getName());
            stmt.setInt(2, reward.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Reward reward) {
        String sql = "DELETE FROM rewards WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, reward.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int key) {
        String sql = "DELETE FROM rewards WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        Reward reward = new Reward();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, key);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Reward> getAllById(int key) {
        String sql = "SELECT rewards.id AS id, rewards.name AS name"
                + "FROM user_profile_to_rewards INNER JOIN rewards AS result"
                + "WHERE result.profile_id = ?";
        PreparedStatement stmt = null;
        LinkedList<Reward> list = new LinkedList<Reward>();
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reward reward = new Reward();
                reward.setId(rs.getInt("id"));
                reward.setName(rs.getString("name"));
                list.add(reward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public List<Reward> getAll() {
        String sql = "SELECT * FROM rewards";

        PreparedStatement stmt = null;
        LinkedList<Reward> list = new LinkedList<Reward>();
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reward reward = new Reward();
                reward.setId(rs.getInt("id"));
                reward.setName(rs.getString("name"));
                list.add(reward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
