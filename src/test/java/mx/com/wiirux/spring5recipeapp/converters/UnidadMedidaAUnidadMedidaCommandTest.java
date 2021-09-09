package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;

class UnidadMedidaAUnidadMedidaCommandTest {
	
	public static final String DESCRIPCION = "descripcion";
	public static final Long LONG_VALUE = new Long(1L);
	
	UnidadMedidaAUnidadMedidaCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new UnidadMedidaAUnidadMedidaCommand();
	}
	
	@Test
	public void testNullObjectConvert() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObj() throws Exception{
		assertNotNull(converter.convert(new UnidadMedida()));
	}

	@Test
	void convert() throws Exception{
		//dado
		UnidadMedida unidadMedida = new UnidadMedida();
		unidadMedida.setId(LONG_VALUE);
		unidadMedida.setDescripcion(DESCRIPCION);
		
		//cuando
		UnidadMedidaCommand unidadMedidaCommand = converter.convert(unidadMedida);
		
		//entonces
		assertEquals(LONG_VALUE, unidadMedidaCommand.getId());
		assertEquals(DESCRIPCION, unidadMedidaCommand.getDescripcion());
	}

}
