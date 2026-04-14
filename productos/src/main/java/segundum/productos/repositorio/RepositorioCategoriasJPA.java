package segundum.productos.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import segundum.productos.modelo.Categoria;

@Repository
public interface RepositorioCategoriasJPA extends RepositorioCategorias, JpaRepository<Categoria, String> {

	@Query("SELECT c FROM Categoria c WHERE c.categoriaPadre IS NULL")
	List<Categoria> findCategoriasRaiz();

	@Query("SELECT c FROM Categoria c WHERE c.ruta LIKE CONCAT(:ruta, '%') AND c.ruta != :ruta")
	List<Categoria> findDescendientesByRuta(@Param("ruta") String ruta);

	@Query("SELECT c FROM Categoria c WHERE c.categoriaPadre IS NULL")
	Page<Categoria> findCategoriasRaizPaginado(Pageable pageable);

	@Query("SELECT c FROM Categoria c WHERE c.categoriaPadre.id = :idCategoriaPadre")
	Page<Categoria> findDescendientesByRutaPaginado(@Param("idCategoriaPadre") String idCategoriaPadre, Pageable pageable);
}