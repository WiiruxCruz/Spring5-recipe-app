package mx.com.wiirux.spring5recipeapp.services;

import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;

public interface IngredienteService {
	IngredienteCommand buscarPorRecetaIdEIngredienteId(Long recetaId, Long ingredienteId);
	IngredienteCommand guardarIngredienteCommand(IngredienteCommand command);
}
