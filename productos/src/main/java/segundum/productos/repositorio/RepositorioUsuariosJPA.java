package segundum.productos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import segundum.productos.modelo.Usuario;

public interface RepositorioUsuariosJPA extends RepositorioUsuarios, JpaRepository<Usuario, String> {

}