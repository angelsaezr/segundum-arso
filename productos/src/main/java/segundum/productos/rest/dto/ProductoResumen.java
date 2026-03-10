package segundum.productos.rest.dto;

import segundum.productos.modelo.Producto;

public class ProductoResumen {

	private String idProducto;
	private String titulo;
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

	public static ProductoResumen fromEntity(Producto producto) {
		ProductoResumen resumen = new ProductoResumen();
		resumen.idProducto = producto.getId();
		resumen.titulo = producto.getTitulo();
		resumen.precio = producto.getPrecio();
		return resumen;
	}
}
