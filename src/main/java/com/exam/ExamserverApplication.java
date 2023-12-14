package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.RoleService;
import com.exam.service.UserService;
import com.exam.service.impl.UserServiceImpl;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("application running");
//		User us = new User();
//		us.setEmail("another@gmail.com");
//		us.setEnabled(true);
//	us.setFirstName("harsh");
//		us.setLastName("gupta");
//		us.setPassword(this.bCryptPasswordEncoder.encode("pass"));
//		us.setPhone("813481808");
//		us.setProfile("/images/a.jpg");
//		us.setUserName("h3rs9");
//		
//		User us2 = new User();
//		us2.setEmail("another@gmail.com");
//		us2.setEnabled(true);
//		us2.setFirstName("harsh");
//		us2.setLastName("gupta");
//		us2.setPassword(this.bCryptPasswordEncoder.encode("pass"));
//		us2.setPhone("813481808");
//		us2.setProfile("/images/a.jpg");
//		us2.setUserName("h3rs8");
//		
//		
//		Role role1 = new Role();
//		role1.setRoleName("ADMIN");
//		role1.setRoleId(44L);
//		
//		Role role2 = new Role();
//		role2.setRoleName("NORMAL");
//		role2.setRoleId(45L);
//		
//		
//		Set<UserRole> userRoleSet= new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(us);
//		userRoleSet.add(userRole);
//		
//		Set<UserRole> userRoleSet2= new HashSet<>();
//		UserRole userRole2 = new UserRole();
//		userRole2.setRole(role2);
//		userRole2.setUser(us2);
//		userRoleSet2.add(userRole2);
//		
//		
//		
//		this.userService.createuser(us, userRoleSet);
//		this.userService.createuser(us2, userRoleSet2);

		
		
		
		
		
		
	}

}
