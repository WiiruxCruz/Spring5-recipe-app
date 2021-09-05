package mx.com.wiirux.spring5recipeapp.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;

class RecetaServiceImplTest {
	
	RecetaServiceImpl rsi;
	
	@Mock
	RecetaRepositorio rr;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		rsi = new RecetaServiceImpl(rr);
		
	}

	@Test
	void testGetRecetas() {
		
		Receta receta = new Receta();
		HashSet recetasData = new HashSet<>();
		recetasData.add(receta);
		
		when(rr.findAll()).thenReturn(recetasData);
		
		Set<Receta> recetas = rsi.getRecetas();
		
		assertEquals(recetas.size(), 1);
		verify(rr, times(1)).findAll();
	}

}
