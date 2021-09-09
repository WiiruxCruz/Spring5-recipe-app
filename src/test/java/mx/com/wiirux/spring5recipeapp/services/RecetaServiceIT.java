package mx.com.wiirux.spring5recipeapp.services;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.converters.RecetaARecetaCommand;
import mx.com.wiirux.spring5recipeapp.converters.RecetaCommandAReceta;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

//@RunWith(SpringRunner.class)

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RecetaServiceIT {
	
	public static final String NUEVA_DESCRIPCION = "Nueva descripción";
	
	@Autowired
	RecetaService recetaService;
	
	@Autowired
	RecetaRepositorio recetaRepositorio;
	
	@Autowired
	RecetaCommandAReceta recetaCommandAReceta;
	
	@Autowired
	RecetaARecetaCommand recetaARecetaCommand;
	
	@Transactional
	@Test
	void test() throws Exception {
		//dado
		Iterable<Receta> recetas = recetaRepositorio.findAll();
		Receta testReceta = recetas.iterator().next();
		RecetaCommand testRecetaCommand = recetaARecetaCommand.convert(testReceta);
		
		//cuando
		testRecetaCommand.setDescripcion(NUEVA_DESCRIPCION);
		RecetaCommand guardarRecetaCommand = recetaService.guardarRecetaCommand(testRecetaCommand);
		
		//entonces
		assertEquals(NUEVA_DESCRIPCION, guardarRecetaCommand.getDescripcion());
		assertEquals(testReceta.getId(), guardarRecetaCommand.getId());
		assertEquals(testReceta.getCategorias().size(), guardarRecetaCommand.getCategorias().size());
		assertEquals(testReceta.getIngredientes().size(), guardarRecetaCommand.getIngredientes().size());
		
	}

}
