package com.exam.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.exam.model.User;

@Component
public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByUserName(String userName);
	
}
