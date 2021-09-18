package mx.com.wiirux.spring5recipeapp.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.ImageService;

class ImageServiceImplTest {
	
	@Mock
	RecetaRepositorio recetaRepositorio;
	
	ImageService imageService;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageService = new ImageServiceImpl(recetaRepositorio);
	}

	@Test
	void testGuardarArchivoImagen() throws Exception{
		//dado
		Long id = 1L;
		MultipartFile multipartFile = new MockMultipartFile(
				"archivoImagen",
				"testing.txt",
				"text/plain",
				"Texto de prueba".getBytes()
			);
		
		Receta receta = new Receta();
		receta.setId(id);
		Optional<Receta> recetaOpcional = Optional.of(receta);
		
		when(recetaRepositorio.findById(anyLong())).thenReturn(recetaOpcional);
		
		ArgumentCaptor<Receta> argumentCaptor = ArgumentCaptor.forClass(Receta.class);
		
		//cuando
		imageService.guardarArchivoImagen(id, multipartFile);
		
		//entonces
		verify(recetaRepositorio, times(1)).save(argumentCaptor.capture());
		Receta recetaGuardada = argumentCaptor.getValue();
		assertEquals(multipartFile.getBytes().length, recetaGuardada.getImagen().length);
	}

}
