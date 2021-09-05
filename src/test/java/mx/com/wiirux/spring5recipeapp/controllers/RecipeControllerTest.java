package mx.com.wiirux.spring5recipeapp.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

class RecipeControllerTest {
	
	@Mock
	RecetaService rs;
	
	@Mock
	Model model;
	
	RecipeController rc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		rc = new RecipeController(rs);
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
		String viewName = rc.listaRecetas(model);
		
		//entonces
		assertEquals("receta/index", viewName);
		verify(rs, times( 1 ) ).getRecetas();
		//verify( model, times(1) ).addAttribute(Mockito.eq("recetas"), Mockito.anySet() );
		verify( model, times(1) ).addAttribute(Mockito.eq("recetas"), argumentCaptor.capture() );
		Set<Receta> setRecetaController = argumentCaptor.getValue();
		assertEquals(2,  setRecetaController.size());
	}

}
