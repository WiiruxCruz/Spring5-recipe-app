package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.CategoriaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Categoria;

@Component
public class CategoriaACategoriaCommand implements Converter<Categoria, CategoriaCommand>{

	@Synchronized
	@Nullable
	@Override
	public CategoriaCommand convert(Categoria source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final CategoriaCommand categoriaCommand = new CategoriaCommand();
		categoriaCommand.setId(source.getId());
		categoriaCommand.setDescripcion(source.getDescripcion());
		return categoriaCommand;
	}

}
