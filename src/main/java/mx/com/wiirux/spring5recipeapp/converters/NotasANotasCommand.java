package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.NotasCommand;
import mx.com.wiirux.spring5recipeapp.domain.Notas;

@Component
public class NotasANotasCommand implements Converter<Notas, NotasCommand>{

	@Synchronized
	@NotNull
	@Override
	public NotasCommand convert(Notas source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final NotasCommand notasCommand = new NotasCommand();
		notasCommand.setId(source.getId());
		notasCommand.setNotasReceta(source.getNotasRecetas());
		
		return notasCommand;
	}
	
}
