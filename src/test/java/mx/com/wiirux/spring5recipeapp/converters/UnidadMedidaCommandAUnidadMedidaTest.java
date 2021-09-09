package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;

class UnidadMedidaCommandAUnidadMedidaTest {
	
	public static final String DESCRIPCION = "descripcion";
	public static final Long LONG_VALUE = new Long(1L);
	
	UnidadMedidaCommandAUnidadMedida converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new UnidadMedidaCommandAUnidadMedida();
	}

	@Test
	void testNullParameter() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new UnidadMedidaCommand()));
	}
	
	@Test
	public void convert() throws Exception{
		//dado
		UnidadMedidaCommand command = new UnidadMedidaCommand();
		command.setId(LONG_VALUE);
		command.setDescripcion(DESCRIPCION);
		
		//cuando
		UnidadMedida um = converter.convert(command);
		
		//entonces
		assertNotNull(um);
		assertEquals(LONG_VALUE, um.getId());
		assertEquals(DESCRIPCION, um.getDescripcion());
	}

}
