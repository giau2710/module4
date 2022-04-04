package com.cg.model.dto;

import com.cg.model.User;
import com.cg.model.status.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    @Size(min = 2, max = 30)
    @NotBlank
    private String fullName;
    @Size(min = 2, max = 30)
    @NotBlank
    private String username;
    @Size(min = 6, max = 50)
    @NotBlank
    private String password;
    private String phone;
    private String email;
    private String avatar;
    private boolean activeStatus;

    public User toUser() {
        return new User()
                .setId(id)
                .setFullName(fullName)
                .setFullName(username)
                .setPassword(password)
                .setPhone(phone)
                .setEmail(email)
                .setAvatar(avatar)
                .setActiveStatus(activeStatus);
    }
}
