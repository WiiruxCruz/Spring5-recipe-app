package mx.com.wiirux.spring5recipeapp.services;

import java.util.Set;

import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;

public interface UnidadMedidaService {
	Set<UnidadMedidaCommand> listaTodasUnidadesMedidas();
}
