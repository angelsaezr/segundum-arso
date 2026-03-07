package segundum.productos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import segundum.productos.modelo.Categoria;

@Repository
public interface RepositorioCategoriasJPA extends RepositorioCategorias, JpaRepository<Categoria, String> {

	@Query("SELECT c FROM Categoria c WHERE c.categoriaPadre IS NULL")
	List<Categoria> findCategoriasRaiz();

	@Query("SELECT c FROM Categoria c WHERE c.ruta LIKE CONCAT(:ruta, '/%')")
	List<Categoria> findDescendientesByRuta(@Param("ruta") String ruta);
}