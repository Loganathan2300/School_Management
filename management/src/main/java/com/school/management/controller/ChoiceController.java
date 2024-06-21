package com.school.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.ChoiceDTO;
import com.school.management.dto.PaginationDto;
import com.school.management.entity.Choice;
import com.school.management.entity.Question;
import com.school.management.service.ChoiceService;

@RestController
@RequestMapping("/api/v1")
public class ChoiceController {
	
	@Autowired
	ChoiceService choiceService;
	
	@PostMapping("choice/question/{id}")
	public Question createQuestions(@PathVariable long id, @RequestBody Choice choice) {
		return choiceService.createQuestions(id,choice);
	}
	
	@GetMapping("/choice")
	public List<Choice> retriveStudent(){
		return this.choiceService.retriveChoice();
	}
	
	@GetMapping("/choices")
    public List<ChoiceDTO> getAllChoices() {
        return choiceService.getAllChoices();
    }
	
	@GetMapping("/choice/{id}")
	public Choice retrieveStudentId(@PathVariable Long id) {
		return this.choiceService.retrieveChoiceId(id);
	}
	
	@GetMapping("/choice/pagination")
	public List<Choice>getChoicePagination(PaginationDto paginationDto){
		return choiceService.getChoicePagination(paginationDto.getPage(),paginationDto.getSize());
	}
	
	@GetMapping("/choice/search")
	public List<Choice>SearchChoice(ChoiceDTO choiceDTO){
		return choiceService.searchChoice(choiceDTO.getId(),choiceDTO.getContent());
	}
	
	@DeleteMapping("/choice/{id}")
	public void deleteChoiceId(@PathVariable Long id){
		 choiceService.deleteChoiceId(id);
	}
}
