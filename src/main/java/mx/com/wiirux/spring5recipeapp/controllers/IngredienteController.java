package mx.com.wiirux.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Controller
public class IngredienteController {
	private final RecetaService recetaService;
	
	public IngredienteController(RecetaService recetaService) {
		this.recetaService = recetaService;
	}
	
	@GetMapping
	@RequestMapping("/receta/{recetaId}/ingredientes")
	public String listaIngredientes(@PathVariable String recetaId, Model model) {
		
		model.addAttribute("receta", recetaService.buscarCommandPorId(Long.valueOf(recetaId)));
		
		return "receta/ingrediente/lista";
	}
}
