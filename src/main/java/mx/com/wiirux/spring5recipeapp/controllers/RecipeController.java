package mx.com.wiirux.spring5recipeapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.exceptions.NoEncontradoExcepcion;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Controller
public class RecipeController {
	
	private final RecetaService rs;
	
	public RecipeController(RecetaService rs) {
		// TODO Auto-generated constructor stub
		this.rs = rs;
	}
	/*
	 * Comportamiento anterior
	@GetMapping("/recetas")
	public String listaRecetas(Model m){
		//List<Receta> listaRecetas = (List<Receta>) this.rs.getRecetas();
		log.debug("Log desde RecipeController");
		m.addAttribute("recetas", this.rs.getRecetas());
		return "receta/index";
	}
	
	*/
	
	@GetMapping("/receta/{id}/mostrar")
	public String mostrarPorId(@PathVariable String id, Model model) {
		
		model.addAttribute("receta", rs.buscarPorId( new Long(id) ) );
		
		return "receta/mostrar";
	}
	
	@GetMapping("receta/nuevo")
	public String nuevaReceta(Model model) {
		
		model.addAttribute("receta", new Receta());
		
		return "receta/formularioReceta";
	}
	
	@GetMapping("receta/{id}/actualizar")
	public String actualizarReceta(@PathVariable String id, Model model) {
		model.addAttribute("receta", rs.buscarCommandPorId(Long.valueOf(id)));
		
		return "receta/formularioReceta";
	}
	
	@PostMapping("receta")
	public String guardarOActualizar(@ModelAttribute RecetaCommand command) {
		RecetaCommand salvarCommand = rs.guardarRecetaCommand(command);
		
		return "redirect:/receta/" + salvarCommand.getId() + "/mostrar";
	}
	
	@GetMapping
	@RequestMapping("receta/{id}/borrar")
	public String borraPorId(@PathVariable String id) {
		log.debug("Borrando id:" + id);
		
		rs.borrarRecetaPorId(Long.valueOf(id));
		
		return "redirect:/";
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoEncontradoExcepcion.class)
	public ModelAndView ManejadorNoEncontrado(Exception exception) {
		log.error("Manejador no encontrado");
		log.error(exception.getMessage());
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("error404");
		mav.addObject("excepcion", exception);
		
		return mav;
	}
	
	
}
