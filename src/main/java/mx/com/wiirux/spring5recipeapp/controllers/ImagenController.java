package mx.com.wiirux.spring5recipeapp.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
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
	
	@GetMapping("receta/{id}/recetaImagen")
	public void renderImagenDesdeDB(@PathVariable String id, HttpServletResponse respuesta) throws IOException{
		RecetaCommand recetaCommand = recetaService.buscarCommandPorId(Long.valueOf(id));
		
		byte[] arregloByte = new byte[recetaCommand.getImagen().length];
		
		int i = 0;
		for(byte wrappedByte : recetaCommand.getImagen()) {
			arregloByte[i++] = wrappedByte;
		}
		
		respuesta.setContentType("imagen/jpeg");
		InputStream is = new ByteArrayInputStream(arregloByte);
		IOUtils.copy(is, respuesta.getOutputStream());
	}
}
