package mx.com.wiirux.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;

import lombok.Synchronized;
import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Receta;

@Component
public class RecetaCommandAReceta implements Converter<RecetaCommand, Receta>{
	
	private final CategoriaCommandACategoria categoriaConverter;
	private final IngredienteCommandAIngrediente ingredienteConverter;
	private final NotasCommandANotas notasConverter;
	
	public RecetaCommandAReceta(
			CategoriaCommandACategoria categoriaConverter,
			IngredienteCommandAIngrediente ingredienteConverter,
			NotasCommandANotas notasConverter
	) {
		// TODO Auto-generated constructor stub
		this.categoriaConverter = categoriaConverter;
		this.ingredienteConverter = ingredienteConverter;
		this.notasConverter = notasConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Receta convert(RecetaCommand source) {
		// TODO Auto-generated method stub
		if(source == null) {
			return null;
		}
		
		final Receta receta = new Receta();
		receta.setId(source.getId());
		receta.setTiempoCoccion(source.getTiempoCoccion());
		receta.setTiempoPreparacion(source.getTiempoPreparacion());
		receta.setDescripcion(source.getDescripcion());
		receta.setDificultad(source.getDificultad());
		receta.setDirecciones(source.getDirecciones());
		receta.setPorciones(source.getPorciones());
		receta.setOrigen(source.getOrigen());
		receta.setUrl(source.getUrl());
		receta.setNotas(notasConverter.convert(source.getNotas()));
		
		if(
			source.getCategorias() != null
			&& source.getCategorias().size() > 0
		) {
			source.getCategorias()
			.forEach( categoria -> receta.getCategorias().add(categoriaConverter.convert(categoria)));
		}
		
		if(
			source.getIngredientes() != null
			&& source.getIngredientes().size() > 0
		) {
			source.getIngredientes()
			.forEach( ingrediente -> receta.getIngredientes().add(ingredienteConverter.convert(ingrediente)));
		}
		
		return receta;
	}

}
