package mx.com.wiirux.spring5recipeapp.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.services.ImageService;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

class ImagenControllerTest {
	
	@Mock
	ImageService imageService;
	
	@Mock
	RecetaService recetaService;
	
	ImagenController controller;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new ImagenController(imageService, recetaService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void testObtenerImagenFormulario() throws Exception{
		//dado
		RecetaCommand command = new RecetaCommand();
		command.setId(1L);
		
		when(recetaService.buscarCommandPorId(anyLong())).thenReturn(command);
		
		//cuando
		mockMvc.perform(get("/receta/1/imagen"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("receta"))
			;
		
		//entonces
		verify(recetaService, times(1)).buscarCommandPorId(anyLong());
	}
	
	@Test
	public void manejarCargaImagen() throws Exception{
		MockMultipartFile multipartFile =
				new MockMultipartFile(
					"imageFile",
					"testing.xml",
					"text/plain",
					"Nombre de prueba".getBytes()
				);
		
		mockMvc.perform(multipart("/receta/1/imagen").file(multipartFile))
		.andExpect(status().is3xxRedirection())
		.andExpect(header().string("Location", "/receta/1/mostrar"));
		
		verify(imageService, times(1)).guardarArchivoImagen(anyLong(), any());
	}

}
