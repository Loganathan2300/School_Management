package com.school.management.service;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.management.dto.JwtAuthenticationResponse;
import com.school.management.dto.SignInRequest;
import com.school.management.dto.SignUpRequest;
import com.school.management.entity.Role;
import com.school.management.entity.User;
import com.school.management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final AuthenticationManager authenticationManager;
	    private final JWTServiceImpl jwtService;
	    
	    public User signUp(SignUpRequest signUpRequest) {
	        User user =  new User();
	        user.setEmail(signUpRequest.getEmail());
	        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
	        user.setName(signUpRequest.getName());
	        user.setRole(Role.USER);
	        return userRepository.save(user);
	    }
	    
	    public JwtAuthenticationResponse signIn(SignInRequest signInRequest){
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
	                signInRequest.getPassword()));

	        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email id"));
	        String jwt = jwtService.generateToken(user);
	        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

	        JwtAuthenticationResponse jwtAuthenticationResponse =new JwtAuthenticationResponse();
	        jwtAuthenticationResponse.setToken(jwt);
	        jwtAuthenticationResponse.setRefreshToken(refreshToken);

	        return jwtAuthenticationResponse;
	    }


	    public User adminSignUp(SignUpRequest signUpRequest) {
	        User user =  new User();
	        user.setEmail(signUpRequest.getEmail());
	        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
	        user.setName(signUpRequest.getName());
	        user.setRole(Role.ADMIN);
	        return userRepository.save(user);
	    }

	    public JwtAuthenticationResponse refreshToken(String token ){
	        String userEmail = jwtService.extractUserName(token);
	        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new IllegalArgumentException("Invalid Email id"));  //.orElseThrow(() -> new IllegalArgumentException("Invalid Email id"))
	        if(jwtService.isTokenValid(token, user)){
	            String jwt = jwtService.generateToken(user);
	 

	            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
	            jwtAuthenticationResponse.setToken(jwt);
	            jwtAuthenticationResponse.setRefreshToken(token);

	            return jwtAuthenticationResponse;
	        }
	        return null;
	    }
}
