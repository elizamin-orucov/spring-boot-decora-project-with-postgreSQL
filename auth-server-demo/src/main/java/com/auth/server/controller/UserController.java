package com.auth.server.controller;

//import com.auth.server.dto.AuthRequest;
//import com.auth.server.dto.CreateUserRequest;
//import com.auth.server.dto.CreateUserResponse;
//import com.auth.server.service.TokenService;
//import com.auth.server.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.Map;
//
//@Slf4j
//@RestController
//@RequestMapping("/auth/test")
//public class UserController {
//
//    private final UserService service;
//
//    private final TokenService tokenService;
//
//    public UserController(UserService service, TokenService tokenService) {
//        this.service = service;
//        this.tokenService = tokenService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<CreateUserResponse> addUser(@RequestBody CreateUserRequest request){
//        CreateUserResponse response = service.createUser(request);
//        return ResponseEntity.ok(response);
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<Map<String, String>> generateToken(@RequestBody AuthRequest request) {
////        Map<String, String> response = tokenService.generateToken(request);
////        return ResponseEntity.ok(response);
////    }
//
//    @GetMapping("/user")
//    public Principal getUserString(Principal user) {
//        return user;
//    }
//
//    @GetMapping("/admin")
//    public String getAdminString() {
//        return "Admin page!";
//    }
//
//
//}
