package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;
import mx.com.wiirux.spring5recipeapp.domain.Receta;

class IngredienteCommandAIngredienteTest {
	
	public static final Receta RECETA = new Receta();
	public static final BigDecimal CANTIDAD = new BigDecimal("1");
	public static final String DESCRIPCION = "Hamburguesa de queso";
	public static final Long ID_VALUE = new Long(1L);
	public static final Long UM_ID = new Long(2L);
	
	IngredienteCommandAIngrediente converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new IngredienteCommandAIngrediente(new UnidadMedidaCommandAUnidadMedida());
	}
	
	@Test
	public void testNullObject() throws Exception{
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception{
		assertNotNull(converter.convert(new IngredienteCommand()));
	}

	@Test
	void testConvert() throws Exception{
		//dado
		IngredienteCommand command = new IngredienteCommand();
		command.setId(ID_VALUE);
		command.setCantidad(CANTIDAD);
		command.setDescripcion(DESCRIPCION);
		UnidadMedidaCommand unidadMedidaCommand = new UnidadMedidaCommand();
		unidadMedidaCommand.setId(UM_ID);
		command.setUnidadMedida(unidadMedidaCommand);
		
		//cuando
		Ingrediente ingrediente = converter.convert(command);
		
		//entonces
		assertNotNull(ingrediente);
		assertNotNull(ingrediente.getUnidadMedida());
		assertEquals(ID_VALUE, ingrediente.getId());
		assertEquals(CANTIDAD, ingrediente.getCantidad());
		assertEquals(DESCRIPCION, ingrediente.getDescripcion());
		assertEquals(UM_ID, ingrediente.getUnidadMedida().getId());
	}
	
	@Test
	public void convertirConNulaUnidadMedida() throws Exception {
		//dado
		IngredienteCommand command = new IngredienteCommand();
		command.setId(ID_VALUE);
		command.setCantidad(CANTIDAD);
		command.setDescripcion(DESCRIPCION);
		UnidadMedidaCommand unidadMedidaCommand = new UnidadMedidaCommand();
		
		//cuando
		Ingrediente ingrediente = converter.convert(command);
		
		//entonces
		assertNotNull(ingrediente);
		assertNull(ingrediente.getUnidadMedida());
		assertEquals(ID_VALUE, ingrediente.getId());
		assertEquals(CANTIDAD, ingrediente.getCantidad());
		assertEquals(DESCRIPCION, ingrediente.getDescripcion());
	}

}
