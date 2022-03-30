package com.cg.repository;

import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByDeletedIsFalse();

    boolean existsByUsernameAndPassword(String pass,String email);

    User findByUsername(String username);
}
