package segundum.productos.servicio;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import segundum.productos.modelo.Categoria;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioException;

public interface IServicioCategorias {

	void cargarJerarquiaCategorias(String ruta) throws RepositorioException, JAXBException, IOException;

	void modificarDescripcionCategoria(String idCategoria, String nuevaDescripcion)
			throws RepositorioException, EntidadNoEncontrada;

	List<Categoria> getCategoriasRaiz() throws RepositorioException, EntidadNoEncontrada;

	List<Categoria> getDescendientesCategoria(String idCategoria) throws RepositorioException, EntidadNoEncontrada;
}
