package com.cg.model;

import com.cg.model.dto.UserDTO;
import com.cg.model.status.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;


    private Role role;

    @Column(columnDefinition = "boolean default false")
    private boolean activeStatus;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    public UserDTO toUserDTO(){
        return new UserDTO()
                .setId(id)
                .setFullName(fullName)
                .setFullName(username)
                .setPassword(password)
                .setPhone(phone)
                .setEmail(email)
                .setAvatar(avatar);
    }

}
