package mx.com.wiirux.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.services.IngredienteService;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Controller
public class IngredienteController {
	private final RecetaService recetaService;
	private final IngredienteService ingredienteService;
	
	public IngredienteController(RecetaService recetaService, IngredienteService ingredienteService) {
		this.recetaService = recetaService;
		this.ingredienteService = ingredienteService;
	}
	
	@GetMapping
	@RequestMapping("/receta/{recetaId}/ingredientes")
	public String listaIngredientes(@PathVariable String recetaId, Model model) {
		
		model.addAttribute("receta", recetaService.buscarCommandPorId(Long.valueOf(recetaId)));
		
		return "receta/ingrediente/lista";
	}
	
	@GetMapping
	@RequestMapping("receta/{recetaId}/ingrediente/{ingredienteId}/mostrar")
	public String mostrarRecetaIngrediente(
		@PathVariable String recetaId,
		@PathVariable String ingredienteId,
		Model model
	) {
		
		model.addAttribute("ingrediente", ingredienteService.buscarPorRecetaIdEIngredienteId(
				Long.valueOf(recetaId),
				Long.valueOf(ingredienteId)
		));
		return "receta/ingrediente/mostrar";
	}
	
}
