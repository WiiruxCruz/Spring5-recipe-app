package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.CategoriaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Categoria;

class CategoriaCommandACategoriaTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String DESCRIPCION = "descripcción";
	CategoriaACategoriaCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new CategoriaACategoriaCommand();
	}
	
	@Test
	public void testNullObject() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new Categoria()));
	}

	@Test
	void testConvert() throws Exception{
		//dado
		Categoria categoria = new Categoria();
		categoria.setId(ID_VALUE);
		categoria.setDescripcion(DESCRIPCION);
		
		//cuando
		CategoriaCommand categoriaCommand = converter.convert(categoria);
		
		//entonces
		assertEquals(ID_VALUE, categoriaCommand.getId());
		assertEquals(DESCRIPCION, categoriaCommand.getDescripcion());
	}

}
