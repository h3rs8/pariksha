package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	public User createuser(User user, Set<UserRole> userRoles) throws Exception;

	public User getUser(String username);
	
	public void deleteUser(Long id);
	
	public User updateUser(User user);
	

}
