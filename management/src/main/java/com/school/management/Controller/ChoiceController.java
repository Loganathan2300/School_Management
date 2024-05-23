package com.school.management.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.Entity.Choice;
import com.school.management.Entity.Question;
import com.school.management.Service.ChoiceService;

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
	
	@GetMapping("/choice/{id}")
	public Choice retrieveStudentId(@PathVariable Long id) {
		return this.choiceService.retrieveChoiceId(id);
	}
	
	@DeleteMapping("/choice/{id}")
	public Map<String, String> removeId(@PathVariable Long id){
		Map<String, String> response = new HashMap<>();
		response.put("Message", choiceService.removeId(id));
		return response;
	}
}
