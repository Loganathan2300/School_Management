package com.school.management.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.QuestionDTO;
import com.school.management.entity.Question;
import com.school.management.entity.Teacher;
import com.school.management.service.QuestionService;

@RestController
@RequestMapping("/api/v1/admin")
public class QuestionController {

	
	@Autowired
	QuestionService questionService;
	
	@PostMapping("/question/{teacherid}")
	public Teacher createQuestions(@PathVariable long teacherid, @RequestBody Question question) {
		return questionService.createQuestions(teacherid,question);
	}
	
	@GetMapping("/question")
	public List<Question> retriveStudent(){
		return this.questionService.retriveQuestion();
	}
	
	@GetMapping("/question/{id}")
	public Question retrieveStudentId(@PathVariable Long id) {
		return this.questionService.retrieveQuestionId(id);
	}
	
	@PutMapping("/question/{id}")
    public Question update(@PathVariable("id") Long id, @RequestBody Question question) throws AccountNotFoundException {
        return this.questionService.update(id, question);
    }
	
	@GetMapping("/question/pagination")
	public List<QuestionDTO>getPaginateQuestion(@RequestParam int pageNo,@RequestParam int size){
		return questionService.getPaginateQuestion(pageNo,size);
	}
	
	@GetMapping("/question/search")
	public List<QuestionDTO>getSearchQuestion(String subject,String content,Integer points,int page,int size,String sortField,String sortDirection ){
		return questionService.getSearchQuestion(subject,content,points,page,size,sortField,sortDirection);
	}
	
	@DeleteMapping("/question/{id}")
	public void deleteTeacherId(@PathVariable Long id) {
		questionService.deleteTeacherId(id);
	}
	
//	@PostMapping("/question")
//	public Question createQuestion(@RequestBody final Question question) {
//		return this.questionService.createQuestion(question);
//	}
}

