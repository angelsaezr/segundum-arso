package segundum.productos.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import segundum.productos.modelo.Categoria;

@NoRepositoryBean
public interface RepositorioCategorias extends CrudRepository<Categoria, String> {

	List<Categoria> findCategoriasRaiz();

	List<Categoria> findDescendientesByRuta(String ruta);
}