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

import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.converters.UnidadMedidaAUnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;
import mx.com.wiirux.spring5recipeapp.repositories.UnidadMedidaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.UnidadMedidaService;

class UnidadMedidaServiceImplTest {
	
	UnidadMedidaAUnidadMedidaCommand unidadMedidaAUnidadMedidaCommand = new UnidadMedidaAUnidadMedidaCommand();
	UnidadMedidaService service;
	
	@Mock
	UnidadMedidaRepositorio unidadMedidaRepositorio;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new UnidadMedidaServiceImpl( unidadMedidaRepositorio, unidadMedidaAUnidadMedidaCommand);
	}

	@Test
	void testListaTodasUnidadesMedidas() {
		//dado
		Set<UnidadMedida> unidadMedida = new HashSet<>();
		
		UnidadMedida um1 = new UnidadMedida();
		um1.setId(1L);
		unidadMedida.add(um1);
		
		UnidadMedida um2 = new UnidadMedida();
		um2.setId(2L);
		unidadMedida.add(um2);
		
		when(unidadMedidaRepositorio.findAll()).thenReturn(unidadMedida);
		
		//cuando
		Set<UnidadMedidaCommand> commands = service.listaTodasUnidadesMedidas();
		
		//entonces
		assertEquals(2,  commands.size());
		verify(unidadMedidaRepositorio, times(1)).findAll();
	}

}
