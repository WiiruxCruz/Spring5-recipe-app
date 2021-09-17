package mx.com.wiirux.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mx.com.wiirux.spring5recipeapp.services.ImageService;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Controller
public class ImagenController {
	private final ImageService imageService;
	private final RecetaService recetaService;
	
	public ImagenController(
		ImageService imageService,
		RecetaService recetaService
	) {
		this.imageService = imageService;
		this.recetaService = recetaService;
	}
	
	@GetMapping("receta/{id}/imagen")
	public String mostrarFormularioCargaImagen(@PathVariable String id, Model model) {
		model.addAttribute("receta", recetaService.buscarCommandPorId(Long.valueOf(id)));
		
		return "receta/formularioSubirImagen";
	}
	
	@PostMapping("receta/{id}/imagen")
	public String manejarImagenPost(
		@PathVariable String id,
		@RequestParam("imageFile") MultipartFile archivo
	) {
		imageService.guardarArchivoImagen(Long.valueOf(id), archivo);
		
		return "redirect:/receta/" + id + "/mostrar";
	}
	
}
