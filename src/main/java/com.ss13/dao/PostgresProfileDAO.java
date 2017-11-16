package com.ss13.dao;

import com.ss13.pojo.Profile;
import com.ss13.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresProfileDAO extends PostgresDAO implements ProfileDAO {

    public Profile read(int key) {
        String sql = "SELECT * FROM profile WHERE id = ?";
        PreparedStatement stmt = null;
        Profile profile = new Profile();
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            profile.setId(rs.getInt("id"));
            profile.setAvatar(rs.getString("avatar"));
            profile.setFirstName(rs.getString("name"));
            profile.setLastName(rs.getString("surname"));
            profile.setRating(rs.getInt("rating"));
            profile.setRegistrationDate(rs.getDate("reg_date"));
            profile.setAboutSelf(rs.getString("about_self"));
            profile.setRewards_list((new PostgresRewardsDAO()).getAllById(rs.getInt("rewards")));
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
        return profile;
    }

    public Profile read(User user) {
        String sql = "SELECT * FROM profile WHERE id = ?";
        PreparedStatement stmt = null;
        Profile profile = new Profile();
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            profile.setId(rs.getInt("id"));
            profile.setAvatar(rs.getString("avatar"));
            profile.setFirstName(rs.getString("name"));
            profile.setLastName(rs.getString("surname"));
            profile.setRating(rs.getInt("rating"));
            profile.setRegistrationDate(rs.getDate("reg_date"));
            profile.setAboutSelf(rs.getString("about_self"));
            profile.setRewards_list((new PostgresRewardsDAO()).getAllById(rs.getInt("rewards")));
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
        return profile;
    }

    public void update(Profile profile) {
        String sql = "UPDATE profile SET avatar = ?, name = ?, surname = ?, rating = ?, about_self = ?"
                + "WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, profile.getAvatar());
            stmt.setString(2, profile.getFirstName());
            stmt.setString(3, profile.getLastName());
            stmt.setInt(4, profile.getRating());
            stmt.setString(5, profile.getAboutSelf());
            stmt.setInt(6, profile.getId());

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
}
