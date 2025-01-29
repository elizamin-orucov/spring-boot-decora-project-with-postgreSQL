package com.auth.server.controller;

import com.auth.server.dto.request.CreateUserRequest;
import com.auth.server.dto.request.UserRequestDto;
import com.auth.server.dto.response.ApiResponseDto;
import com.auth.server.dto.response.UserResponseDto;
import com.auth.server.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> signUp(
            @RequestBody CreateUserRequest request
    ){
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserResponseDto> signIn(@RequestBody UserRequestDto request) {
        return ResponseEntity.ok(authService.signIn(request));
    }

    @GetMapping("/user")
    public Principal getUserString(Principal user) {
        return user;
    }

    @GetMapping("/admin")
    public String getAdminString() {
        return "Admin page!";
    }


}
