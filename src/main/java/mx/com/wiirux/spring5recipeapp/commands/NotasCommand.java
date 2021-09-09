package mx.com.wiirux.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotasCommand {
	private Long id;
	private String notasReceta;
}
