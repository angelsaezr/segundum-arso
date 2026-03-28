package segundum.productos.servicio;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import segundum.productos.modelo.Categoria;
import segundum.productos.repositorio.EntidadNoEncontrada;

public interface ServicioCategorias {

	void cargarJerarquiaCategorias(String ruta) throws JAXBException, IOException;

	Categoria getCategoria(String id) throws EntidadNoEncontrada;

	List<Categoria> getCategoriasRaiz() throws EntidadNoEncontrada;

	List<Categoria> getDescendientesCategoria(String idCategoria) throws EntidadNoEncontrada;
}
