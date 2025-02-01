package com.auth.server.service;

import com.auth.server.dto.request.CreateUserRequest;
import com.auth.server.dto.response.UserResponseDto;
import com.auth.server.model.UserEntity;
import com.auth.server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByEmail(email);

        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
    }

    public UserService(
            UserRepository theUserRepository,
            BCryptPasswordEncoder passwordEncoder,
            EmailService emailService
    ) {
    this.userRepository = theUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public Optional<UserEntity> getByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    public String createUser(CreateUserRequest request) {
        UserEntity newUser = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(request.getAuthorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        String message;
        try {
            UserEntity savedUser = userRepository.save(newUser);
            message = "Success create new user";
        } catch (Exception e) {
            message = e.getMessage();
        }

        return message;

    }

    private UserResponseDto toUserResponseDto(CreateUserRequest userRequest){
        return new UserResponseDto().builder().email(userRequest.getEmail()).build();
    }

}
