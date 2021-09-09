package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.CategoriaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Categoria;

@Component
public class CategoriaCommandACategoria implements Converter<CategoriaCommand, Categoria>{
	
	@Synchronized
	@Nullable
	@Override
	public Categoria convert(CategoriaCommand source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final Categoria categoria = new Categoria();
		categoria.setId(source.getId());
		categoria.setDescripcion(source.getDescripcion());
		return categoria;
	}
	
}
