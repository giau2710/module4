package com.cg.model.dto;

import com.cg.model.User;
import com.cg.model.status.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;

    public User toUser(){
        return new User()
                .setId(id)
                .setFullName(fullName)
                .setFullName(username)
                .setPassword(password)
                .setPhone(phone)
                .setEmail(email)
                .setAvatar(avatar);
    }

}
