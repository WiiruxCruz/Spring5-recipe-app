package mx.com.wiirux.spring5recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

class IndexControllerTest {

	@Mock
	RecetaService rs;
	
	@Mock
	Model model;
	
	IndexController ic;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		ic = new IndexController(rs);
	}
	
	@Test
	public void testMockMVC() throws Exception{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ic).build();
		
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"))
			;
		
	}

	@Test
	void testListaRecetas() {
		
		//dado
		Set<Receta> recetas = new HashSet<>();
		recetas.add(new Receta());
		
		Receta receta = new Receta();
		receta.setId(1L);
		recetas.add(receta);
		
		when(rs.getRecetas()).thenReturn(recetas);
		
		ArgumentCaptor<Set<Receta>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		//cuando
		String viewName = ic.listaRecetas(model);
		
		//entonces
		assertEquals("index", viewName);
		verify(rs, times( 1 ) ).getRecetas();
		//verify( model, times(1) ).addAttribute(Mockito.eq("recetas"), Mockito.anySet() );
		verify( model, times(1) ).addAttribute(Mockito.eq("recetas"), argumentCaptor.capture() );
		Set<Receta> setRecetaController = argumentCaptor.getValue();
		assertEquals(2,  setRecetaController.size());
	}


}
