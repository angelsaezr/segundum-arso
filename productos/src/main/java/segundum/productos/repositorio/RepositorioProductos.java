package segundum.productos.repositorio;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;

@NoRepositoryBean
public interface RepositorioProductos extends CrudRepository<Producto, String> {

	public List<Producto> buscarProductos(Set<String> idsCategoria, String texto,
			List<EstadoProducto> estadosPermitidos, Double precioMax);
}
