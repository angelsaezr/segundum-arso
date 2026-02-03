package segundum.productos.servicio;

import java.time.LocalDate;

public class ProductoResumenMensual {

	private final String idProducto;
	private final String titulo;
	private final double precio;
	private final LocalDate fechaPublicacion;
	private final String nombreCategoria;
	private final int visualizaciones;

	public ProductoResumenMensual(String idProducto, String titulo, double precio, LocalDate fechaPublicacion,
			String nombreCategoria, int visualizaciones) {
		this.idProducto = idProducto;
		this.titulo = titulo;
		this.precio = precio;
		this.fechaPublicacion = fechaPublicacion;
		this.nombreCategoria = nombreCategoria;
		this.visualizaciones = visualizaciones;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public int getVisualizaciones() {
		return visualizaciones;
	}
}
