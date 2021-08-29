package mx.com.wiirux.spring5recipeapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;

public interface UnidadMedidaRepositorio extends CrudRepository<UnidadMedida, Long>{
	Optional<UnidadMedida> findByDescripcion(String descripcion);
	
}
