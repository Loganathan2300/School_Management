package com.school.management.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.school.management.Entity.Answer;
import com.school.management.Entity.Question;
import com.school.management.Repository.AnswerRepository;
import com.school.management.Repository.ChoiceRepository;
import com.school.management.Repository.QuestionRepository;
import com.school.management.Repository.StudentRepository;


@Service
public class AnswerService {

	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	ChoiceRepository choiceRepository;

	private static final Logger logger = LoggerFactory.getLogger(AnswerService.class);

	  public List<Answer> getAllAnswers() {
	        return answerRepository.findAll();
	    }
	  
	  public Answer getAnswerById(Long id) {
	        Optional<Answer> answerOptional = answerRepository.findById(id);
	        return answerOptional.orElse(null);
	    }

	 
	public Answer createAnswer(Answer answer) {
        Long studentId = answer.getStudent().getId();
        Long questionId = answer.getQuestion().getId();

        if (answerRepository.existsByStudentIdAndQuestionId(studentId, questionId)) {
            throw new RuntimeException("Student has already answered this question!");
        }
        
        if (!validateAnswer(answer)) {
            throw new RuntimeException("Invalid Answer");
        }
        return answerRepository.save(answer);
    }
	
    private boolean validateAnswer(Answer answer) {
    	
        if (answer == null) {
            logger.error("Answer is null");
            return false;
        }
        
        if (answer.getStudent() == null || answer.getQuestion() == null || answer.getChoice() == null) {
            logger.error("Answer contains null reference: {}", answer);
            return false;
        }
        
        Long studentId = answer.getStudent().getId();
        Long questionId = answer.getQuestion().getId();
        Long choiceId = answer.getChoice().getId();

        boolean studentExists = studentRepository.existsById(studentId);
        boolean questionExists = questionRepository.existsById(questionId);
        boolean choiceExists = choiceRepository.existsById(choiceId);

        logger.debug("Validating Answer - studentId: {}, studentExists: {}", studentId, studentExists);
        logger.debug("Validating Answer - questionId: {}, questionExists: {}", questionId, questionExists);
        logger.debug("Validating Answer - choiceId: {}, choiceExists: {}", choiceId, choiceExists);

        if (!studentExists) {
            logger.error("Student with ID {} does not exist", studentId);
        }
        if (!questionExists) {
            logger.error("Question with ID {} does not exist", questionId);
        }
        if (!choiceExists) {
            logger.error("Choice with ID {} does not exist", choiceId);
        }

        return studentExists && questionExists && choiceExists;
    }


    public Answer updateAnswer(Answer answer) {
        Long answerId = answer.getId();
        if (!answerRepository.existsById(answerId)) {
            throw new RuntimeException("Answer with ID " + answerId + " not found!");
        }

        if (!validateAnswer(answer)) {
            throw new RuntimeException("Invalid Answer");
        }

        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public int calculateScore(Long studentId) {
        int score = 0;
        List<Answer> studentAnswers = answerRepository.findAllByStudentId(studentId);

        for (Answer answer : studentAnswers) {
            Question question = answer.getQuestion();
            if (answer.getChoice().isCorrect()) {
                score += question.getPoints();
            }
        }
        return score;
    }
    
    public Map<Long, Integer> getAllStudentScores() {
        Map<Long, Integer> studentScores = new HashMap<>();
        List<Answer> allAnswers = answerRepository.findAll();
        
        for (Answer answer : allAnswers) {
            Long studentId = answer.getStudent().getId();
            Question question = answer.getQuestion();
            
            if (answer.getChoice().isCorrect()) {
                studentScores.put(studentId, studentScores.getOrDefault(studentId, 0) + question.getPoints());
            }
        }
        return studentScores;
    }
    
}
