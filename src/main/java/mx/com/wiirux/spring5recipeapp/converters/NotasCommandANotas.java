package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.NotasCommand;
import mx.com.wiirux.spring5recipeapp.domain.Notas;

@Component
public class NotasCommandANotas implements Converter<NotasCommand, Notas>{

	@Synchronized
	@Nullable
	@Override
	public Notas convert(NotasCommand source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final Notas notas = new Notas();
		notas.setId(source.getId());
		notas.setNotasRecetas(source.getNotasRecetas());
		
		return notas;
	}

}
