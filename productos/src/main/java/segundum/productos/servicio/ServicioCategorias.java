package segundum.productos.servicio;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import segundum.productos.modelo.Categoria;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.rest.dto.CategoriaDTO;

public interface ServicioCategorias {

	void cargarJerarquiaCategorias(String ruta) throws JAXBException, IOException;

	Categoria getCategoria(String id) throws EntidadNoEncontrada;

	List<Categoria> getCategoriasRaiz() throws EntidadNoEncontrada;

	List<Categoria> getDescendientesCategoria(String idCategoria) throws EntidadNoEncontrada;

	Page<CategoriaDTO> getCategoriasRaizPaginado(Pageable pageable);

	Page<CategoriaDTO> getDescendientesPaginado(String idCategoria, Pageable pageable) throws EntidadNoEncontrada;
}
