package segundum.usuarios.repositorio;

import java.util.List;
import java.util.Set;

import segundum.usuarios.modelo.EstadoProducto;
import segundum.usuarios.modelo.Producto;

public interface RepositorioProductosAdHoc extends RepositorioString<Producto> {

	public List<Producto> buscarProductos(Set<String> idsCategoria, String texto,
			List<EstadoProducto> estadosPermitidos, Double precioMax) throws RepositorioException;
}
