package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired	
	private UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user ) throws Exception {
		
		user.setProfile("default.png");
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));	
		
		
		Set<UserRole> ur = new HashSet<UserRole>();
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		ur.add(userRole);
		
		userService.createuser(user, ur);
		
		
		return user;
		
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	@DeleteMapping("/{userid}")
	public void deleteUser(@PathVariable("userid") Long userid) {
		this.userService.deleteUser(userid);
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user) throws Exception {
		User u = this.userService.getUser(user.getUserName());
		if(u!=null) {
			if(user.getEmail()!=null)
			u.setEmail(user.getEmail());
			if(user.getPassword()!=null)
				u.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			if(user.getPhone()!=null)
			u.setPhone(user.getPhone());			
			this.userService.updateUser(u);
			return u;
						
			
			
			

		}
		else {
Set<UserRole> ur = new HashSet<UserRole>();
			
			Role role = new Role();
			role.setRoleId(45L);
			role.setRoleName("NORMAL");
			
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);
			
			ur.add(userRole);
			
			return userService.createuser(user, ur);
		}
	}
	@PutMapping("/change")
	public User changePhoneEmailUser(@RequestBody User user) throws Exception {
			User u = this.userService.getUser(user.getUserName());
			if(u!=null) {
				if(user.getEmail()!=null)
				u.setEmail(user.getEmail());
				if(user.getPassword()!=null)
					u.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
				if(user.getPhone()!=null)
				u.setPhone(user.getPhone());			
				this.userService.updateUser(u);
				return u;
							
				
				
				

			}
			else return null;
			
		
				
	}
	

}
