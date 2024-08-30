package com.school.management.service;

import com.school.management.dto.JwtAuthenticationResponse;
import com.school.management.dto.ReferceTokenRequest;
import com.school.management.dto.SignInRequest;
import com.school.management.dto.SignUpRequest;
import com.school.management.entity.Role;
import com.school.management.entity.User;
import com.school.management.exception.CustomException;
import com.school.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@Component
public class AuthenticationServiceImpl {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTServiceImpl jwtService;

    public User signUp(SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already registered: " + signUpRequest.getEmail());
        }

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User superAdminSignUp(SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already registered: " + signUpRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setRole(Role.SUPERADMIN);
        return userRepository.save(user);
    }

    public User adminSignUp(SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already registered: " + signUpRequest.getEmail());
        }

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("Invalid email"));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid password");
        }

        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(ReferceTokenRequest referceTokenRequest) {
        String userEmail = jwtService.extractUserName(referceTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("Invalid Email id"));
        if (jwtService.isTokenValid(referceTokenRequest.getToken(), user)) {
            String jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(referceTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }

}
