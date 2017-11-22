package com.ss13.dao;

import com.ss13.pojo.User;
import com.ss13.pojo.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresRolesDAO extends PostgresDAO implements RolesDAO {
    public UserRoles getUserrole(User user) {
        String sql = "SELECT * FROM userroles WHERE username = ?";
        PreparedStatement stmt = null;
        UserRoles role = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            role = UserRoles.valueOf(rs.getString("role"));
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
        return role;
    }

    public UserRoles getUserrole(String username) {
        String sql = "SELECT * FROM userroles WHERE username = ?";
        PreparedStatement stmt = null;
        UserRoles role = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            role = UserRoles.valueOf(rs.getString("role"));
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
        return role;
    }

    public void setUserrole(User user, UserRoles role) {
        String sql = "UPDATE userroles SET role = ? WHERE username = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, role.toString());
            stmt.setString(2, user.getUsername());
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

    public void setUserrole(String username, UserRoles role) {
        String sql = "UPDATE userroles SET role = ? WHERE username = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, role.toString());
            stmt.setString(2, username);
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
