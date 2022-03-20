package com.cg.controller.api;

import com.cg.model.User;
import com.cg.model.status.Role;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserRestController {
    @Autowired
    UserService userService;

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        user.setRole(Role.ADMIN);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for get customer", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user) {
        Optional<User> userCheck = userService.findById(id);
        if (userCheck.isPresent()) {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for get customer", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> doDelete(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            userService.softDelete(user.get());
            return new ResponseEntity<>("Delete customer successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error for delete customer", HttpStatus.BAD_REQUEST);
    }
}
