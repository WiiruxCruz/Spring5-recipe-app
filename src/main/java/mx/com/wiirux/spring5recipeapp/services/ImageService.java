package mx.com.wiirux.spring5recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	void guardarArchivoImagen(Long recetaId, MultipartFile archivo);
}
