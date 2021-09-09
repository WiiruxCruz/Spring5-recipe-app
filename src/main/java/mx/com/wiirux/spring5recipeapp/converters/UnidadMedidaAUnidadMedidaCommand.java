package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;

@Component
public class UnidadMedidaAUnidadMedidaCommand implements Converter<UnidadMedida, UnidadMedidaCommand>{
	
	@Synchronized
	@Nullable
	@Override
	public UnidadMedidaCommand convert(UnidadMedida unidadMedida) {
		// TODO Auto-generated method stub
		if(unidadMedida != null) {
			final UnidadMedidaCommand umc = new UnidadMedidaCommand();
			umc.setId(unidadMedida.getId());
			umc.setDescripcion(unidadMedida.getDescripcion());
			return umc;
		}
		
		return null;
	}
	
}
