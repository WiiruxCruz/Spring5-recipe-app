package mx.com.wiirux.spring5recipeapp.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;
import mx.com.wiirux.spring5recipeapp.domain.Receta;

class IngredienteAIngredienteCommandTest {
	
	public static final Receta RECETA = new Receta();
	public static final BigDecimal CANTIDAD = new BigDecimal("1");
	public static final String DESCRIPCION = "Hamburguesa de queso";
	public static final Long UM_ID = new Long(2L);
	public static final Long ID_VALUE = new Long(1L);
	
	IngredienteAIngredienteCommand converter;

	@BeforeEach
	void setUp() throws Exception {
		converter = new IngredienteAIngredienteCommand(new UnidadMedidaAUnidadMedidaCommand());
	}
	
	@Test
	public void testNullConvert() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new Ingrediente()));
	}

	@Test
	public void testConvertNullUnidadMedida() throws Exception{
		//dado
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(ID_VALUE);
		ingrediente.setReceta(RECETA);
		ingrediente.setCantidad(CANTIDAD);
		ingrediente.setDescripcion(DESCRIPCION);
		ingrediente.setUnidadMedida(null);
		
		//cuando
		IngredienteCommand ingredienteCommand = converter.convert(ingrediente);
		
		//entonces
		assertNull(ingredienteCommand.getUnidadMedidaCommand());
		assertEquals(ID_VALUE, ingredienteCommand.getId());
		//assertEquals(RECETA, ingredienteCommand.get);
		assertEquals(CANTIDAD, ingredienteCommand.getCantidad());
		assertEquals(DESCRIPCION, ingredienteCommand.getDescripcion());
	}

}
