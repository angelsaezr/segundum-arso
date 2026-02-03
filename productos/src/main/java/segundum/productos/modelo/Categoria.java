package segundum.productos.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import segundum.productos.repositorio.Identificable;

@Entity
@Table(name = "categorias")
@XmlRootElement(name = "categoria")
@XmlAccessorType(XmlAccessType.FIELD)
public class Categoria implements Identificable {

	@Id
	@XmlAttribute
	private String id;

	@XmlElement
	private String nombre;

	@Lob
	@XmlTransient
	private String descripcion;
	
	@Lob
	@XmlAttribute
	private String ruta;

	@OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.ALL)
	@XmlElement(name = "categoria")
	private List<Categoria> subcategorias = new LinkedList<>();

	@ManyToOne
	@XmlTransient
	private Categoria categoriaPadre;

	// Constructores
	public Categoria() {

	}

	public Categoria(String id, String nombre, String descripcion, String ruta) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ruta = ruta;
		this.subcategorias = new LinkedList<Categoria>();
	}

	// Getters y Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public Categoria getCategoriaPadre() {
		return categoriaPadre;
	}

	public void setCategoriaPadre(Categoria categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}

	// Métodos auxiliares
	public void addSubcategoria(Categoria subcategoria) {
		subcategorias.add(subcategoria);
		subcategoria.setCategoriaPadre(this);
	}

	public void removeSubcategoria(Categoria subcategoria) {
		subcategorias.remove(subcategoria);
		subcategoria.setCategoriaPadre(null);
	}

	@Override
	public String toString() {
		return "Categoria{" + "id='" + id + '\'' + ", nombre='" + nombre + '\'' + ", descripcion='" + descripcion + '\''
				+ ", ruta='" + ruta + '\'' + ", subcategorias=" + subcategorias.size() + '}';
	}
}
