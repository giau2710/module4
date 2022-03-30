package com.cg.service;

import com.cg.model.User;

import java.util.List;

public interface UserService extends IGeneralService<User> {
    List<User> fillAllActive();

    boolean existsByUsernameAndPassword(String username,String password);

    User findByUsername(String username);
}
