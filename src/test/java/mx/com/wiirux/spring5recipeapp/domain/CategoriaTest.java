package mx.com.wiirux.spring5recipeapp.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoriaTest {
	
	Categoria categoria;

	@BeforeEach
	void setUp() throws Exception {
		categoria = new Categoria();
	}

	@Test
	void testSetId() {
		//fail("Not yet implemented");
		Long id = 4L;
		categoria.setId(id);
		
		assertEquals(id, categoria.getId());
	}

	@Test
	void testSetDescripcion() {
		//fail("Not yet implemented");
	}

	@Test
	void testSetRecetas() {
		//fail("Not yet implemented");
	}

}
