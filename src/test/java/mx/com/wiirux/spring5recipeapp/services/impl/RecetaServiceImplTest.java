package mx.com.wiirux.spring5recipeapp.services.impl;


//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;


import java.util.HashSet;
import java.util.Optional;
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
	public void getRecipeByIdTest() throws Exception{
		Receta receta = new Receta();
		receta.setId(1L);
		Optional<Receta> recetaOpcional = Optional.of(receta);
		
		when(rr.findById(anyLong())).thenReturn(recetaOpcional);
		
		Receta recetaRegresada = rsi.buscarPorId(1L);
		
		assertNotNull(recetaRegresada, "Receta nula retornada");
		verify(rr, times(1)).findById(anyLong());
		verify(rr, never()).findAll();
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
