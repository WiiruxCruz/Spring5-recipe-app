package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;

@Component
public class UnidadMedidaCommandAUnidadMedida implements Converter<UnidadMedidaCommand, UnidadMedida>{
	
	@Synchronized
	@Nullable
	@Override
	public UnidadMedida convert(UnidadMedidaCommand source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final UnidadMedida um = new UnidadMedida();
		um.setId(source.getId());
		um.setDescripcion(source.getDescripcion());
		return um;
	}
	

}
