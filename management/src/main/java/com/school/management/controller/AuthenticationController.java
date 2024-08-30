package com.school.management.controller;

import com.school.management.dto.JwtAuthenticationResponse;
import com.school.management.dto.ReferceTokenRequest;
import com.school.management.dto.SignInRequest;
import com.school.management.dto.SignUpRequest;
import com.school.management.entity.User;
import com.school.management.exception.CustomException;
import com.school.management.service.AuthenticationServiceImpl;
import com.school.management.service.JWTServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private JWTServiceImpl jwtService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/admin-signup")
    public User adminSignUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.adminSignUp(signUpRequest);
    }

    @PostMapping("/superadmin-signup")
    public User superAdminSignUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.superAdminSignUp(signUpRequest);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@Valid @RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationResponse refresh(@RequestBody ReferceTokenRequest referceTokenRequest) {
        return authenticationService.refreshToken(referceTokenRequest);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {

        SecurityContextHolder.clearContext(); // Clear the Security Context

        ResponseCookie cookie = jwtService.getCleanJwtCookie();   // Create a cookie to remove the JWT token

        // Return response indicating the user has been signed out
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new CustomException("You've been signed out!"));
    }

}
