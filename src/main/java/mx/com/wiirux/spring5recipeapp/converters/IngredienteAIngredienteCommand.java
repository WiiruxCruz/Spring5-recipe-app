package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;

@Component
public class IngredienteAIngredienteCommand implements Converter<Ingrediente, IngredienteCommand>{
	
	private final UnidadMedidaAUnidadMedidaCommand umConverter;
	
	public IngredienteAIngredienteCommand(UnidadMedidaAUnidadMedidaCommand umConverter) {
		// TODO Auto-generated constructor stub
		this.umConverter = umConverter;
	}
	
	@Synchronized
	@Nullable
	@Override
	public IngredienteCommand convert(Ingrediente ingrediente) {
		// TODO Auto-generated method stub
		if(ingrediente == null) {
			return null;
		}
		
		IngredienteCommand ingredienteCommand = new IngredienteCommand();
		ingredienteCommand.setId(ingrediente.getId());
		ingredienteCommand.setCantidad(ingrediente.getCantidad());
		ingredienteCommand.setDescripcion(ingrediente.getDescripcion());
		ingredienteCommand.setUnidadMedida(umConverter.convert(ingrediente.getUnidadMedida()));
		return ingredienteCommand;
	}

}
