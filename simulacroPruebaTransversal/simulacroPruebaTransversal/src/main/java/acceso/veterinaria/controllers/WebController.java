package acceso.veterinaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import Exceptions.VeterinariaNotFoundException;
import acceso.veterinaria.models.Animal;
import acceso.veterinaria.services.VeterinariaService;

@Controller
@RequestMapping("/clinica")

public class WebController {
	@Autowired
	private VeterinariaService veterinariaService;

	@RequestMapping("/") 
	public String index(Model model) {
		return "index";
	}

	@PostMapping("/animal")
	@ResponseBody
	public String addAnimal(@RequestBody Animal animal) {
	    String addedAnimal = veterinariaService.createAnimal(animal);
	    return addedAnimal;
	}

	@RequestMapping("/lista")
	public String catalog(Model model) {
		List<Animal> animales = veterinariaService.findAll();
		model.addAttribute("animales", animales);
		return "lista";
	}
	
    // Método para obtener un producto por ID
    @GetMapping("/animal/{id}")
    public String getAnimalById(@PathVariable Long id, Model model) {
        Animal animal = veterinariaService.findAnimalById(id);
    	model.addAttribute("detalleAnimales", animal);
        return "detalle";
    }
    
	
	@ExceptionHandler(VeterinariaNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> handleException(VeterinariaNotFoundException pnfe) 
	{
	        Response response = Response.errorResonse(Response.NOT_FOUND, pnfe.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	 }

/*	@PutMapping("/animal")
	public ResponseEntity<Animal> updateProduct(@PathVariable Long id,@RequestBody Animal animal) {
		String addedAnimal = veterinariaService.createAnimal(animal);
		return new ResponseEntity<>(addedAnimal, HttpStatus.CREATED);
	}
*/
}
