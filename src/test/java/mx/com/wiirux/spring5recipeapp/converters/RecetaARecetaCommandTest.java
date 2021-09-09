package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Categoria;
import mx.com.wiirux.spring5recipeapp.domain.Dificultad;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;
import mx.com.wiirux.spring5recipeapp.domain.Notas;
import mx.com.wiirux.spring5recipeapp.domain.Receta;

class RecetaARecetaCommandTest {
	
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
    RecetaARecetaCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new RecetaARecetaCommand(
			new CategoriaACategoriaCommand(),
			new IngredienteAIngredienteCommand( new UnidadMedidaAUnidadMedidaCommand() ),
			new NotasANotasCommand()
		);
	}
	
	@Test
	public void testNullObject() throws Exception{
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new Receta()));
	}

	@Test
	void convert() throws Exception {
		//dado
		Receta receta = new Receta();
		receta.setId(RECETA_ID);
		receta.setTiempoCooccion(TIEMPO_COOCCION);
		receta.setTiempoPreparacion(TIEMPO_PREPARACION);
		receta.setDescripcion(DESCRIPCION);
		receta.setDificultad(DIFICULTAD);
		receta.setDirecciones(INDICACIONES);
		receta.setPorciones(PORCIONES);
		receta.setOrigen(ORIGEN);
		receta.setUrl(URL);
		
		Notas notas = new Notas();
		notas.setId(NOTAS_ID);
		
		receta.setNotas(notas);
		
		Categoria categoria = new Categoria();
		categoria.setId(CAT_ID_1);
		
		Categoria categoria2 = new Categoria();
		categoria2.setId(CAT_ID_2);
		
		receta.getCategorias().add(categoria);
		receta.getCategorias().add(categoria2);
		
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(INGREDIENTE_ID_1);
		
		Ingrediente ingrediente2 = new Ingrediente();
		ingrediente2.setId(INGREDIENTE_ID_2);
		
		receta.getIngredientes().add(ingrediente);
		receta.getIngredientes().add(ingrediente2);
		
		//cuando
		RecetaCommand command = converter.convert(receta);
		
		//entonces
		assertNotNull(command);
		assertEquals(RECETA_ID, command.getId());
		assertEquals(TIEMPO_COOCCION, command.getTiempoCoccion());
		assertEquals(TIEMPO_PREPARACION, command.getTiempoPreparacion());
		assertEquals(DESCRIPCION, command.getDescripcion());
		assertEquals(DIFICULTAD, command.getDificultad());
		assertEquals(INDICACIONES, command.getDirecciones());
		assertEquals(ORIGEN, command.getOrigen());
		assertEquals(URL, command.getUrl());
		assertEquals(NOTAS_ID, command.getNotas().getId());
		assertEquals(2, command.getCategorias().size());
		assertEquals(2, command.getIngredientes().size());
	}

}
