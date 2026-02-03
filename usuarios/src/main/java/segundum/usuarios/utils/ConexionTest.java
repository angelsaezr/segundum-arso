package segundum.usuarios.utils;

import javax.persistence.EntityManager;

public class ConexionTest {
	public static void main(String[] args) {
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManager();
			em.getTransaction().begin();
			System.out.println("Conexión a la BD establecida correctamente.");
			em.getTransaction().commit();
		} catch (Exception e) {
			System.err.println("Error de conexión: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
			JPAUtil.close();
		}
	}
}
