package com.ss13.dao;

import com.ss13.pojo.User;
import com.ss13.pojo.UserRoles;

public interface RolesDAO {
    UserRoles getUserrole(User user);
    UserRoles getUserrole(String username);
    void setUserrole(User user, UserRoles role);
    void setUserrole(String username, UserRoles role);
}
