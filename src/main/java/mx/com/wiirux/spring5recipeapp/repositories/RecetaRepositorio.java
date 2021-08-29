package mx.com.wiirux.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.wiirux.spring5recipeapp.domain.Receta;

//
public interface RecetaRepositorio extends CrudRepository<Receta, Long>{
	
}
