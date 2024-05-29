package com.school.management.controller;

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

import com.school.management.dto.AnswerDTO;
import com.school.management.dto.StudentScore;
import com.school.management.entity.Answer;
import com.school.management.service.AnswerService;

@RestController
@RequestMapping("/api/v1")
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	@PostMapping("/answer")
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.createAnswer(answer);
    }
    
    @GetMapping("/answer/{studentId}")
    public int getStudentScore(@PathVariable Long studentId) {
        return answerService.calculateScore(studentId);
    }
    
    @GetMapping("/answers")
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }
    
    @GetMapping("/answer/student/score")
    public List<StudentScore> getAllStudentScores() {
        return answerService.getAllStudentScores();
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
    
}
