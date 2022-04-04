package com.cg.controller.api;

import com.cg.model.User;

import com.cg.model.dto.UserDTO;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {


        Optional<UserDTO> userDTO = userService.findUserDTOById(id);
        if (userDTO.isPresent()) {
            return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for get admin", HttpStatus.NO_CONTENT);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
//        Optional<User> userCheck = userService.findById(id);
//        if (userCheck.isPresent()) {
//            userService.save(user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Error for get customer", HttpStatus.NO_CONTENT);
//        }
//    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> doDelete(@PathVariable Long userId) {
        Optional<UserDTO> userDTO = userService.findUserDTOById(userId);
        if (userDTO.isPresent()) {
            userService.softDelete(userDTO.get().toUser());
            return new ResponseEntity<>("Delete admins successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error for delete admins", HttpStatus.BAD_REQUEST);
    }
}
