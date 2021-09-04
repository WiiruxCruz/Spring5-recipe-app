package mx.com.wiirux.spring5recipeapp.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Service
public class RecetaServiceImpl implements RecetaService{
	private final RecetaRepositorio rr;
	
	public RecetaServiceImpl(RecetaRepositorio rr) {
		this.rr = rr;
	}

	@Override
	public Set<Receta> getRecetas() {
		// TODO Auto-generated method stub
		log.debug("Estoy en la implementación del servicio");
		Set<Receta> recetas = new HashSet<>();
		
		rr.findAll().iterator().forEachRemaining(recetas::add);
		return recetas;
	}
	
	
}
