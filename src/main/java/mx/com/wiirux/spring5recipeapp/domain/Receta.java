package mx.com.wiirux.spring5recipeapp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@Getter
//@Setter
//@Transactional
@Entity
public class Receta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	private Integer tiempoPreparacion;
	private Integer tiempoCooccion;
	private Integer porciones;
	private String origen;
	private String url;
	
	@Lob
	private String direcciones;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "receta")
	private Set<Ingrediente> ingredientes = new HashSet<>();;
	
	@Lob
	private Byte[] imagen;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Notas notas;
	
	@Enumerated(value = EnumType.STRING)
	private Dificultad dificultad;
	
	@ManyToMany
	@JoinTable(
		name = "receta_categoria",
		joinColumns = @JoinColumn(name = "receta_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private Set<Categoria> categorias = new HashSet<>();
	
	public void setNotas(Notas notas) {
		this.notas = notas;
		notas.setReceta(this);
	}
	public Receta agregarIngrediente(Ingrediente ingrediente) {
		ingrediente.setReceta(this);
		this.ingredientes.add(ingrediente);
		return this;
	}
}
