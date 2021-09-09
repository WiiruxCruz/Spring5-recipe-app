package mx.com.wiirux.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnidadMedidaCommand {
	private Long id;
	private String descripcion;
}
