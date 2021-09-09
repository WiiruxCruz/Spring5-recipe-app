package mx.com.wiirux.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoriaCommand {
	private Long id;
	private String descripcion;
}
