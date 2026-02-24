package segundum.productos.servicio;

import java.util.List;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.repositorio.EntidadNoEncontrada;

public interface IServicioProductos {

	String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			boolean envioDisponible, String idCategoria, String idVendedor, String descripcionRecogida, double longitud,
			double latitud) throws EntidadNoEncontrada;

	void modificarDatosProducto(String idProducto, Double nuevoPrecio, String nuevaDescripcion)
			throws EntidadNoEncontrada;

	void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcionLugar)
			throws EntidadNoEncontrada;

	void incrementarVisualizaciones(String idProducto) throws EntidadNoEncontrada;

	public List<ProductoResumenMensual> getResumenMensual(String idVendedor, int mes, int año)
			throws EntidadNoEncontrada;

	List<Producto> buscarProductos(String descripcion, String idCategoria, EstadoProducto estado, Double precioMax);
}