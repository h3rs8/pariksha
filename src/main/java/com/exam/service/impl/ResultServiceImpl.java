package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.Result;
import com.exam.repo.ResultRepository;
import com.exam.service.ResultService;
@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	ResultRepository repo;

	@Override
	public Result addResult(Result result) {
		repo.save(result);
		return null;
	}

	@Override
	public Result getResult(Integer rid) {
		return repo.getById(rid);
		
	}

	@Override
	public Set<Result> getResultsOfQuiz(Quiz quiz) {
		
		return repo.findByQuiz(quiz);
	}

	@Override
	public Set<Result> getResultsOfUser(User user) {
		return repo.findByUser(user);
		
	}
	
	

}
