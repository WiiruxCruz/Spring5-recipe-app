package mx.com.wiirux.spring5recipeapp.services.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.ImageService;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	
	private final RecetaRepositorio recetaRepositorio;
	
	public ImageServiceImpl(RecetaRepositorio recetaRepositorio) {
		// TODO Auto-generated constructor stub
		this.recetaRepositorio = recetaRepositorio;
	}

	@Override
	@Transactional
	public void guardarArchivoImagen(Long recetaId, MultipartFile archivo) {
		// TODO Auto-generated method stub
		log.debug("Recibi un archivo");
		
		try {
			Receta receta = recetaRepositorio.findById(recetaId).get();
			
			Byte[] byteObjects = new Byte[archivo.getBytes().length];
			
			int i = 0;
			
			for(byte b : archivo.getBytes()) {
				byteObjects[i++] = b;
			}
			
			receta.setImagen(byteObjects);
			
			recetaRepositorio.save(receta);
		} catch(IOException e) {
			log.error("Ocurrio un error", e);
			e.printStackTrace();
		}
	}

}
