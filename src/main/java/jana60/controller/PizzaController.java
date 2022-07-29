package jana60.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	 @GetMapping("/add")
	  public String AddPizza(Model model) {
	   model.addAttribute("pizza", new jana60.model.Pizza());
	   return "FormPizza";
	    }
	 
	    @PostMapping("/add")
	    public String SavePizza(@Valid @ModelAttribute("pizza") jana60.model.Pizza addPizza, BindingResult br) {
	    		Boolean brError = br.hasErrors();
	    		Boolean checkName = true;
	    		if (addPizza.getId() != null) {
	    			jana60.model.Pizza vecchia = repo.findById(addPizza.getId()).get();
	    			if(vecchia.getName().equalsIgnoreCase(addPizza.getName()))
	    				checkName = false;
	    		}
	    		if (checkName && repo.countByName(addPizza.getName()) > 0)
	    		{ br.addError(new FieldError("pizza", "name", "Hai già una pizza con questo nome"));
	    		brError = true;
	    		}
	    		if (brError)
	    			return "/formPizza";
	    		else {
	            repo.save(addPizza);
	            return "redirect:/";	
	    }
	    }
	    
	    @GetMapping("/delete/{id}")
	    public String PizzaDel (@PathVariable("id") Integer pizzaId , RedirectAttributes rd) {
	        Optional<jana60.model.Pizza> pizzaDel = repo.findById(pizzaId);
	        if (pizzaDel.isPresent()) {
	            repo.deleteById(pizzaId);
	            rd.addFlashAttribute("deleted","L'elemento è stato eliminato");
	            return "redirect:/";
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza " + pizzaId + " non esiste");
	        }
	    }
	    @GetMapping("/edit/{id}")
	    public String PizzaEdit(@PathVariable("id") Integer pizzaId , Model model){
	        Optional<jana60.model.Pizza> pizzaEdi = repo.findById(pizzaId);
	        if (pizzaEdi.isPresent()) {
	            model.addAttribute("pizza", pizzaEdi.get());
	            return "formPizza";
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La pizza " + pizzaId + " non esiste");
	        }
	    }
	    
	    
}
