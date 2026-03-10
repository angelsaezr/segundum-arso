package segundum.productos.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;

@NoRepositoryBean
public interface RepositorioProductos extends PagingAndSortingRepository<Producto, String> {

	List<Producto> buscarProductos(Set<String> idsCategoria, String texto, List<EstadoProducto> estadosPermitidos,
			Double precioMax);

	Page<Producto> findResumenMensual(String idVendedor, LocalDateTime inicio, LocalDateTime fin, Pageable pageable);
}
