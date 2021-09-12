package mx.com.wiirux.spring5recipeapp.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.com.wiirux.spring5recipeapp.domain.UnidadMedida;

@Getter
@Setter
@NoArgsConstructor
public class IngredienteCommand {
	private Long id;
	private String descripcion;
	private BigDecimal cantidad;
	private UnidadMedidaCommand unidadMedida;
}
