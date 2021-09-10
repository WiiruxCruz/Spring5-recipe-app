package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Categoria;
import mx.com.wiirux.spring5recipeapp.domain.Receta;

@Component
public class RecetaARecetaCommand implements Converter<Receta, RecetaCommand>{
	
	private final CategoriaACategoriaCommand categoriaConverter;
	private final IngredienteAIngredienteCommand ingredienteConverter;
	private final NotasANotasCommand notasConverter;
	
	public RecetaARecetaCommand(
			CategoriaACategoriaCommand categoriaConverter,
			IngredienteAIngredienteCommand ingredienteConverter,
			NotasANotasCommand notasConverter
	) {
		// TODO Auto-generated constructor stub
		this.categoriaConverter = categoriaConverter;
		this.ingredienteConverter = ingredienteConverter;
		this.notasConverter = notasConverter;
	}
	
	@Synchronized
	@NotNull
	@Override
	public RecetaCommand convert(Receta source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final RecetaCommand command = new RecetaCommand();
		command.setId(source.getId());
		command.setTiempoCoccion(source.getTiempoCoccion());
		command.setTiempoPreparacion(source.getTiempoPreparacion());
		command.setDescripcion(source.getDescripcion());
		command.setDificultad(source.getDificultad());
		command.setDirecciones(source.getDirecciones());
		command.setPorciones(source.getPorciones());
		command.setOrigen(source.getOrigen());
		command.setUrl(source.getUrl());
		command.setNotas(notasConverter.convert(source.getNotas()));
		
		if(source.getCategorias() != null && source.getCategorias().size() > 0) {
			source.getCategorias()
			.forEach( (Categoria categoria) -> command.getCategorias().add( categoriaConverter.convert(categoria) ) );
		}
		
		if(source.getIngredientes() != null && source.getIngredientes().size() > 0) {
			source.getIngredientes()
			.forEach(ingrediente -> command.getIngredientes().add(ingredienteConverter.convert(ingrediente)));
		}
		
		return command;
	}

}
