package com.school.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.school.management.exception.CustomException;
import com.school.management.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws CustomException {
        return userRepository.findByEmail(username).orElseThrow(() -> new CustomException("User Not Found !!!"));
    }
}
