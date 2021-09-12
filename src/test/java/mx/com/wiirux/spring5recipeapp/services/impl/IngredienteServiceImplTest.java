package mx.com.wiirux.spring5recipeapp.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.converters.IngredienteAIngredienteCommand;
import mx.com.wiirux.spring5recipeapp.converters.UnidadMedidaAUnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.IngredienteService;

class IngredienteServiceImplTest {
	
	private IngredienteAIngredienteCommand ingredienteAIngredienteCommand;
	
	@Mock
	RecetaRepositorio recetaRepositorio;
	
	IngredienteService ingredienteService;
	
	public IngredienteServiceImplTest() {
		this.ingredienteAIngredienteCommand = new IngredienteAIngredienteCommand(new UnidadMedidaAUnidadMedidaCommand());
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredienteService = new IngredienteServiceImpl(ingredienteAIngredienteCommand, recetaRepositorio);
	}
	
	@Test
	public void testBuscarPorRecetaIdEIngredienteId() throws Exception {
		
	}

	@Test
	void testBuscarPorRecetaIdEIngredienteIdHappyPath() throws Exception{
		//dado
		Receta receta = new Receta();
		receta.setId(1L);
		
		Ingrediente ingrediente1 = new Ingrediente();
		ingrediente1.setId(1L);
		
		Ingrediente ingrediente2 = new Ingrediente();
		ingrediente2.setId(2L);
		
		Ingrediente ingrediente3 = new Ingrediente();
		ingrediente3.setId(3L);
		
		receta.agregarIngrediente(ingrediente1);
		receta.agregarIngrediente(ingrediente2);
		receta.agregarIngrediente(ingrediente3);
		
		Optional<Receta> recetaOpcional = Optional.of(receta);
		
		when(recetaRepositorio.findById(anyLong())).thenReturn(recetaOpcional);
		
		//entonces
		IngredienteCommand ingredienteCommand = ingredienteService.buscarPorRecetaIdEIngredienteId(1L,  3L);
		
		//cuando
		assertEquals(Long.valueOf(3L), ingredienteCommand.getId());
		assertEquals(Long.valueOf(1L), ingredienteCommand.getRecetaId());
		verify(recetaRepositorio, times(1)).findById(anyLong());
		
	}

}
