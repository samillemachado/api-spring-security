package com.growdev.desafio.dtos;

import com.growdev.desafio.entities.Role;
import com.growdev.desafio.entities.User;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class RoleDTO implements Serializable {

    private Long id;
    private String authority;
    private List<User> users;

    public RoleDTO(Role role){
        this.id = role.getId();
        this.authority = role.getAuthority();
    }
}
