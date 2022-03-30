package com.cg.service.impl;

import com.cg.model.User;
import com.cg.repository.UserRepository;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl() {

    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(User user) {
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<User> fillAllActive() {
        return userRepository.findAllByDeletedIsFalse();
    }

    @Override
    public boolean existsByUsernameAndPassword(String username,String password) {
        return userRepository.existsByUsernameAndPassword(username,password);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
