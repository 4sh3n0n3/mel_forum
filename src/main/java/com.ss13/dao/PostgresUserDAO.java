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

    /**
     * @param user Ссылка на профиль, роль и награды пользователя создаётся в момент записи
     *             пользователя в БД. (Хранимые процедуры СУБД, триггеры)
     */
    public void create(User user) throws SQLException{
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement("INSERT INTO users "
                + "(username, password, email) VALUES(?, ?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPasswordHash());
        stmt.setString(3, user.getEmail());

        stmt.execute();
        stmt.close();
        conn.close();
    }

    public User read(int key) throws SQLException{
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        UserRoles role = (new PostgresRolesDAO()).getUserrole(rs.getString("username"));
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
        try {
            user.setPassword(rs.getString("password"), false);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setUuid(rs.getString("uuid"));
        user.setProfile((new PostgresProfileDAO()).read(rs.getInt("profile")));
        stmt.close();
        conn.close();
        return user;
    }

    public void update(User user) throws SQLException{
        String sql = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPasswordHash());
        stmt.setString(3, user.getEmail());
        stmt.setInt(4, user.getId());

        stmt.execute();
        stmt.close();
        conn.close();
    }

    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, user.getId());

        stmt.execute();
        stmt.close();
        conn.close();
    }

    public void delete(int key)  throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, key);

        stmt.execute();
        stmt.close();
        conn.close();
    }

    public User read(String username, String password) throws SQLException  {
        String sql = "SELECT * FROM users WHERE username = ?, password = ?";
        User user = new User();
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        UserRoles role = (new PostgresRolesDAO()).getUserrole(username);
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
        try {
            user.setPassword(rs.getString("password"), false);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setUuid(rs.getString("uuid"));
        user.setProfile((new PostgresProfileDAO()).read(rs.getInt("profile")));
        stmt.close();
        conn.close();
        return user;
    }

    public void setUUID(String login, String uuid) throws SQLException {
        String sql = "UPDATE users SET uuid=? WHERE username=?";
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, uuid);
        stmt.setString(2, login);
        stmt.execute();
    }

    public void deleteUUID(String login) throws SQLException {
        setUUID(login, User.EMPTY_UUID);
    }

    public User readByUUID(String uuid) throws SQLException {
        String sql = "SELECT * FROM users WHERE uuid = ?";
        User user = new User();
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, uuid);
        ResultSet rs = stmt.executeQuery();

        UserRoles role = (new PostgresRolesDAO()).getUserrole(rs.getString("username"));
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
        try {
            user.setPassword(rs.getString("password"), false);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setUuid(rs.getString("uuid"));
        user.setProfile((new PostgresProfileDAO()).read(rs.getInt("profile")));
        stmt.close();
        conn.close();

        return user;
    }

    public User read(String username) throws SQLException  {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = new User();
        PreparedStatement stmt = null;
        Connection conn = getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        UserRoles role = (new PostgresRolesDAO()).getUserrole(username);
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
        try {
            user.setPassword(rs.getString("password"), false);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setUuid(rs.getString("uuid"));
        user.setProfile((new PostgresProfileDAO()).read(rs.getInt("profile")));
        stmt.close();
        conn.close();
        return user;
    }

    public List<User> getAll() throws SQLException  {
        return null;
    }
}
