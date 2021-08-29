package mx.com.wiirux.spring5recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mx.com.wiirux.spring5recipeapp.domain.Categoria;

public interface CategoriaRepositorio extends CrudRepository<Categoria, Long>{
	
	Optional <Categoria> findByDescripcion(String descripcion);
}
