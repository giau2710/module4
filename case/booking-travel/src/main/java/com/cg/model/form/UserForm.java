package com.cg.model.form;

import com.cg.model.status.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserForm {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private MultipartFile avatar;
    private Role role;


}
