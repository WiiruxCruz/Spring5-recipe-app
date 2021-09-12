package mx.com.wiirux.spring5recipeapp.services;

import java.util.Set;

import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.domain.Receta;

public interface RecetaService {
	Set<Receta> getRecetas();
	Receta buscarPorId(Long id);
	RecetaCommand buscarCommandPorId(Long id);
	RecetaCommand guardarRecetaCommand(RecetaCommand command);
}
