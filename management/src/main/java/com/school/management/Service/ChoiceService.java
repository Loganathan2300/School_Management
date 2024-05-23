package com.school.management.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.Entity.Choice;
import com.school.management.Entity.Question;
import com.school.management.Repository.ChoiceRepository;
import com.school.management.Repository.QuestionRepository;

@Service
public class ChoiceService {

	@Autowired
	ChoiceRepository choiceRepository;
	
	@Autowired
	QuestionRepository questionRepository;

	public Question createQuestions(long id, Choice choice) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if(optionalQuestion.isPresent()) {
			Question question = optionalQuestion.get();
			choice.setQuestion(question);
			choiceRepository.save(choice);
			return questionRepository.save(question);
		}else {
            throw new RuntimeException("School not found");
        }
	}

	public List<Choice> retriveChoice() {
		return choiceRepository.findAll();
	}

	public Choice retrieveChoiceId(Long id) {
		return choiceRepository.findById(id).orElse(null);
	}

	public String removeId(Long id) {
		Optional<Choice> optionalChoice = choiceRepository.findById(id);
		if(optionalChoice.isPresent()) {
			choiceRepository.deleteById(id);
			return "Sucessfully Deleted....";
		}else {
			return "Data Not Found....";
		}
	}	
	
	
	
	
	
}
