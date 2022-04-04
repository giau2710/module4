package com.cg.service;

import com.cg.model.Tour;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService extends IGeneralService<User> {
    List<User> findAllActive();

    List<UserDTO> findAllUserDTOByDeletedIsFalse();

    Optional<UserDTO> findUserDTOById(Long id);

    boolean existsByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
