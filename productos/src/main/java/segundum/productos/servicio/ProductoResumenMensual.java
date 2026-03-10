package segundum.productos.servicio;

import java.time.LocalDateTime;

import segundum.productos.modelo.Producto;

public class ProductoResumenMensual {

	private String idProducto;
	private String titulo;
	private double precio;
	private LocalDateTime fechaPublicacion;
	private String nombreCategoria;
	private int visualizaciones;

	public String getIdProducto() {
		return idProducto;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public int getVisualizaciones() {
		return visualizaciones;
	}

	public String toString() {
		return "ProductoResumenMensual [idProducto=" + idProducto + ", titulo=" + titulo + ", precio=" + precio
				+ ", fechaPublicacion=" + fechaPublicacion + ", nombreCategoria=" + nombreCategoria
				+ ", visualizaciones=" + visualizaciones + "]";
	}

	public static ProductoResumenMensual fromEntity(Producto producto) {
		ProductoResumenMensual resumen = new ProductoResumenMensual();
		resumen.idProducto = producto.getId();
		resumen.titulo = producto.getTitulo();
		resumen.precio = producto.getPrecio();
		resumen.fechaPublicacion = producto.getFechaPublicacion();
		resumen.nombreCategoria = producto.getCategoria().getNombre();
		resumen.visualizaciones = producto.getVisualizaciones();
		return resumen;
	}
}
