package mx.com.wiirux.spring5recipeapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ManejadorExcepcionController {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView ManejadorNumeroFormatoExcepcion(Exception exception) {
		log.error("Manejador de formato de numero excepcion");
		log.error(exception.getMessage());
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("error400");
		mav.addObject("excepcion", exception);
		
		return mav;
	}
	
}
