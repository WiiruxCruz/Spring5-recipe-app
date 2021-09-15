package mx.com.wiirux.spring5recipeapp.services.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import mx.com.wiirux.spring5recipeapp.commands.UnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.converters.UnidadMedidaAUnidadMedidaCommand;
import mx.com.wiirux.spring5recipeapp.repositories.UnidadMedidaRepositorio;
import mx.com.wiirux.spring5recipeapp.services.UnidadMedidaService;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService{
	
	private final UnidadMedidaRepositorio unidadMedidaRepositorio;
	private final UnidadMedidaAUnidadMedidaCommand unidadMedidaAUnidadMedidaCommand;
	
	public UnidadMedidaServiceImpl(
		UnidadMedidaRepositorio unidadMedidaRepositorio,
		UnidadMedidaAUnidadMedidaCommand unidadMedidaAUnidadMedidaCommand
	) {
		this.unidadMedidaRepositorio = unidadMedidaRepositorio;
		this.unidadMedidaAUnidadMedidaCommand = unidadMedidaAUnidadMedidaCommand;
	}



	@Override
	public Set<UnidadMedidaCommand> listaTodasUnidadesMedidas() {
		// TODO Auto-generated method stub
		return StreamSupport.stream(
			unidadMedidaRepositorio.findAll().spliterator(), false
		)
		.map(unidadMedidaAUnidadMedidaCommand::convert)
		.collect(Collectors.toSet())
		;
	}
	
}
