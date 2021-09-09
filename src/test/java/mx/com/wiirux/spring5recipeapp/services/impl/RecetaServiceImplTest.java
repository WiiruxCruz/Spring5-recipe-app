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

import mx.com.wiirux.spring5recipeapp.converters.RecetaARecetaCommand;
import mx.com.wiirux.spring5recipeapp.converters.RecetaCommandAReceta;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;

class RecetaServiceImplTest {
	
	RecetaServiceImpl rsi;
	
	@Mock
	RecetaRepositorio recetaRepository;
	
	@Mock
	RecetaARecetaCommand recetaARecetaCommand;
	
	@Mock
	RecetaCommandAReceta recetaCommandAReceta;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		rsi = new RecetaServiceImpl(recetaRepository, recetaCommandAReceta, recetaARecetaCommand);
		
	}
	
	@Test
	public void getRecipeByIdTest() throws Exception{
		Receta receta = new Receta();
		receta.setId(1L);
		Optional<Receta> recetaOpcional = Optional.of(receta);
		
		when(recetaRepository.findById(anyLong())).thenReturn(recetaOpcional);
		
		Receta recetaRegresada = rsi.buscarPorId(1L);
		
		assertNotNull(recetaRegresada, "Receta nula retornada");
		verify(recetaRepository, times(1)).findById(anyLong());
		verify(recetaRepository, never()).findAll();
	}

	@Test
	void testGetRecetas() {
		
		Receta receta = new Receta();
		HashSet recetasData = new HashSet<>();
		recetasData.add(receta);
		
		when(recetaRepository.findAll()).thenReturn(recetasData);
		
		Set<Receta> recetas = rsi.getRecetas();
		
		assertEquals(recetas.size(), 1);
		verify(recetaRepository, times(1)).findAll();
	}

}
