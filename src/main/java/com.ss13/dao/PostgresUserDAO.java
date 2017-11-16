package com.ss13.dao;

import com.ss13.pojo.User;
import com.ss13.pojo.UserRoles;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostgresUserDAO extends PostgresDAO implements UserDAO {
    public void create(User user) {
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement("INSERT INTO users "
                    + "(username, password) VALUES(?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());

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

    public User read(int key) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = new User();
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            UserRoles role = UserRoles.valueOf(rs.getString("role"));
            switch (role) {
                case USER:
                    user.setRole(UserRoles.USER);
                    break;
                case MODERATOR:
                    user.setRole(UserRoles.MODERATOR);
                    break;
                default:
                    user.setRole(UserRoles.USER);
                    break;
            }
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"), false);
            user.setUsername(rs.getString("username"));
            user.setProfile((new PostgresProfileDAO()).read(rs.getInt("profile")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void update(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getRole().toString());
            stmt.setInt(4, user.getId());

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

    public void delete(User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());

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
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
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

    public User read(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?, password = ?";
        User user = new User();
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            UserRoles role = UserRoles.valueOf(rs.getString("role"));
            switch (role) {
                case USER:
                    user.setRole(UserRoles.USER);
                    break;
                case MODERATOR:
                    user.setRole(UserRoles.MODERATOR);
                    break;
                default:
                    user.setRole(UserRoles.USER);
                    break;
            }
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"), false);
            user.setUsername(rs.getString("username"));
            user.setProfile((new PostgresProfileDAO()).read(rs.getInt("profile")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public User read(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = new User();
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            UserRoles role = UserRoles.valueOf(rs.getString("role"));
            switch (role) {
                case USER:
                    user.setRole(UserRoles.USER);
                    break;
                case MODERATOR:
                    user.setRole(UserRoles.MODERATOR);
                    break;
                default:
                    user.setRole(UserRoles.USER);
                    break;
            }
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"), false);
            user.setUsername(rs.getString("username"));
            user.setProfile((new PostgresProfileDAO()).read(rs.getInt("profile")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public List<User> getAll() {
        return null;
    }
}
