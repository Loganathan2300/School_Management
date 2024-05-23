package com.school.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.Entity.Answer;
//import com.school.management.Repository.AnswerRepository;
import com.school.management.Service.AnswerService;

@RestController
@RequestMapping("/api/v1")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	

	
	@PostMapping("/answer")
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }

    @GetMapping("answer/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    @PutMapping("answer/{id}")
    public Answer updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        answer.setId(id); 
        return answerService.updateAnswer(answer);
    }

    @DeleteMapping("answer/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Answer> getAnswersByStudentId(@PathVariable Long studentId) {
        return answerService.findAllByStudentId(studentId);
    }
}
