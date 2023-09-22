package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.MyUserDetailsService;
import com.model.LoginRequest;
import com.service.JwtUtility;

@RestController
@Configuration
@RequestMapping("/auth")
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;
    
    @Autowired
	private MyUserDetailsService myUserDetailsService;
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;

	@GetMapping("/message")
	public String getString() {
		return "Hello";
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

		try {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return new ResponseEntity<>(jwtUtility.generateToken(loginRequest.getEmail()),HttpStatus.OK);
        } else {
            throw new RuntimeException("invalid access");
        }
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
	}
}
