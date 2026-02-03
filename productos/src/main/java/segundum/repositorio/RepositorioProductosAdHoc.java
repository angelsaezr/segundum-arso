package segundum.repositorio;

import java.util.List;
import java.util.Set;

import segundum.modelo.EstadoProducto;
import segundum.modelo.Producto;

public interface RepositorioProductosAdHoc extends RepositorioString<Producto> {

	public List<Producto> buscarProductos(Set<String> idsCategoria, String texto,
			List<EstadoProducto> estadosPermitidos, Double precioMax) throws RepositorioException;
}
