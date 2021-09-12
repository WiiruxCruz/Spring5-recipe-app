package mx.com.wiirux.spring5recipeapp.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredienteCommand {
	private Long id;
	private Long recetaId;
	private String descripcion;
	private BigDecimal cantidad;
	private UnidadMedidaCommand unidadMedida;
}
