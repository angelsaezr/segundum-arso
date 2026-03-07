package segundum.productos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import segundum.productos.modelo.Categoria;

@NoRepositoryBean
public interface RepositorioCategorias extends CrudRepository<Categoria, String> {

	@Query("SELECT c FROM Categoria c WHERE c.categoriaPadre IS NULL")
	List<Categoria> findCategoriasRaiz();

	@Query("SELECT c FROM Categoria c WHERE c.ruta LIKE CONCAT(:ruta, '/%')")
	List<Categoria> findDescendientesByRuta(@Param("ruta") String ruta);
}