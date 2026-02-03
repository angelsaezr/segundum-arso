package segundum.usuarios.servicio;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import segundum.usuarios.modelo.Categoria;
import segundum.usuarios.repositorio.EntidadNoEncontrada;
import segundum.usuarios.repositorio.RepositorioException;

public interface IServicioCategorias {

	void cargarJerarquiaCategorias(String ruta) throws RepositorioException, JAXBException, IOException;

	void modificarDescripcionCategoria(String idCategoria, String nuevaDescripcion)
			throws RepositorioException, EntidadNoEncontrada;

	List<Categoria> getCategoriasRaiz() throws RepositorioException, EntidadNoEncontrada;

	List<Categoria> getDescendientesCategoria(String idCategoria) throws RepositorioException, EntidadNoEncontrada;
}
