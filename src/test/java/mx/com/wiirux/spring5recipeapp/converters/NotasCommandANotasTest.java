package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.NotasCommand;
import mx.com.wiirux.spring5recipeapp.domain.Notas;

class NotasCommandANotasTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String RECETA_NOTAS = "Notas";
	NotasCommandANotas converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new NotasCommandANotas();
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new NotasCommand()));
	}

	@Test
	void testConvert() throws Exception{
		//dado
		NotasCommand notasCommand = new NotasCommand();
		notasCommand.setId(ID_VALUE);
		notasCommand.setNotasRecetas(RECETA_NOTAS);
		
		//cuando
		Notas notas = converter.convert(notasCommand);
		
		//entonces
		assertNotNull(notas);
		assertEquals(ID_VALUE, notas.getId());
		assertEquals(RECETA_NOTAS, notas.getNotasRecetas());
	}

}
