package mx.com.wiirux.spring5recipeapp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Receta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	private Integer tiempoPreparacion;
	private Integer tiempoCooccion;
	private String origen;
	private String url;
	
	@Lob
	private String direcciones;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receta")
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
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getTiempoPreparacion() {
		return tiempoPreparacion;
	}
	public void setTiempoPreparacion(Integer tiempoPreparacion) {
		this.tiempoPreparacion = tiempoPreparacion;
	}
	public Integer getTiempoCooccion() {
		return tiempoCooccion;
	}
	public void setTiempoCooccion(Integer tiempoCooccion) {
		this.tiempoCooccion = tiempoCooccion;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDirecciones() {
		return direcciones;
	}
	public void setDirecciones(String direcciones) {
		this.direcciones = direcciones;
	}
	public Byte[] getImagen() {
		return imagen;
	}
	public void setImagen(Byte[] imagen) {
		this.imagen = imagen;
	}
	public Notas getNotas() {
		return notas;
	}
	public void setNotas(Notas notas) {
		this.notas = notas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(Set<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public Dificultad getDificultad() {
		return dificultad;
	}
	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}
	public Set<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}
	
}
