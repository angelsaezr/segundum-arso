package segundum.productos.servicio;

import java.util.List;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioException;

public interface IServicioProductos {

	String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			boolean envioDisponible, String idCategoria, String idVendedor, String descripcionRecogida, double longitud,
			double latitud) throws RepositorioException, EntidadNoEncontrada;

	void modificarDatosProducto(String idProducto, Double nuevoPrecio, String nuevaDescripcion)
			throws RepositorioException, EntidadNoEncontrada;

	void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcionLugar)
			throws RepositorioException, EntidadNoEncontrada;

	void incrementarVisualizaciones(String idProducto) throws RepositorioException, EntidadNoEncontrada;

	public List<ProductoResumenMensual> getResumenMensual(String idVendedor, int mes, int año)
			throws RepositorioException, EntidadNoEncontrada;

	List<Producto> buscarProductos(String descripcion, String idCategoria, EstadoProducto estado, Double precioMax)
			throws RepositorioException;
}