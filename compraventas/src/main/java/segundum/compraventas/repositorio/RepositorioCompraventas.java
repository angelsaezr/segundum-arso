package segundum.compraventas.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import segundum.compraventas.modelo.Compraventa;

@NoRepositoryBean
public interface RepositorioCompraventas extends CrudRepository<Compraventa, String> {
	List<Compraventa> findByIdComprador(String idComprador);

	List<Compraventa> findByIdVendedor(String idVendedor);

	List<Compraventa> findByIdCompradorAndIdVendedor(String idComprador, String idVendedor);
}