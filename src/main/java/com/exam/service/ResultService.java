package com.exam.service;

import java.util.List;
import java.util.Set;

import com.exam.model.User;
import com.exam.model.exam.Category;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.Result;
import com.exam.repo.ResultRepository;

public interface ResultService {
	public Result addResult(Result result);
	public Set<Result> getResultsOfQuiz(Quiz quiz);
	public Result getResult(Integer rid);
	public Set<Result> getResultsOfUser(User user);
	
	
}
