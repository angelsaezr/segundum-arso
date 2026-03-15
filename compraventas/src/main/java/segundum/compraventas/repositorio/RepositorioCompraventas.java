package segundum.compraventas.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import segundum.compraventas.modelo.Compraventa;

@NoRepositoryBean
public interface RepositorioCompraventas extends CrudRepository<Compraventa, String> {
	Optional<Compraventa> findByTitulo(String titulo);
}
