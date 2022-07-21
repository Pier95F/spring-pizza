package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.repository.PizzaRepository;

@Controller
@RequestMapping("/")
public class PizzaController {

	@Autowired
	public PizzaRepository repo;
	
	@GetMapping
	public String Pizza(Model model) {
		model.addAttribute("pizza", repo.findAll());
		return "Index";
	}
}
