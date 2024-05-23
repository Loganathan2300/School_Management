package com.school.management.Service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.Entity.Question;
import com.school.management.Entity.Teacher;
import com.school.management.Repository.QuestionRepository;
import com.school.management.Repository.TeacherRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	TeacherRepository teacherRepository;

	public Question createQuestion(Question question) {
		return questionRepository.save(question);
	}

	public Teacher createQuestions(long id, Question question) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
		if(optionalTeacher.isPresent()) {
			Teacher teacher = optionalTeacher.get();
			question.setTeacher(teacher);
            questionRepository.save(question);
            return teacherRepository.save(teacher);
		}else {
            throw new RuntimeException("School not found");
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

	public String removeId(Long id) {
		Optional<Question> removeDetails=questionRepository.findById(id);
		if(removeDetails.isPresent()) {
			questionRepository.deleteById(id);
			return "Sucessfully Deleted....";
		}else {
			return "Data Not Found....";
		}
	}
	
}
