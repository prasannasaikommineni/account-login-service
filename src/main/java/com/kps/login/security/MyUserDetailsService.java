package com.kps.login.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if("Prasanna".equals(username)) {
		return new User("Prasanna",passwordEncoder.encode("Test@123"), new ArrayList<>());
		}else if("Sai".equals(username)){
		return new User("Sai",passwordEncoder.encode("Test@123"), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException(username);
		}
	}

}
