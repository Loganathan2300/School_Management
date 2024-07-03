package com.school.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.management.dto.AnswerDTO;
import com.school.management.dto.ChoiceDTO;
import com.school.management.dto.QuestionDTO;
import com.school.management.dto.StudentDTO;
import com.school.management.dto.StudentScore;
import com.school.management.entity.Answer;
import com.school.management.entity.Question;
import com.school.management.exception.CustomException;
import com.school.management.repository.AnswerRepository;
import com.school.management.repository.ChoiceRepository;
import com.school.management.repository.QuestionRepository;
import com.school.management.repository.StudentRepository;


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
		  
	    public List<AnswerDTO> getAllAnswers() {
		        List<Answer> answers = answerRepository.findAll();
		        return answers.stream().map(this::convertToDTO).collect(Collectors.toList());
		    }
		  
	    private AnswerDTO convertToDTO(Answer answer) {
		        AnswerDTO answerDTO = new AnswerDTO();
		        answerDTO.setId(answer.getId());
		        answerDTO.setAnswers(answer.getAnswers());

		        StudentDTO studentDTO = new StudentDTO();
		        studentDTO.setId(answer.getStudent().getId());
		        studentDTO.setName(answer.getStudent().getName());
		        studentDTO.setEmail(answer.getStudent().getEmail());
		        studentDTO.setSchool_name(answer.getStudent().getSchool());
		        answerDTO.setStudent(studentDTO);

		        QuestionDTO questionDTO = new QuestionDTO();
		        questionDTO.setId(answer.getQuestion().getId());
		        questionDTO.setSubject(answer.getQuestion().getSubject());
		        questionDTO.setContent(answer.getQuestion().getContent());
		        questionDTO.setPoints(answer.getQuestion().getPoints());
		        answerDTO.setQuestion(questionDTO);

		        ChoiceDTO choiceDTO = new ChoiceDTO();
		        choiceDTO.setId(answer.getChoice().getId());
		        choiceDTO.setContent(answer.getChoice().getContent());
		        answerDTO.setChoice(choiceDTO);

		        return answerDTO;
		    }
		  
		public Answer getAnswerById(Long id) {
		        Optional<Answer> answerOptional = answerRepository.findById(id);
		        return answerOptional.orElse(null);
		  }
	 
		public Answer createAnswer(Answer answer) {
	        Long studentId = answer.getStudent().getId();
	        Long questionId = answer.getQuestion().getId();
	
	        if (answerRepository.existsByStudentIdAndQuestionId(studentId, questionId)) {
	            throw new CustomException("Student has already answered this question..!");
	        }
	        
	        if (!validateAnswer(answer)) {
	            throw new CustomException("Invalid Answer");
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
	            throw new CustomException("Answer with ID " + answerId + " not found!");
	        }
	
	        if (!validateAnswer(answer)) {
	            throw new CustomException("Invalid Answer");
	        }
	
	        return answerRepository.save(answer);
	    }
	
	    public void deleteAnswer(Long id) {
	        answerRepository.deleteById(id);
	    }
	
	    public String calculateScore(Long studentId) {
	        int score = 0;
	        List<Answer> studentAnswers = answerRepository.findAllByStudentId(studentId);
	
	        for (Answer answer : studentAnswers) {
	            Question question = answer.getQuestion();
	            if (answer.getChoice().isCorrect()) {
	                score += question.getPoints();
	            }
	        }
	        
	        return "Student_Id : "+studentId+"\n"+"student_Result :"+score;
	    }
	    
	    public List<StudentScore> getAllStudentScores() {
	        Map<Long, Integer> studentScoresMap = new HashMap<>();
	        List<Answer> allAnswers = answerRepository.findAll();
	        
	        for (Answer answer : allAnswers) {
	            Long studentId = answer.getStudent().getId();
	            Question question = answer.getQuestion();
	            
	            if (answer.getChoice().isCorrect()) {
	                studentScoresMap.put(studentId, studentScoresMap.getOrDefault(studentId, 0) + question.getPoints());
	            }
	        }

	        List<StudentScore> studentScores = new ArrayList<>();
	        for (Map.Entry<Long, Integer> entry : studentScoresMap.entrySet()) {
	            studentScores.add(new StudentScore(entry.getKey(), entry.getValue()));
	        }
	        return studentScores;
	    }
	    
	    
	    
	    public List<AnswerDTO> getAnswers(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Answer> pagedAnswers = answerRepository.findAll(pageable);
	        List<Answer> answerList = pagedAnswers.getContent();
	        List<AnswerDTO> answerDTOs = new ArrayList<>();

	        for (Answer answer : answerList) {
	            AnswerDTO answerDTO = new AnswerDTO();
	            answerDTO.setId(answer.getId());
	            answerDTO.setAnswers(answer.getAnswers());

	            StudentDTO studentDTO = new StudentDTO();
	            studentDTO.setId(answer.getStudent().getId());
	            studentDTO.setName(answer.getStudent().getName());
	            studentDTO.setEmail(answer.getStudent().getEmail());
	            answerDTO.setStudent(studentDTO);

	            QuestionDTO questionDTO = new QuestionDTO();
	            questionDTO.setId(answer.getQuestion().getId());
	            questionDTO.setSubject(answer.getQuestion().getSubject());
	            questionDTO.setContent(answer.getQuestion().getContent());
	            questionDTO.setPoints(answer.getQuestion().getPoints());
	            answerDTO.setQuestion(questionDTO);

	            ChoiceDTO choiceDTO = new ChoiceDTO();
	            choiceDTO.setId(answer.getChoice().getId());
	            choiceDTO.setContent(answer.getChoice().getContent());
	            answerDTO.setChoice(choiceDTO);

	            answerDTOs.add(answerDTO);
	        }
	        return answerDTOs;
	    }

	
	    public List<AnswerDTO> searchAnswers( Boolean answers, Long student, Long question, Long choice) {
	        List<Answer> answerData = answerRepository.searchAnswers(answers, student, question, choice);
	        List<AnswerDTO> answerDTOs = new ArrayList<>();
	       
	        for (Answer answer : answerData) {
	            AnswerDTO answerDTO = new AnswerDTO();
	            answerDTO.setId(answer.getId());
	            answerDTO.setAnswers(answer.getAnswers());

	            StudentDTO studentDTO = new StudentDTO();
	            studentDTO.setId(answer.getStudent().getId());
	            studentDTO.setName(answer.getStudent().getName());
	            studentDTO.setEmail(answer.getStudent().getEmail());
	            answerDTO.setStudent(studentDTO);

	            QuestionDTO questionDTO = new QuestionDTO();
	            questionDTO.setId(answer.getQuestion().getId());
	            questionDTO.setSubject(answer.getQuestion().getSubject());
	            questionDTO.setContent(answer.getQuestion().getContent());
	            questionDTO.setPoints(answer.getQuestion().getPoints());
	            answerDTO.setQuestion(questionDTO);

	            ChoiceDTO choiceDTO = new ChoiceDTO();
	            choiceDTO.setId(answer.getChoice().getId());
	            choiceDTO.setContent(answer.getChoice().getContent());
	            answerDTO.setChoice(choiceDTO);

	            answerDTOs.add(answerDTO);
	        }
	        return answerDTOs;
	    }

		public List<AnswerDTO> getStudents(String search, Integer page, Integer size, String sortField,
				String sortDirection) {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
	        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 6, sort);
	        Page<Answer> answerPage = answerRepository.searchStudents(search, pageable);
	        List<AnswerDTO> answerDTOs = new ArrayList<>();
	        
	        for(Answer answer:answerPage) {
	        	
	        	AnswerDTO answerDTO = new AnswerDTO();
	            answerDTO.setId(answer.getId());
	            answerDTO.setAnswers(answer.getAnswers());

	            StudentDTO studentDTO = new StudentDTO();
	            studentDTO.setId(answer.getStudent().getId());
	            studentDTO.setName(answer.getStudent().getName());
	            studentDTO.setEmail(answer.getStudent().getEmail());
	            answerDTO.setStudent(studentDTO);

	            QuestionDTO questionDTO = new QuestionDTO();
	            questionDTO.setId(answer.getQuestion().getId());
	            questionDTO.setSubject(answer.getQuestion().getSubject());
	            questionDTO.setContent(answer.getQuestion().getContent());
	            questionDTO.setPoints(answer.getQuestion().getPoints());
	            answerDTO.setQuestion(questionDTO);

	            ChoiceDTO choiceDTO = new ChoiceDTO();
	            choiceDTO.setId(answer.getChoice().getId());
	            choiceDTO.setContent(answer.getChoice().getContent());
	            answerDTO.setChoice(choiceDTO);

	            answerDTOs.add(answerDTO);
	        }
			return answerDTOs;
		} 
		

}
