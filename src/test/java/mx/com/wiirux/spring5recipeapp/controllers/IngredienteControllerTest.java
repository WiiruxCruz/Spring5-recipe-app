package mx.com.wiirux.spring5recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

class IngredienteControllerTest {
	
	@Mock
	RecetaService recetaService;
	
	IngredienteController controlador;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controlador = new IngredienteController(recetaService);
		
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

}
