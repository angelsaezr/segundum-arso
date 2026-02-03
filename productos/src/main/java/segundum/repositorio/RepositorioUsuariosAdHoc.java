package segundum.repositorio;

import segundum.modelo.Usuario;

public interface RepositorioUsuariosAdHoc extends RepositorioString<Usuario> {
	Usuario buscarPorEmail(String email) throws RepositorioException;
}
