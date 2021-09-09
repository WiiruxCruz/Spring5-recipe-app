package mx.com.wiirux.spring5recipeapp.commands;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.com.wiirux.spring5recipeapp.domain.Dificultad;

@Getter
@Setter
@NoArgsConstructor
public class RecetaCommand {
	private Long id;
	private String descripcion;
	private Integer tiempoPreparacion;
	private Integer tiempoCoccion;
	private Integer porciones;
	private String origen;
	private String url;
	private String direcciones;
	private Set<IngredienteCommand> ingredientes = new HashSet<>();
	private Dificultad dificultad;
	private NotasCommand notas;
	private Set<CategoriaCommand> categorias = new HashSet<>();
}
