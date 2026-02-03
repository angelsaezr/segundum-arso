package segundum.servicio;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import segundum.modelo.Categoria;
import segundum.repositorio.EntidadNoEncontrada;
import segundum.repositorio.RepositorioException;

public interface IServicioCategorias {

	void cargarJerarquiaCategorias(String ruta) throws RepositorioException, JAXBException, IOException;

	void modificarDescripcionCategoria(String idCategoria, String nuevaDescripcion)
			throws RepositorioException, EntidadNoEncontrada;

	List<Categoria> getCategoriasRaiz() throws RepositorioException, EntidadNoEncontrada;

	List<Categoria> getDescendientesCategoria(String idCategoria) throws RepositorioException, EntidadNoEncontrada;
}
