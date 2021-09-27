package com.kps.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kps.login.model.AuthenticationRequest;
import com.kps.login.model.AuthenticationResponse;
import com.kps.login.security.MyUserDetailsService;
import com.kps.login.util.JwtTokenUtil;

@RestController
public class AccountController {
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Autowired 
	MyUserDetailsService myUserDetailsService;
	
	@Autowired 
	JwtTokenUtil jwtUtil;
	
	@GetMapping("/hello")
	public String accountLogin() {
		return "Hello";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
 		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
				,authenticationRequest.getPassword()));
		}catch(Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.UNAUTHORIZED, authenticationRequest.getPassword()+" Not Found");
			
		}
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
		
		
	}
}
