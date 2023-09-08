package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@Override
	public User createuser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = userRepository.findByUserName(user.getUserName());
		if(local!=null) {
			System.out.println("User already exists");
			throw new Exception("User already present");
		}
		else {
			//adding roles to db
			for( UserRole ur:userRoles) {
				roleRepository.save(ur.getRole());
			}
			// adding roles to user object
			user.getUserRoles().addAll(userRoles);
			// saving that user object to db
			local  = userRepository.save(user);
			return local;
		}
		
		
		

	}


	@Override
	public User getUser(String username) {
		return this.userRepository.findByUserName(username);
		
	}


	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
		
	}


	@Override
	public User updateUser(User user) {
		return this.userRepository.save(user);
		
	}
	
	
}
