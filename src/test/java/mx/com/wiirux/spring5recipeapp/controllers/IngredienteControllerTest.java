package mx.com.wiirux.spring5recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.services.IngredienteService;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;
import mx.com.wiirux.spring5recipeapp.services.UnidadMedidaService;

class IngredienteControllerTest {
	
	@Mock
	RecetaService recetaService;
	
	@Mock
	IngredienteService ingredienteService;
	
	@Mock
	UnidadMedidaService unidadMedidaService;
	
	IngredienteController controlador;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controlador = new IngredienteController(
			recetaService,
			ingredienteService,
			unidadMedidaService
		);
		
		mockMvc = MockMvcBuilders.standaloneSetup(controlador).build();
	}

	@Test
	void testListaIngredientes() throws Exception {
		//dado
		RecetaCommand recetaCommand = new RecetaCommand();
		when(recetaService.buscarCommandPorId(anyLong())).thenReturn(recetaCommand);
		
		//cuando
		mockMvc.perform(get("/receta/1/ingredientes"))
		.andExpect(status().isOk())
		.andExpect(view().name("receta/ingrediente/lista"))
		.andExpect(model().attributeExists("receta"))
		;
		
		//entonces
		verify(recetaService, times(1)).buscarCommandPorId(anyLong());
	}
	
	@Test
	public void testShowIngrediente() throws Exception {
		//dado
		IngredienteCommand ingredientecommand = new IngredienteCommand();
		
		//cuando
		when(ingredienteService.buscarPorRecetaIdEIngredienteId(anyLong(), anyLong())).thenReturn(ingredientecommand);
		
		//entonces
		mockMvc.perform(get("/receta/1/ingrediente/2/mostrar"))
		.andExpect(status().isOk())
		.andExpect(view().name("receta/ingrediente/mostrar"))
		.andExpect(model().attributeExists("ingrediente"))
		;
	}
	
	@Test
	public void testActualizarIngrediente() throws Exception{
		//dado
		IngredienteCommand ingredienteCommand = new IngredienteCommand();
		
		//cuando
		when(ingredienteService.buscarPorRecetaIdEIngredienteId(
			anyLong(),
			anyLong()
		)).thenReturn(ingredienteCommand);
		
		when(unidadMedidaService.listaTodasUnidadesMedidas()).thenReturn(new HashSet<>());
		
		//entonces
		mockMvc.perform(get("/receta/1/ingrediente/2/actualizar"))
		.andExpect(status().isOk())
		.andExpect(view().name("receta/ingrediente/formularioIngrediente"))
		.andExpect(model().attributeExists("ingrediente"))
		.andExpect(model().attributeExists("listaUnidadMedida"))
		;
	}
	
	@Test
	public void testGuardarOActualizar() throws Exception{
		//dado
		IngredienteCommand command = new IngredienteCommand();
		command.setId(3L);
		command.setRecetaId(2L);
		
		//cuando
		when(ingredienteService.guardarIngredienteCommand(any())).thenReturn(command);
		
		//entonces
		mockMvc.perform(
			post("/receta/2/ingrediente")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "")
			.param("descripcion", "some string")
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/receta/2/ingrediente/3/mostrar"))
		;
	}

}
