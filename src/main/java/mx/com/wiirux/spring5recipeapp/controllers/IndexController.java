package mx.com.wiirux.spring5recipeapp.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.com.wiirux.spring5recipeapp.domain.Categoria;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;
import mx.com.wiirux.spring5recipeapp.repositories.CategoriaRepositorio;
import mx.com.wiirux.spring5recipeapp.repositories.UnidadMedidaRepositorio;

@Controller
public class IndexController {
	
	private CategoriaRepositorio cr;
	private UnidadMedidaRepositorio umr;
	
	public IndexController(CategoriaRepositorio cr, UnidadMedidaRepositorio umr) {
		this.cr = cr;
		this.umr = umr;
	}

	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model m) {
		
		Optional<Categoria> categoriaOptional = cr.findByDescripcion("Americana");
		Optional<UnidadMedida> unidadMedidaOptional = umr.findByDescripcion("Cucharada");
		
		System.out.println("Categoria id is:" + categoriaOptional.get().getId());
		System.out.println("Unidad medida id es:" + unidadMedidaOptional.get().getId());
		
		return "index";
	}
}
