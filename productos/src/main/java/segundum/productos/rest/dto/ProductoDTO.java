package segundum.productos.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import segundum.productos.modelo.Producto;

@Schema(description = "DTO de respuesta con los datos completos de un producto")
public class ProductoDTO {

	@Schema(description = "Identificador único del producto")
	private String id;
	@Schema(description = "Título del producto")
	private String titulo;
	@Schema(description = "Descripción detallada del producto")
	private String descripcion;
	@Schema(description = "Precio del producto en euros")
	private double precio;
	@Schema(description = "Estado del producto")
	private String estado;
	@Schema(description = "Indica si el envío del producto está disponible")
	private boolean envioDisponible;
	@Schema(description = "Identificador de la categoría a la que pertenece el producto")
	private String idCategoria;
	@Schema(description = "Identificador del vendedor del producto")
	private String idVendedor;

	public ProductoDTO() {

	}

	public ProductoDTO(String id, String titulo, String descripcion, double precio, String estado,
			boolean envioDisponible, String idCategoria, String idVendedor) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.envioDisponible = envioDisponible;
		this.idCategoria = idCategoria;
		this.idVendedor = idVendedor;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
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

	public static ProductoDTO fromEntity(Producto producto) {
		return new ProductoDTO(producto.getId(), producto.getTitulo(), producto.getDescripcion(), producto.getPrecio(),
				producto.getEstado().toString(), producto.isEnvioDisponible(), producto.getCategoria().getId(),
				producto.getVendedor().getId());
	}
}
