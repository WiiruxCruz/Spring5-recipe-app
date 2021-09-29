package mx.com.wiirux.spring5recipeapp.commands;

import java.util.HashSet;
import java.util.Set;


import org.hibernate.validator.constraints.URL;
//import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.com.wiirux.spring5recipeapp.domain.Dificultad;

@Getter
@Setter
@NoArgsConstructor
public class RecetaCommand {
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 255)
	private String descripcion;
	
	@Min(1)
	@Max(999)
	private Integer tiempoPreparacion;
	
	@Min(1)
	@Max(999)
	private Integer tiempoCoccion;
	
	@Min(1)
	@Max(100)
	private Integer porciones;
	private String origen;
	
	@URL
	private String url;
	
	@NotBlank
	private String direcciones;
	
	
	private Set<IngredienteCommand> ingredientes = new HashSet<>();
	private Byte[] imagen;
	private Dificultad dificultad;
	private NotasCommand notas;
	private Set<CategoriaCommand> categorias = new HashSet<>();
}
