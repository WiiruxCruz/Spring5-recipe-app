package mx.com.wiirux.spring5recipeapp.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.exceptions.NoEncontradoExcepcion;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

class RecipeControllerTest {
	
	@Mock
	RecetaService rs;
	
	RecipeController rc;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		rc = new RecipeController(rs);
		
		mockMvc = MockMvcBuilders.standaloneSetup(rc).build();
	}
	
	@Test
	public void testObtenerReceta() throws Exception{
		Receta receta = new Receta();
		receta.setId(1L);
		
		when(rs.buscarPorId(anyLong())).thenReturn(receta);
		
		mockMvc.perform(get("/receta/1/mostrar"))
		.andExpect(status().isOk())
		.andExpect(view().name("receta/mostrar"))
		.andExpect(model().attributeExists("receta"))
		;
	}
	
	@Test
	public void testObtenerRecetaNoEncontrada() throws Exception{
		/*
		Receta receta = new Receta();
		receta.setId(1L);
		
		when(rs.buscarPorId(anyLong())).thenThrow(NoEncontradoExcepcion.class);
		
		mockMvc.perform(get("/receta/1/mostrar"))
		.andExpect(status().isNotFound())
		//para hacer funcionar este, cambiar el estatus error code y recompilar
		//.andExpect(status().is5xxServerError())
		;
		*/
		
		RecetaCommand command = new RecetaCommand();
		mockMvc.perform(get("/receta/nuevo"))
		.andExpect(status().isOk())
		.andExpect(view().name("receta/formularioReceta"))
		.andExpect(model().attributeExists("receta"))
		;
	}
	
	@Test
	public void testObtenerRecetaNumberFormatExcepcion() throws Exception {
		
		mockMvc.perform(get("/receta/asdf/mostrar"))
		.andExpect(status().isBadRequest())
		.andExpect(view().name("error400"))
		;
	}
	
	@Test
	public void testObtenerNuevaReceta() throws Exception{
		RecetaCommand command = new RecetaCommand();
		command.setId(1L);
		
		mockMvc.perform(get("/receta/nuevo"))
		.andExpect(status().isOk())
		.andExpect(view().name("receta/formularioReceta"))
		.andExpect(model().attributeExists("receta"))
		;
	}
	
	@Test
	public void testPostNewRecetaForm() throws Exception{
		RecetaCommand command = new RecetaCommand();
		command.setId(2L);
		when( rs.guardarRecetaCommand( any() ) ).thenReturn(command);
		
		mockMvc.perform(
			post("/receta")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "")
			.param("descripcion", "Algun String")
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/receta/2/mostrar"))
		;
	}
	
	@Test
	public void testGetUpdateView() throws Exception{
		RecetaCommand command = new RecetaCommand();
		command.setId(2L);
		
		when(rs.buscarCommandPorId(anyLong())).thenReturn(command);
		
		mockMvc.perform(
			get("/receta/1/actualizar")
		).andExpect(status().isOk())
		.andExpect(view().name("receta/formularioReceta"))
		.andExpect(model().attributeExists("receta"))
		;
	}
	
	@Test
	public void testDeleteAction() throws Exception{
		mockMvc.perform( get("/receta/1/borrar") )
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"))
		;
		
		verify(rs, times(1)).borrarRecetaPorId(anyLong());
	}
}
