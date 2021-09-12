package mx.com.wiirux.spring5recipeapp.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.converters.IngredienteAIngredienteCommand;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.IngredienteService;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Service
public class IngredienteServiceImpl implements IngredienteService{
	
	private final IngredienteAIngredienteCommand ingredienteAIngredienteCommand;
	private final RecetaRepositorio recetaRepositorio;
	
	public IngredienteServiceImpl(IngredienteAIngredienteCommand ingredienteAIngredienteCommand,
			RecetaRepositorio recetaRepositorio) {
		this.ingredienteAIngredienteCommand = ingredienteAIngredienteCommand;
		this.recetaRepositorio = recetaRepositorio;
	}
	
	@Override
	public IngredienteCommand buscarPorRecetaIdEIngredienteId(Long recetaId, Long ingredienteId) {
		// TODO Auto-generated method stub
		
		Optional<Receta> recetaOpcional = recetaRepositorio.findById(recetaId);
		
		if(!recetaOpcional.isPresent()) {
			log.error("Receta id no encontrada con id:" + recetaId);
		}
		
		Receta receta = recetaOpcional.get();
		
		Optional<IngredienteCommand> ingredienteCommandOpcional = receta.getIngredientes()
				.stream()
				.filter( ingrediente -> ingrediente.getId().equals(ingredienteId))
				.map( ingrediente -> ingredienteAIngredienteCommand.convert(ingrediente))
				.findFirst()
				;
		
		if(!ingredienteCommandOpcional.isPresent()) {
			log.error("Ingrediente id no encontrado con id:" + ingredienteId);
		}
		
		return ingredienteCommandOpcional.get();
	}

}
