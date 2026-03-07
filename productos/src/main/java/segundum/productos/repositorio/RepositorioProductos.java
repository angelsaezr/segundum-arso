package segundum.productos.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;

@NoRepositoryBean
public interface RepositorioProductos extends CrudRepository<Producto, String> {

	List<Producto> buscarProductos(Set<String> idsCategoria, String texto, List<EstadoProducto> estadosPermitidos,
			Double precioMax);

	List<Producto> findResumenMensual(String idVendedor, LocalDateTime inicio, LocalDateTime fin);
}
