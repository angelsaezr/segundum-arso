package segundum.productos.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import segundum.productos.modelo.EstadoProducto;

@Schema(description = "DTO de entrada para crear un nuevo producto")
public class NuevoProductoDTO {

	@NotNull
	@Schema(description = "Título del producto")
	private String titulo;
	@NotNull
	@Schema(description = "Descripción detallada del producto")
	private String descripcion;
	@NotNull
	@Min(0)
	@Schema(description = "Precio del producto en euros")
	private double precio;
	@Schema(description = "Estado del producto")
	private EstadoProducto estado;
	@Schema(description = "Indica si el envío del producto está disponible")
	private boolean envioDisponible;
	@NotNull
	@Schema(description = "Identificador de la categoría a la que pertenece el producto")
	private String idCategoria;
	@NotNull
	@Schema(description = "Identificador del vendedor del producto")
	private String idVendedor;

	public NuevoProductoDTO() {

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
