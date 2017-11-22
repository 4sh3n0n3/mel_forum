package com.ss13.pojo;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private int id;
    private String username;
    private UserRoles role;
    private String passwordHash;
    private Profile profile;
    private String email;

    static Logger log = Logger.getLogger(User.class);

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password, boolean isHashed) throws NoSuchAlgorithmException {
        if (!isHashed) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            this.passwordHash = sb.toString();
        } else {
            this.passwordHash = password;
        }
    }

    public String getEmail() {
        return email;
    }

    public UserRoles getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Profile getProfile() {
        return profile;
    }
}
