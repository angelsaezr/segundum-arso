package segundum.compraventas.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import segundum.compraventas.modelo.Compraventa;

@Repository
public interface RepositorioCompraventasMongo extends RepositorioCompraventas, MongoRepository<Compraventa, String> {

}
