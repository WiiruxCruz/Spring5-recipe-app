package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.CategoriaCommand;
import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.commands.NotasCommand;
import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Dificultad;
import mx.com.wiirux.spring5recipeapp.domain.Receta;

class RecetaCommandARecetaTest {
	
	public static final Long RECETA_ID = 1L;
	public static final Integer TIEMPO_COOCCION = Integer.valueOf(5);
	public static final Integer TIEMPO_PREPARACION = Integer.valueOf(7);
	public static final String DESCRIPCION = "Mi Receta";
	public static final String INDICACIONES = "Indicaciones";
	public static final Dificultad DIFICULTAD = Dificultad.FACIL;
	public static final Integer PORCIONES = Integer.valueOf(3);
	public static final String ORIGEN = "Origen";
	public static final String URL = "Alguna URL";
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID_2 = 2L;
	public static final Long INGREDIENTE_ID_1 = 3L;
	public static final Long INGREDIENTE_ID_2 = 4L;
	public static final Long NOTAS_ID = 9L;
	
	RecetaCommandAReceta converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new RecetaCommandAReceta(
			new CategoriaCommandACategoria(),
			new IngredienteCommandAIngrediente( new UnidadMedidaCommandAUnidadMedida() ),
			new NotasCommandANotas()
		);
	}

	@Test
	void testNullObject() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull( converter.convert( new RecetaCommand() ) );
		//assertNull( converter.convert( null ) );
	}
	
	@Test
	public void convert() throws Exception{
		//dado
		RecetaCommand recetaCommand = new RecetaCommand();
		recetaCommand.setId(RECETA_ID);
		recetaCommand.setTiempoCoccion(TIEMPO_COOCCION);
		recetaCommand.setTiempoPreparacion(TIEMPO_PREPARACION);
		recetaCommand.setDescripcion(DESCRIPCION);
		recetaCommand.setDificultad(DIFICULTAD);
		recetaCommand.setDirecciones(INDICACIONES);
		recetaCommand.setPorciones(PORCIONES);
		recetaCommand.setOrigen(ORIGEN);
		recetaCommand.setUrl(URL);
		
		NotasCommand notas = new NotasCommand();
		notas.setId(NOTAS_ID);
		
		recetaCommand.setNotas(notas);
		
		CategoriaCommand categoria = new CategoriaCommand();
		categoria.setId(CAT_ID_1);
		
		CategoriaCommand categoria2 = new CategoriaCommand();
		categoria.setId(CAT_ID_2);
		
		recetaCommand.getCategorias().add(categoria);
		recetaCommand.getCategorias().add(categoria2);
		
		IngredienteCommand ingrediente = new IngredienteCommand();
		ingrediente.setId(INGREDIENTE_ID_1);
		
		IngredienteCommand ingrediente2 = new IngredienteCommand();
		ingrediente.setId(INGREDIENTE_ID_2);
		
		recetaCommand.getIngredientes().add(ingrediente);
		recetaCommand.getIngredientes().add(ingrediente2);
		
		//cuando
		Receta receta = converter.convert(recetaCommand);
		
		assertNotNull(receta);
		assertEquals(RECETA_ID, receta.getId());
		assertEquals(TIEMPO_COOCCION, receta.getTiempoCoccion());
		assertEquals(TIEMPO_PREPARACION, receta.getTiempoPreparacion());
		assertEquals(DESCRIPCION, receta.getDescripcion());
		assertEquals(DIFICULTAD, receta.getDificultad());
		assertEquals(INDICACIONES, receta.getDirecciones());
		assertEquals(PORCIONES, receta.getPorciones());
		assertEquals(URL, receta.getUrl());
		assertEquals(NOTAS_ID, receta.getNotas().getId());
		assertEquals(2, receta.getCategorias().size());
		assertEquals(2, receta.getIngredientes().size());
		
		//entonces
	}

}
