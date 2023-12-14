package com.exam.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.User;
import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.model.exam.Result;
import com.exam.repo.ResultRepository;
import com.exam.service.QuizService;
import com.exam.service.ResultService;
import com.exam.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private QuizService quizService;
	 @Autowired
	 ResultService res;
	 @Autowired
	 ResultRepository repo;
	 @Autowired
	 UserService userv;

	

	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
	return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	// get quizzes
	@GetMapping("/")
	public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	//get single quiz
	@GetMapping("/{qid}")
	public Quiz getQuiz(@PathVariable("qid") Long qid) {
		return this.quizService.getQuiz(qid);
	}
	
	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid) {
	this.quizService.deleteQuiz(qid) ;
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long
	cid){
		Category category=new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesOfCategory(category);
	}
	@PostMapping("/{qid}/addresult")	
	public ResponseEntity<Quiz> addResult(@PathVariable("qid") Long qid,@RequestBody Result mar) {
		System.out.println("I AMM CALLED YAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA + "+ mar.getMarks());
		
		 Quiz q = this.quizService.getQuiz(qid);
		
		 Result resu = new Result();
		 resu.setMarks(mar.getMarks()); 
		 
		 resu.setQname(q.getTitle());
		 resu.setQuiz(q);
		 resu.setDate(new Date());
		 resu.setUname(mar.getUname());
		 System.out.println("Receiving data from aaaaaaaaaaaaaaaaaaa +  "+mar.getUname());
		 User u = userv.getUser(resu.getUname());
		 resu.setUser(u);
		 u.getResults().add(resu);
	     q.getResults().add(resu);
	     resu.setQuiz(q);
	     repo.save(resu);
		 return ResponseEntity.ok(q);
		
	}
	@GetMapping("/{qid}/getresult")
	public ResponseEntity<Set<Result>> getResult(@PathVariable("qid") Long qid) {
		
		 Quiz q = this.quizService.getQuiz(qid);
		
		 Set<Result> retrn =  res.getResultsOfQuiz(q);
		 return ResponseEntity.ok(retrn);
		 
	}
	@GetMapping("/getresultsofuser/{uid}")
	public ResponseEntity<Set<Result>> getResults(@PathVariable("uid") String userName) {
		User user = userv.getUser(userName);
		
		
		 Set<Result> retrn =  res.getResultsOfUser(user);
		 return ResponseEntity.ok(retrn);
		
	}
}
