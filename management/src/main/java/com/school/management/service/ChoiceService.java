package com.school.management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.school.management.dto.ChoiceDTO;
import com.school.management.entity.Choice;
import com.school.management.entity.Question;
import com.school.management.repository.ChoiceRepository;
import com.school.management.repository.QuestionRepository;

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
	
	public List<ChoiceDTO> getAllChoices() {
        List<Choice> choices = choiceRepository.findAll();
        return choices.stream().map(this::convertToChoiceDTO).collect(Collectors.toList());
    }

    private ChoiceDTO convertToChoiceDTO(Choice choice) {
        ChoiceDTO choiceDTO = new ChoiceDTO();
        choiceDTO.setId(choice.getId());
        choiceDTO.setContent(choice.getContent());
        return choiceDTO;
    }

	public Choice retrieveChoiceId(Long id) {
		return choiceRepository.findById(id).orElse(null);
	}	
	
	public void deleteChoiceId(Long id) {
		choiceRepository.deleteById(id);
	}

	public List<Choice> getChoicePagination(int page, int size) {
	    Pageable pageable=PageRequest.of(page, size);
	    Page<Choice> pageChoice=choiceRepository.findAll(pageable);
		return pageChoice.getContent();
	}
	
	public List<Choice>searchChoice(Long id,String content){
		List<Choice> choiceSearch=choiceRepository.searchChoiceDetails(id,content);
		return choiceSearch;
	}

}
