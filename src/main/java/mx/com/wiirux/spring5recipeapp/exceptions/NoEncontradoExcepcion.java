package mx.com.wiirux.spring5recipeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoEncontradoExcepcion extends RuntimeException{
	public NoEncontradoExcepcion() {
		super();
	}
	
	public NoEncontradoExcepcion(String mensaje) {
		super(mensaje);
	}
	
	public NoEncontradoExcepcion(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
