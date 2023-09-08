package com.exam.controller;

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

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		
		Category category1= this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
		
	}
	
	@GetMapping("/{id}")
	public Category getCategory(@PathVariable("id") Long id) {
		return this.categoryService.getCategory(id);
	}
	
	//getall
	@GetMapping("/")
	public ResponseEntity<Set<Category>> getCategories(){
		return ResponseEntity.ok( this.categoryService.getCategories());
	}
	
	//update
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable("id") Long id) {
		this.categoryService.deleteCategory(id);
	}
}
