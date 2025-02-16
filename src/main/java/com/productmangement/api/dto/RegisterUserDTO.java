package com.productmangement.api.dto;

import com.productmangement.api.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class RegisterUserDTO extends UserDTO{
    private Set<Role> roles;
}
