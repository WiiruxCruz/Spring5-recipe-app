package mx.com.wiirux.spring5recipeapp.services;

import java.util.Set;

import mx.com.wiirux.spring5recipeapp.domain.Receta;

public interface RecetaService {
	public Set<Receta> getRecetas();
	public Receta buscarPorId(Long id);
}
