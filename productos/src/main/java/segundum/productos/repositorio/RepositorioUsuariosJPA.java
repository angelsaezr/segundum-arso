package segundum.productos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import segundum.productos.modelo.Usuario;

@Repository
public interface RepositorioUsuariosJPA extends RepositorioUsuarios, JpaRepository<Usuario, String> {

}