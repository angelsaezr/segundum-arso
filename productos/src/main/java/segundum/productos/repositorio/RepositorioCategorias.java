package segundum.productos.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import segundum.productos.modelo.Categoria;

@NoRepositoryBean
public interface RepositorioCategorias extends PagingAndSortingRepository<Categoria, String> {

	List<Categoria> findCategoriasRaiz();

	List<Categoria> findDescendientesByRuta(String ruta);

	Page<Categoria> findCategoriasRaizPaginado(Pageable pageable);

	Page<Categoria> findDescendientesByRutaPaginado(String idCategoriaPadre, Pageable pageable);
}