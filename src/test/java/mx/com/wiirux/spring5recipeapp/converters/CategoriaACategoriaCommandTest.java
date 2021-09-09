package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.CategoriaCommand;
import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Categoria;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;

class CategoriaACategoriaCommandTest {
	
	public static final String DESCRIPCION = "descripcion";
	public static final Long LONG_VALUE = new Long(1L);
	
	CategoriaACategoriaCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new CategoriaACategoriaCommand();
	}
	
	@Test
	public void testNullParameter() throws Exception{
		assertNull(converter.convert( null ));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert( new Categoria() ));
	}
	
	@Test
	void testConvert() throws Exception {
		//dado
		Categoria command = new Categoria();
		command.setId(LONG_VALUE);
		command.setDescripcion(DESCRIPCION);
		
		//cuando
		CategoriaCommand unidadMedida = converter.convert(command);
		
		//entonces
		assertNotNull(unidadMedida);
		assertEquals(LONG_VALUE, unidadMedida.getId());
		assertEquals(DESCRIPCION, unidadMedida.getDescripcion());
	}

}
