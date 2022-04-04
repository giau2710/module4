package com.cg.service.impl;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.repository.UserRepository;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<User> findAllActive() {
        return userRepository.findAllByDeletedIsFalse();
    }

    @Override
    public List<UserDTO> findAllUserDTOByDeletedIsFalse() {
        return userRepository.findAllUserDTOByDeletedIsFalse();
    }

    @Override
    public Optional<UserDTO> findUserDTOById(Long id) {
        return userRepository.findUserDTOById(id);
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


}
