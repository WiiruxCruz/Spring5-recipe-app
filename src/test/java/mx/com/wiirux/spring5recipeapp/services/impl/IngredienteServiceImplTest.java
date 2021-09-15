package mx.com.wiirux.spring5recipeapp.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.converters.IngredienteAIngredienteCommand;
import mx.com.wiirux.spring5recipeapp.converters.IngredienteCommandAIngrediente;
import mx.com.wiirux.spring5recipeapp.converters.UnidadMedidaAUnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.converters.UnidadMedidaCommandAUnidadMedida;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.repositories.UnidadMedidaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.IngredienteService;

class IngredienteServiceImplTest {
	
	private IngredienteAIngredienteCommand ingredienteAIngredienteCommand;
	private final IngredienteCommandAIngrediente ingredienteCommandAIngrediente;
	
	@Mock
	RecetaRepositorio recetaRepositorio;
	
	@Mock
	UnidadMedidaRepositorio unidadMedidaRepositorio;
	
	IngredienteService ingredienteService;
	
	public IngredienteServiceImplTest() {
		this.ingredienteAIngredienteCommand = new IngredienteAIngredienteCommand(new UnidadMedidaAUnidadMedidaCommand());
		this.ingredienteCommandAIngrediente = new IngredienteCommandAIngrediente(new UnidadMedidaCommandAUnidadMedida());
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredienteService = new IngredienteServiceImpl(
			ingredienteAIngredienteCommand,
			ingredienteCommandAIngrediente,
			recetaRepositorio,
			unidadMedidaRepositorio
		);
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
	
	@Test
	public void testGuardarRecetaCommand() throws Exception{
		//dado
		IngredienteCommand command = new IngredienteCommand();
		command.setId(3l);
		command.setRecetaId(2L);
		
		Optional<Receta> recetaOpcional = Optional.of(new Receta());
		
		Receta guardarReceta = new Receta();
		guardarReceta.agregarIngrediente(new Ingrediente());
		guardarReceta.getIngredientes().iterator().next().setId(3L);
		
		when(recetaRepositorio.findById(anyLong())).thenReturn(recetaOpcional);
		when(recetaRepositorio.save(Mockito.any())).thenReturn(guardarReceta);
		
		//cuando
		IngredienteCommand guardarCommand = ingredienteService.guardarIngredienteCommand(command);
		
		//entonces
		assertEquals(Long.valueOf(3L), guardarCommand.getId());
		verify(recetaRepositorio, times(1)).findById(anyLong());
		//verify(recetaRepositorio, times(1)).save(any(Receta.class));
		verify(recetaRepositorio, times(1)).save(Mockito.any(Receta.class));
	}

}
