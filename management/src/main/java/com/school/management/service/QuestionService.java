package com.school.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.management.dto.QuestionDTO;
import com.school.management.entity.Question;
import com.school.management.entity.Teacher;
import com.school.management.exception.CustomException;
import com.school.management.repository.QuestionRepository;
import com.school.management.repository.TeacherRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	TeacherRepository teacherRepository;

	public Teacher createQuestions(long id, Question question) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
		if(optionalTeacher.isPresent()) {
			Teacher teacher = optionalTeacher.get();
			question.setTeacher(teacher);
            questionRepository.save(question);
            return teacherRepository.save(teacher);
		}else {
            throw new CustomException("School not found");
        }
	}

	public List<Question> retriveQuestion() {
		return questionRepository.findAll();
	}

	public Question retrieveQuestionId(Long id) {
		return questionRepository.findById(id).orElse(null);
	}
	
	 public Question update(long id, Question question) throws AccountNotFoundException { 
		 Optional <Question> QuestionData=questionRepository.findById(id);
		 if(QuestionData.isEmpty()) {
			 throw new AccountNotFoundException("User Not Found");
		 }
		 Question finalQuestion=QuestionData.orElseThrow();
		 if(question.getContent()!=null) {
			 finalQuestion.setContent(question.getContent());
		 }
		 if(question.getPoints()!= -1) {
			 finalQuestion.setPoints(question.getPoints());
		 }
		return this.questionRepository.save(finalQuestion);
	    }

	public void deleteTeacherId(Long id) {
		questionRepository.deleteById(id);
	}

	public List<QuestionDTO> getPaginateQuestion(int pageNo, int size) {
		Pageable pageable = PageRequest.of(pageNo, size);
		Page<Question> Questionpage=questionRepository.findAll(pageable);
		List<Question> questionList=Questionpage.getContent();
		List<QuestionDTO> QuestionDTOs = new ArrayList<>();
		
		for(Question question: questionList) {
			QuestionDTO questionDTO=new QuestionDTO();
			questionDTO.setId(question.getId());
			questionDTO.setSubject(question.getSubject());
			questionDTO.setContent(question.getContent());
			questionDTO.setPoints(question.getPoints());
			QuestionDTOs.add(questionDTO);
		}
		return QuestionDTOs;
	}
	
	public List<QuestionDTO> getSearchQuestion( String subject, String content,Integer points, int page, int size, String sortField, String sortDirection) {
		 Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
	        Pageable pageable = PageRequest.of(page, size, sort);
	        List<Question> questiondata=questionRepository.searchQuestion(subject,content,points,pageable);
            List<QuestionDTO> QuestionDTOs = new ArrayList<>();
		
		for(Question question: questiondata) {
			QuestionDTO questionDTO=new QuestionDTO();
			questionDTO.setId(question.getId());
			questionDTO.setSubject(question.getSubject());
			questionDTO.setContent(question.getContent());
			questionDTO.setPoints(question.getPoints());
			QuestionDTOs.add(questionDTO);
		}
		return QuestionDTOs;
	}
	
	
//	public Question createQuestion(Question question) {
//		return questionRepository.save(question);
//	}
}
