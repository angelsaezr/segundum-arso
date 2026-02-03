package segundum.usuarios.repositorio;

import segundum.usuarios.modelo.Usuario;

public interface RepositorioUsuariosAdHoc extends RepositorioString<Usuario> {
	Usuario buscarPorEmail(String email) throws RepositorioException;
}
