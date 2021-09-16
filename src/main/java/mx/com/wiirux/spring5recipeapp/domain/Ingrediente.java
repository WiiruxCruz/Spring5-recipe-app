package mx.com.wiirux.spring5recipeapp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"receta"})
@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	private BigDecimal cantidad;
	
	@ManyToOne
	private Receta receta;
	
	@OneToOne(fetch = FetchType.EAGER)
	private UnidadMedida unidadMedida;
	
	public Ingrediente() {
	}
	
	public Ingrediente(String descripcion, BigDecimal cantidad, UnidadMedida unidadMedida) {
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
	}
	
	public Ingrediente(String descripcion, BigDecimal cantidad, UnidadMedida unidadMedida, Receta receta) {
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.unidadMedida = unidadMedida;
		this.receta = receta;
	}	
	
}
