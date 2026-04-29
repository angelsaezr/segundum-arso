package segundum.productos.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import segundum.productos.modelo.Producto;

@Schema(description = "DTO de respuesta con los datos resumidos de un producto")
public class ProductoResumenDTO {

	@Schema(description = "Identificador único del producto")
	private String idProducto;
	@Schema(description = "Título del producto")
	private String titulo;
	@Schema(description = "Precio del producto en euros")
	private double precio;

	public String getIdProducto() {
		return idProducto;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public String toString() {
		return "ProductoResumen [idProducto=" + idProducto + ", titulo=" + titulo + ", precio=" + precio + "]";
	}

	public static ProductoResumenDTO fromEntity(Producto producto) {
		ProductoResumenDTO resumen = new ProductoResumenDTO();
		resumen.idProducto = producto.getId();
		resumen.titulo = producto.getTitulo();
		resumen.precio = producto.getPrecio();
		return resumen;
	}
}
