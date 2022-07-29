package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.repository.IngredientiRepository;

@Controller
@RequestMapping("/Ingredienti")
public class IngredientiController {

	@Autowired
	public IngredientiRepository repo;
	
	@GetMapping
	public String Ingredienti (Model model) {
		model.addAttribute("ing", repo.findAllByOrderByName());
		model.addAttribute("newIng", new jana60.model.Ingredienti ());
		return "Ingredienti";
		
	}
	
	@PostMapping("/add")
	public String IngredientsAdd (@ModelAttribute("newIng") jana60.model.Ingredienti Ingredients, Model mod) {
		repo.save(Ingredients);
		return "redirect:/Ingredienti";
	}
}
