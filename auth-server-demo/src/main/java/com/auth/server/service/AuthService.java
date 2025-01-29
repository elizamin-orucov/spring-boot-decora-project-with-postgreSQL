package com.auth.server.service;

import com.auth.server.dto.request.CreateUserRequest;
import com.auth.server.dto.request.UserRequestDto;
import com.auth.server.dto.response.ApiResponseDto;
import com.auth.server.dto.response.UserResponseDto;

public interface AuthService {
    UserResponseDto signIn(UserRequestDto requestDto);

    ApiResponseDto<UserResponseDto> signUp(CreateUserRequest createUserRequest);
}
