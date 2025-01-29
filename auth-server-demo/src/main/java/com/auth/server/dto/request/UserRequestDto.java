package com.auth.server.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    private String email;
    private String password;
}
