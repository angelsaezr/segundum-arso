package segundum.productos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import segundum.productos.modelo.Usuario;

@NoRepositoryBean
public interface RepositorioUsuarios extends CrudRepository<Usuario, String> {

}
