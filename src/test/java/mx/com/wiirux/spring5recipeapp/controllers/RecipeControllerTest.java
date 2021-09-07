package mx.com.wiirux.spring5recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

class RecipeControllerTest {
	
	@Mock
	RecetaService rs;
	
	RecipeController rc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		rc = new RecipeController(rs);
	}
	
	@Test
	public void testObtenerReceta() throws Exception{
		Receta receta = new Receta();
		receta.setId(1L);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(rc).build();
		
		when(rs.buscarPorId(anyLong())).thenReturn(receta);
		
		mockMvc.perform(get("/receta/mostrar/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("receta/mostrar"))
		.andExpect(model().attributeExists("receta"))
		;
	}

}
