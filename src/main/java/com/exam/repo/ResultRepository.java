package com.exam.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.model.User;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
	
	Set<Result> findByQuiz(Quiz quiz);
	Set<Result> findByUser(User user);

}
