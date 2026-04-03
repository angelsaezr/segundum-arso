package segundum.usuarios.puerto;

import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.RepositorioException;

public interface ManejadorEventos {

	void compraventaCreada(String idComprador, String idVendedor) throws EntidadNoEncontrada, RepositorioException;
}
