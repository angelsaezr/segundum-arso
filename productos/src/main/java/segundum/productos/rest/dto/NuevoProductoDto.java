package segundum.productos.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import segundum.productos.modelo.EstadoProducto;

public class NuevoProductoDto {

	@NotNull
	private String titulo;
	@NotNull
	private String descripcion;
	@NotNull
	@Min(0)
	private double precio;
	private EstadoProducto estado;
	private boolean envioDisponible;
	@NotNull
	private String idCategoria;
	@NotNull
	private String idVendedor;

	public NuevoProductoDto() {

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

	public boolean isEnvioDisponible() {
		return envioDisponible;
	}

	public void setEnvioDisponible(boolean envioDisponible) {
		this.envioDisponible = envioDisponible;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}

}
