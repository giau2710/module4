package com.cg.controller.api;

import com.cg.model.User;
import com.cg.model.form.UserForm;
import com.cg.model.status.Role;
import com.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    UserService userService;

    //    @PostMapping("/create-admin")
//    public ResponseEntity<?> createAdmin(@RequestBody User user) {
//        user.setRole(Role.ADMIN);
//        userService.save(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestParam("file") MultipartFile file) throws IOException {
//        ,@RequestBody UserForm userForm
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("H:/image", file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        }else{
            return new ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST);
        }
//        User user = new User(userForm.getId(),userForm.getFullName(),userForm.getUsername(),userForm.getPassword(),userForm.getPhone(),userForm.getEmail(),fileUpload,userForm.getRole(),false,false);
//        user.setRole(Role.ADMIN);
//        userService.save(user);
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(MultipartFile userfile1) {
        String file1 = userfile1.getOriginalFilename();

        return new ResponseEntity<>(new Object(), HttpStatus.OK);
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
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
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
