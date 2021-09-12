package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.NotasCommand;
import mx.com.wiirux.spring5recipeapp.domain.Notas;

class NotasANotasCommandTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String RECETA_NOTAS = "Notas";
	NotasANotasCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new NotasANotasCommand();
	}

	@Test
	public void testConvert() throws Exception{
		//dado
		Notas notas = new Notas();
		notas.setId(ID_VALUE);
		notas.setNotasRecetas(RECETA_NOTAS);
		
		//cuando
		NotasCommand notasCommand = converter.convert(notas);
		
		//entonces
		assertEquals(ID_VALUE, notasCommand.getId());
		assertEquals(RECETA_NOTAS, notasCommand.getNotasRecetas());
	}
	
	@Test
	public void testNull() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new Notas()));
	}
}
