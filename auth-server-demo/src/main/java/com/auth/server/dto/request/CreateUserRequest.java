package com.auth.server.dto.request;

import com.auth.server.model.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CreateUserRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    Set<RoleEnum> authorities;
}
