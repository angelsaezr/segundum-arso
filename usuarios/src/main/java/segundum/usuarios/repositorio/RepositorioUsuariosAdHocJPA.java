package segundum.usuarios.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.utils.EntityManagerHelper;

public class RepositorioUsuariosAdHocJPA extends RepositorioUsuariosJPA implements RepositorioUsuariosAdHoc {

	@Override
	public Class<Usuario> getClase() {
		return Usuario.class;
	}

	@Override
	public Usuario buscarPorEmail(String email) throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
			query.setParameter("email", email);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);

			return query.getSingleResult();
		} catch (Exception e) {
			throw new RepositorioException("Error buscando usuario por email", e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public Usuario buscarPorGithubId(String githubId) throws RepositorioException, EntidadNoEncontrada {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.githubId = :githubId",
					Usuario.class);
			query.setParameter("githubId", githubId);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);

			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new EntidadNoEncontrada("No existe usuario con githubId: " + githubId);
		} catch (Exception e) {
			throw new RepositorioException("Error buscando usuario por githubId", e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
}