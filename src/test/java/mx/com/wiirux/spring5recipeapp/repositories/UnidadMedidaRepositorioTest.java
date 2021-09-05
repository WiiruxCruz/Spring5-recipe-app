package mx.com.wiirux.spring5recipeapp.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//No encontró esta clase
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;

import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;


//@RunWith(SpringRunner.class) Esto es para JUnit 4
//En JUnit 5 no es necesario especificar el runWith, en su caso es ExtendWith para hacer retrocompatibilidad con 4
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnidadMedidaRepositorioTest {
	
	@Autowired
	UnidadMedidaRepositorio umr;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	//este es para usar un contexto sucio
	//@DirtiesContext
	void testFindByDescripcion() {
		Optional<UnidadMedida> umo = umr.findByDescripcion("Cucharada pequenia");
		assertEquals( "Cucharada pequenia", umo.get().getDescripcion() );
	}
	
	
	@Test
	void testFindByDescripcionTaza() {
		Optional<UnidadMedida> umo = umr.findByDescripcion("Taza");
		assertEquals( "Taza", umo.get().getDescripcion() );
	}
	

}
