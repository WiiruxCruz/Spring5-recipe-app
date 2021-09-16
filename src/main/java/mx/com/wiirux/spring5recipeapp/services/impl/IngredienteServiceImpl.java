package mx.com.wiirux.spring5recipeapp.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.wiirux.spring5recipeapp.commands.IngredienteCommand;
import mx.com.wiirux.spring5recipeapp.converters.IngredienteAIngredienteCommand;
import mx.com.wiirux.spring5recipeapp.converters.IngredienteCommandAIngrediente;
import mx.com.wiirux.spring5recipeapp.domain.Ingrediente;
import mx.com.wiirux.spring5recipeapp.domain.Receta;
import mx.com.wiirux.spring5recipeapp.repositories.RecetaRepositorio;import mx.com.wiirux.spring5recipeapp.repositories.UnidadMedidaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.IngredienteService;
import mx.com.wiirux.spring5recipeapp.services.RecetaService;

@Slf4j
@Service
public class IngredienteServiceImpl implements IngredienteService{
	
	private final IngredienteAIngredienteCommand ingredienteAIngredienteCommand;
	private final IngredienteCommandAIngrediente ingredienteCommandAIngrediente;
	private final RecetaRepositorio recetaRepositorio;
	private final UnidadMedidaRepositorio unidadMedidaRepositorio;
	
	public IngredienteServiceImpl(
		IngredienteAIngredienteCommand ingredienteAIngredienteCommand,
		IngredienteCommandAIngrediente ingredienteCommandAIngrediente,
		RecetaRepositorio recetaRepositorio,
		UnidadMedidaRepositorio unidadMedidaRepositorio
	) {
		this.ingredienteAIngredienteCommand = ingredienteAIngredienteCommand;
		this.ingredienteCommandAIngrediente = ingredienteCommandAIngrediente;
		this.recetaRepositorio = recetaRepositorio;
		this.unidadMedidaRepositorio = unidadMedidaRepositorio;
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
	
	@Override
	@Transactional
	public IngredienteCommand guardarIngredienteCommand(IngredienteCommand command) {
		Optional<Receta> recetaOpcional = recetaRepositorio.findById(command.getRecetaId());
		
		if(!recetaOpcional.isPresent()) {
			log.error("Receta no encontrada por id:" + command.getRecetaId() );
			return new IngredienteCommand();
		} else {
			Receta receta = recetaOpcional.get();
			Optional<Ingrediente> ingredienteOpcional = receta
					.getIngredientes()
					.stream()
					.filter( ingrediente -> ingrediente.getId().equals( command.getId() ) )
					.findFirst()
					;
			
			if(ingredienteOpcional.isPresent()) {
				Ingrediente ingredienteEncontrado = ingredienteOpcional.get();
				ingredienteEncontrado.setDescripcion( command.getDescripcion() );
				ingredienteEncontrado.setCantidad( command.getCantidad() );
				ingredienteEncontrado.setUnidadMedida( unidadMedidaRepositorio
					.findById( command.getUnidadMedida().getId() )
					.orElseThrow(
						() -> new RuntimeException("Unidad Medida no encontrada")
					)
				);
			} else {
				//agregar nuevo ingrediente
				Ingrediente ingrediente = ingredienteCommandAIngrediente.convert(command);
				ingrediente.setReceta(receta);
				receta.agregarIngrediente( ingredienteCommandAIngrediente.convert(command) );
			}
			
			Receta guardarReceta = recetaRepositorio.save(receta);
			
			Optional<Ingrediente> guardarIngredienteOpcional = guardarReceta.getIngredientes().stream()
					.filter(recetaIngredientes -> recetaIngredientes.getId().equals(command.getId()))
					.findFirst()
					;
			
			//revisar por descripcion
			if(!guardarIngredienteOpcional.isPresent()) {
				//no totalmente seguro, pero mejor supocicion, but best guess
				guardarIngredienteOpcional = guardarReceta.getIngredientes().stream()
					.filter(recetaIngredientes -> recetaIngredientes.getDescripcion().equals(command.getDescripcion()))
					.filter(recetaIngredientes -> recetaIngredientes.getCantidad().equals(command.getCantidad()))
					.filter(recetaIngredientes -> recetaIngredientes.getUnidadMedida().getId().equals(command.getUnidadMedida().getId()))
					.findFirst()
					;
			}
			
			//hacer check por fallo
			return ingredienteAIngredienteCommand.convert(guardarIngredienteOpcional.get());
		}
	}
	
	@Override
	@Transactional
	public void borrarIngredientePorId(Long recetaId, Long ingredienteId) {
		log.debug("Borrar ingrediente: " + recetaId + ":" + ingredienteId);
		
		Optional<Receta> recetaOpcional = recetaRepositorio.findById(recetaId);
		
		if(recetaOpcional.isPresent()) {
			Receta receta = recetaOpcional.get();
			log.debug("encontré receta");
			
			Optional<Ingrediente> ingredienteOpcional = receta
					.getIngredientes()
					.stream()
					.filter( ingrediente -> ingrediente.getId().equals(ingredienteId) )
					.findFirst()
					;
			
			if(ingredienteOpcional.isPresent()) {
				log.debug("Encontré ingrediente");
				Ingrediente ingredienteBorrar = ingredienteOpcional.get();
				ingredienteBorrar.setReceta(null);
				receta.getIngredientes().remove(ingredienteOpcional.get());
				recetaRepositorio.save(receta);
			}
		} else {
			log.debug("Receta id no encontrada. Id:" + recetaId);
		}
	}

}
