package com.cg.model.form;

import com.cg.model.status.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserForm {
    private Long id;

    @NotNull(message = "The fullName is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String fullName;

    @NotNull(message = "The username is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String username;

    @NotNull(message = "The password is required")
    @Size(min = 6,message = "Password must be 6 or more characters long")
    private String password;

    @NotNull(message = "The phone is required")
    private String phone;

    @NotNull(message = "The email is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String email;


    private MultipartFile avatar;

    private Role role;

}
