package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;

@Component
public class IngredienteCommandAIngrediente implements Converter<IngredienteCommand, Ingrediente>{
	
	private final UnidadMedidaCommandAUnidadMedida umConverter;
	
	public IngredienteCommandAIngrediente(UnidadMedidaCommandAUnidadMedida umConverter) {
		// TODO Auto-generated constructor stub
		this.umConverter = umConverter;
	}
	
	@Nullable
	@Override
	public Ingrediente convert(IngredienteCommand source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(source.getId());
		ingrediente.setCantidad(source.getCantidad());
		ingrediente.setDescripcion(source.getDescripcion());
		ingrediente.setUnidadMedida(umConverter.convert(source.getUnidadMedida()));
		return ingrediente;
	}
}
