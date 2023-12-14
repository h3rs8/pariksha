package com.exam.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.UserService;
import com.exam.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired 
	private JwtUtil jwtUtils;
	
	
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword() );
			
			
		}
		catch(UserPrincipalNotFoundException e){
			e.printStackTrace();
			throw new Exception("User not found");
			
		} 
		//authenticated
		UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	@Autowired	
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@PostMapping("/forgot")
	public ResponseEntity<User> forgot(@RequestBody User user) {
		User us = this.userService.getUser(user.getUsername());
		if(us==null) {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
		if(user.getForgot1().trim().equals(us.getForgot1().trim())  && user.getForgot2().trim().equals(us.getForgot2().trim())   ) {
			us.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			userService.updateUser(us);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}
		catch (DisabledException e) {
			throw new Exception("USER DISABLED" + e.getMessage());
		}catch(BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS" +e.getMessage());
			
		}
		
	}
	
	// get details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		return (User)this.userDetailsService.loadUserByUsername(principal.getName());	
	}

}
