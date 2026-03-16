package segundum.productos.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;

@Repository
public interface RepositorioProductosJPA extends RepositorioProductos, JpaRepository<Producto, String> {

	@Query("SELECT p FROM Producto p LEFT JOIN p.categoria c "
			+ "WHERE (:idsCategoria IS NULL OR c.id IN :idsCategoria) "
			+ "AND (:texto IS NULL OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :texto, '%')) "
			+ "     OR LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :texto, '%'))) "
			+ "AND (:estadosPermitidos IS NULL OR p.estado IN :estadosPermitidos) "
			+ "AND (:precioMax IS NULL OR p.precio <= :precioMax)")
	List<Producto> buscarProductos(@Param("idsCategoria") Set<String> idsCategoria, @Param("texto") String texto,
			@Param("estadosPermitidos") List<EstadoProducto> estadosPermitidos, @Param("precioMax") Double precioMax);

	@Query("SELECT p FROM Producto p " + "WHERE p.vendedor.id = :idVendedor " + "AND p.fechaPublicacion >= :inicio "
			+ "AND p.fechaPublicacion <= :fin " + "ORDER BY p.visualizaciones DESC")
	Page<Producto> findResumenMensual(@Param("idVendedor") String idVendedor, @Param("inicio") LocalDateTime inicio,
			@Param("fin") LocalDateTime fin, Pageable pageable);
}