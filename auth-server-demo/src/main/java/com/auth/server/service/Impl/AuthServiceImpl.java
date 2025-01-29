package com.auth.server.service.Impl;

import com.auth.server.dto.request.CreateUserRequest;
import com.auth.server.dto.request.UserRequestDto;
import com.auth.server.dto.response.ApiResponseDto;
import com.auth.server.dto.response.UserResponseDto;
import com.auth.server.service.AuthService;
import com.auth.server.service.TokenService;
import com.auth.server.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;

    private final UserService userService;

    public AuthServiceImpl(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }


    @Override
    public UserResponseDto signIn(UserRequestDto requestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        String accessToken = tokenService.generateToken(requestDto);

        userResponseDto.setEmail(requestDto.getEmail());
        userResponseDto.setToken(accessToken);
        return userResponseDto;
    }

    @Override
    public ApiResponseDto<UserResponseDto> signUp(CreateUserRequest createUserRequest) {
        String message = userService.createUser(createUserRequest);
        String accessToken = tokenService.generateToken(createUserRequest);

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .email(createUserRequest.getEmail())
                .token(accessToken)
                .build();

        return ApiResponseDto.<UserResponseDto>builder()
                .isSuccess(true)
                .message(message)
                .response(userResponseDto)
                .build();
    }
}
