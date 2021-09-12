package mx.com.wiirux.spring5recipeapp.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.commands.RecetaCommand;
import mx.com.wiirux.spring5recipeapp.converters.RecetaARecetaCommand;
import mx.com.wiirux.spring5recipeapp.converters.RecetaCommandAReceta;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Service
public class RecetaServiceImpl implements RecetaService{
	private final RecetaRepositorio recetaRepositorio;
	private final RecetaCommandAReceta recetaCommandAReceta;
	private final RecetaARecetaCommand recetaARecetaCommand;
	
	public RecetaServiceImpl(
		RecetaRepositorio recetaRepositorio,
		RecetaCommandAReceta recetaCommandAReceta,
		RecetaARecetaCommand recetaARecetaCommand
	) {
		this.recetaRepositorio = recetaRepositorio;
		this.recetaCommandAReceta = recetaCommandAReceta;
		this.recetaARecetaCommand = recetaARecetaCommand;
	}

	@Override
	public Set<Receta> getRecetas() {
		// TODO Auto-generated method stub
		log.debug("Estoy en la implementación del servicio");
		Set<Receta> recetas = new HashSet<>();
		
		recetaRepositorio.findAll().iterator().forEachRemaining(recetas::add);
		return recetas;
	}

	@Override
	public Receta buscarPorId(Long id) {
		// TODO Auto-generated method stub
		Optional<Receta> recetaOpcional = recetaRepositorio.findById(id);
		
		if(!recetaOpcional.isPresent()) {
			throw new RuntimeException("Receta no encontrada");
		}
				
		return recetaOpcional.get();
	}
	
	@Override
	@Transactional
	public RecetaCommand buscarCommandPorId(Long id) {
		// TODO Auto-generated method stub
		return recetaARecetaCommand.convert( buscarPorId(id) );
	}

	@Override
	@Transactional
	public RecetaCommand guardarRecetaCommand(RecetaCommand command) {
		// TODO Auto-generated method stub
		Receta recetaSeparada = recetaCommandAReceta.convert(command);
		Receta recetaSalvada = recetaRepositorio.save(recetaSeparada);
		log.debug("Receta salvada ID:" + recetaSalvada.getId());
		return recetaARecetaCommand.convert(recetaSalvada);
	}
	
	@Override
	@Transactional
	public void borrarRecetaPorId(Long id) {
		recetaRepositorio.deleteById(id);
	}
}
