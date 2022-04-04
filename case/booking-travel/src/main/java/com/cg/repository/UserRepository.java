package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    List<User> findAllByDeletedIsFalse();

    @Query("SELECT new com.cg.model.dto.UserDTO(" +
            "u.id," +
            "u.fullName," +
            "u.username," +
            "u.password," +
            "u.phone," +
            "u.email," +
            "u.avatar," +
            "u.activeStatus" +
            ") " +
            "FROM User AS u " +
            "WHERE u.deleted=false "
    )
    List<UserDTO> findAllUserDTOByDeletedIsFalse();

    Optional<UserDTO> findUserDTOById(Long id);
//    @Query("SELECT new com.cg.model.dto.UserDTO(" +
//            "u.id," +
//            "u.fullName," +
//            "u.username," +
//            "u.password," +
//            "u.phone," +
//            "u.email," +
//            "u.avatar," +
//            "u.activeStatus" +
//            ") " +
//            "FROM User AS u " +
//            "WHERE u.id = :id " +
//            "AND u.deleted=false "
//    )
//    Optional<UserDTO> findUserDTOById(@Param("id") Long id);


    boolean existsByUsernameAndPassword(String pass, String email);

    User findByUsername(String username);
}
