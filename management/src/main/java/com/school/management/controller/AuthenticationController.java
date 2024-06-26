package com.school.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.JwtAuthenticationResponse;
import com.school.management.dto.ReferceTokenRequest;
import com.school.management.dto.SignInRequest;
import com.school.management.dto.SignUpRequest;
import com.school.management.entity.User;
import com.school.management.service.AuthenticationServiceImpl;


@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationServiceImpl authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
		return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
	}

	@PostMapping("/admin-signup")
	public ResponseEntity<User> adminSignUp(@RequestBody SignUpRequest signUpRequest) {
		return ResponseEntity.ok(authenticationService.adminSignUp(signUpRequest));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@Validated @RequestBody SignInRequest signInRequest) {
		return ResponseEntity.ok(authenticationService.signIn(signInRequest));
	}

	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody ReferceTokenRequest referceTokenRequest) {
		return ResponseEntity.ok(authenticationService.refreshToken(referceTokenRequest));
	}

}
