package segundum.productos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import segundum.productos.modelo.Categoria;

@NoRepositoryBean
public interface RepositorioCategorias extends CrudRepository<Categoria, String> {

}
