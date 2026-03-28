package segundum.productos.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.rest.dto.ProductoResumenDTO;

public interface ServicioProductos {

	Producto getProducto(String id) throws EntidadNoEncontrada;

	String crear(String titulo, String descripcion, double precio, EstadoProducto estado, boolean envioDisponible,
			String idCategoria, String idVendedor) throws EntidadNoEncontrada;

	void modificarDatosProducto(String idProducto, Double nuevoPrecio, String nuevaDescripcion)
			throws EntidadNoEncontrada;

	void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcionLugar)
			throws EntidadNoEncontrada;

	void incrementarVisualizaciones(String idProducto) throws EntidadNoEncontrada;

	Page<ProductoResumenMensual> getResumenMensual(String idVendedor, int mes, int año, Pageable pageable) throws EntidadNoEncontrada;

	List<Producto> buscarProductos(String descripcion, String idCategoria, EstadoProducto estado, Double precioMax);

	Page<ProductoResumenDTO> getListadoPaginado(Pageable pageable);
}