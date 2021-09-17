package mx.com.wiirux.spring5recipeapp.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.services.ImageService;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	

	@Override
	public void guardarArchivoImagen(Long id, MultipartFile archivo) {
		// TODO Auto-generated method stub
		log.debug("Recibi un archivo");
	}

}
