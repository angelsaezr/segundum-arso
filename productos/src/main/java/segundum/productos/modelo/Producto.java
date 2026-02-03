package segundum.productos.modelo;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import segundum.productos.repositorio.Identificable;

@Entity
@Table(name = "productos")
public class Producto implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String id;

	private String titulo;

	@Lob
	private String descripcion;

	private double precio;

	@Enumerated(EnumType.STRING)
	private EstadoProducto estado;

	private LocalDateTime fechaPublicacion;

	private int visualizaciones;

	private boolean envioDisponible;

	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Usuario vendedor;

	@Embedded
	private LugarDeRecogida lugarDeRecogida;

	public Producto() {

	}

	public Producto(String titulo, String descripcion, double precio, EstadoProducto estado, boolean envioDisponible,
			Categoria categoria, Usuario vendedor, LugarDeRecogida lugarDeRecogida) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.fechaPublicacion = LocalDateTime.now();
		this.visualizaciones = 0;
		this.envioDisponible = envioDisponible;
		this.categoria = categoria;
		this.vendedor = vendedor;
		this.lugarDeRecogida = lugarDeRecogida;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getVisualizaciones() {
		return visualizaciones;
	}

	public void setVisualizaciones(int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	public boolean isEnvioDisponible() {
		return envioDisponible;
	}

	public void setEnvioDisponible(boolean envioDisponible) {
		this.envioDisponible = envioDisponible;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public LugarDeRecogida getLugarDeRecogida() {
		return lugarDeRecogida;
	}

	public void setLugarDeRecogida(LugarDeRecogida recogida) {
		this.lugarDeRecogida = recogida;
	}

	// Métodos auxiliares
	public void incrementarVisualizaciones() {
		this.visualizaciones++;
	}

	@Override
	public String toString() {
		return "Producto{" + "id='" + id + '\'' + ", titulo='" + titulo + '\'' + ", precio=" + precio + ", estado="
				+ estado + ", categoria=" + (categoria != null ? categoria.getNombre() : "N/A") + ", vendedor="
				+ (vendedor != null ? vendedor.getEmail() : "N/A") + ", visualizaciones=" + visualizaciones + '}';
	}
}
