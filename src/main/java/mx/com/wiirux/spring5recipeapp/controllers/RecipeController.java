package mx.com.wiirux.spring5recipeapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Controller
public class RecipeController {
	
	private final RecetaService rs;
	
	public RecipeController(RecetaService rs) {
		// TODO Auto-generated constructor stub
		this.rs = rs;
	}
	
	@GetMapping("/recetas")
	public String listaRecetas(Model m){
		//List<Receta> listaRecetas = (List<Receta>) this.rs.getRecetas();
		log.debug("Log desde RecipeController");
		m.addAttribute("recetas", this.rs.getRecetas());
		return "receta/index";
	}
}