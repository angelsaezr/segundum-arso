package segundum.productos.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.utils.EntityManagerHelper;

public class RepositorioProductosAdHocJPA extends RepositorioProductosJPA implements RepositorioProductosAdHoc {

	@Override
	public Class<Producto> getClase() {
		return Producto.class;
	}

	@Override
	public List<Producto> buscarProductos(Set<String> idsCategoria, String texto,
			List<EstadoProducto> estadosPermitidos, Double precioMax) throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT p FROM Producto p ");

			List<String> condiciones = new ArrayList<>();

			if (idsCategoria != null && !idsCategoria.isEmpty()) {
				sb.append("JOIN p.categoria c ");
				condiciones.add("c.id IN :idsCategoria");
			}

			if (texto != null && !texto.trim().isEmpty()) {
				condiciones.add("(LOWER(p.titulo) LIKE :texto OR LOWER(p.descripcion) LIKE :texto)");
			}

			if (estadosPermitidos != null && !estadosPermitidos.isEmpty()) {
				condiciones.add("p.estado IN :estados");
			}

			if (precioMax != null) {
				condiciones.add("p.precio <= :precioMax");
			}

			if (!condiciones.isEmpty()) {
				sb.append("WHERE ").append(String.join(" AND ", condiciones));
			}

			TypedQuery<Producto> query = em.createQuery(sb.toString(), Producto.class);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE); // forzar refresco si hay cache
																// [web:95][web:98][web:100]

			if (idsCategoria != null && !idsCategoria.isEmpty()) {
				query.setParameter("idsCategoria", idsCategoria);
			}

			if (texto != null && !texto.trim().isEmpty()) {
				String pattern = "%" + texto.toLowerCase() + "%";
				query.setParameter("texto", pattern);
			}

			if (estadosPermitidos != null && !estadosPermitidos.isEmpty()) {
				query.setParameter("estados", estadosPermitidos);
			}

			if (precioMax != null) {
				query.setParameter("precioMax", precioMax);
			}

			return query.getResultList();
		} catch (RuntimeException e) {
			throw new RepositorioException("Error buscando productos", e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

}
