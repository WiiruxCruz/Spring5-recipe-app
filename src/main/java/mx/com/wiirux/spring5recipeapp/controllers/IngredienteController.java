package mx.com.wiirux.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.services.IngredienteService;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;
import mx.com.wiirux.spring5recipeapp.services.UnidadMedidaService;

@Slf4j
@Controller
public class IngredienteController {
	private final RecetaService recetaService;
	private final IngredienteService ingredienteService;
	private final UnidadMedidaService unidadMedidaService;
	
	public IngredienteController(
		RecetaService recetaService,
		IngredienteService ingredienteService,
		UnidadMedidaService unidadMedidaService
	) {
		this.recetaService = recetaService;
		this.ingredienteService = ingredienteService;
		this.unidadMedidaService = unidadMedidaService;
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
	
	@GetMapping
	@RequestMapping("receta/{recetaId}/ingrediente/{ingredienteId}/actualizar")
	public String actualizarIngrediente(
		@PathVariable String recetaId,
		@PathVariable String ingredienteId,
		Model model
	) {
		model.addAttribute("ingrediente", ingredienteService.buscarPorRecetaIdEIngredienteId( Long.valueOf(recetaId), Long.valueOf(ingredienteId) ) );
		model.addAttribute("listaUnidadMedida", unidadMedidaService.listaTodasUnidadesMedidas() );
		
		return "receta/ingrediente/formularioIngrediente";
	}
	
	@PostMapping
	@RequestMapping("receta/{recetaId}/ingrediente")
	public String guardarOActualizar(@ModelAttribute IngredienteCommand command) {
		IngredienteCommand guardarCommand = ingredienteService.guardarIngredienteCommand(command);
		
		log.info("Receta guardada id:" + guardarCommand.getId());
		log.info("guardar ingrediente id:" + guardarCommand.getId());
		
		return "redirect:/receta/" + guardarCommand.getRecetaId() + "/ingrediente/" + guardarCommand.getId() + "/mostrar";
	}
	
	
}
