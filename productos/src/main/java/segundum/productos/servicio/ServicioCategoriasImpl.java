package segundum.productos.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import segundum.productos.modelo.Categoria;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioCategorias;
import segundum.productos.rest.dto.CategoriaDTO;

@Service
@Transactional
public class ServicioCategoriasImpl implements ServicioCategorias {

	private RepositorioCategorias repositorioCategorias;

	@Autowired
	public ServicioCategoriasImpl(RepositorioCategorias repositorio) {
		this.repositorioCategorias = repositorio;
	}

	@Override
	public Categoria getCategoria(String id) throws EntidadNoEncontrada {
		Optional<Categoria> resultado = repositorioCategorias.findById(id);
		if (!resultado.isPresent())
			throw new EntidadNoEncontrada("No existe categoría con id: " + id);

		return resultado.get();
	}

	@Override
	public void cargarJerarquiaCategorias(String ruta) throws JAXBException, IOException {

		InputStream input = this.getClass().getClassLoader().getResourceAsStream(ruta);
		if (input == null)
			throw new IllegalArgumentException("No se encontró el recurso en el classpath: " + ruta);

		JAXBContext contexto = JAXBContext.newInstance(Categoria.class);
		Unmarshaller unmarshaller = contexto.createUnmarshaller();

		Categoria categoriaRaiz = (Categoria) unmarshaller.unmarshal(input);
		input.close();

		// Toda la jerarquía tiene bien enlazado el padre
		enlazarPadresRecursivamente(categoriaRaiz, null);

		// Persistir solo la raíz (gracias al cascade = ALL se guardará toda la
		// jerarquía)
		repositorioCategorias.save(categoriaRaiz);
	}

	@Override
	public List<Categoria> getCategoriasRaiz() {
		return repositorioCategorias.findCategoriasRaiz();
	}

	@Override
	public List<Categoria> getDescendientesCategoria(String idCategoria) throws EntidadNoEncontrada {
		Categoria categoria = getCategoria(idCategoria);
		return repositorioCategorias.findDescendientesByRuta(categoria.getRuta());
	}

	@Override
	public Page<CategoriaDTO> getCategoriasRaizPaginado(Pageable pageable) {
		Page<Categoria> categorias = repositorioCategorias.findCategoriasRaizPaginado(pageable);
		return categorias.map(CategoriaDTO::fromEntity);
	}

	@Override
	public Page<CategoriaDTO> getDescendientesPaginado(String idCategoria, Pageable pageable)
			throws EntidadNoEncontrada {
		// Verificar que la categoría existe
		getCategoria(idCategoria);
		Page<Categoria> categorias = repositorioCategorias.findDescendientesByRutaPaginado(idCategoria, pageable);
		return categorias.map(CategoriaDTO::fromEntityParaDescendientes);
	}

	// Asigna el categoriaPadre a todos los nodos de la jerarquía.
	private void enlazarPadresRecursivamente(Categoria categoria, Categoria padre) {
		categoria.setCategoriaPadre(padre);

		if (categoria.getSubcategorias() != null) {
			for (Categoria sub : categoria.getSubcategorias()) {
				enlazarPadresRecursivamente(sub, categoria);
			}
		}
	}
}
