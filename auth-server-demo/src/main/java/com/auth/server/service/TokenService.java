package com.auth.server.service;

import com.auth.server.dto.request.CreateUserRequest;
import com.auth.server.dto.request.UserRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtService jwtService;

//    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    @Value("${jwt-key}")
    private static String SECRET;

    public TokenService(
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

//    public Map<String, String> generateToken(AuthRequest request){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
//
//        if (authentication.isAuthenticated()){
//            String token = jwtService.generateToken(request.email());
//
//            UserEntity user = userRepository.findByEmail(request.email()).orElse(null);
//
//            Map<String, String> response = new HashMap<>();
//
//            response.put("access_token", token);
//            response.put("user_id", String.valueOf(user.getId()));
//
//            return response;
//        }
//
//        throw new UsernameNotFoundException("invalid email {} " + request.email());
//    }

    public String generateToken(UserRequestDto requestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));

        if (authentication.isAuthenticated()){
            return jwtService.generateToken(requestDto.getEmail());
        }
        throw new UsernameNotFoundException("invalid email {} " + requestDto.getEmail());
    }

    public String generateToken(CreateUserRequest requestDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));

        if (authentication.isAuthenticated()){
            return jwtService.generateToken(requestDto.getEmail());
        }
        throw new UsernameNotFoundException("invalid email {} " + requestDto.getEmail());
    }

}
