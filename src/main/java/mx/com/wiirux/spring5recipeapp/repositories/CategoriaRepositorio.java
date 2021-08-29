package mx.com.wiirux.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.wiirux.spring5recipeapp.domain.Categoria;

public interface CategoriaRepositorio extends CrudRepository<Categoria, Long>{
	
}
