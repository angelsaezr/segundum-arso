package segundum.productos.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import segundum.productos.modelo.Categoria;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioCategorias;

@Service
@Transactional
public class ServicioCategorias implements IServicioCategorias {

	private RepositorioCategorias repositorioCategorias;

	@Autowired
	public ServicioCategorias(RepositorioCategorias repositorio) {
		this.repositorioCategorias = repositorio;
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

	// Asigna el categoriaPadre a todos los nodos de la jerarquía.
	private void enlazarPadresRecursivamente(Categoria categoria, Categoria padre) {
		categoria.setCategoriaPadre(padre);

		if (categoria.getSubcategorias() != null) {
			for (Categoria sub : categoria.getSubcategorias()) {
				enlazarPadresRecursivamente(sub, categoria);
			}
		}
	}

	@Override
	public void modificarDescripcionCategoria(String idCategoria, String nuevaDescripcion) throws EntidadNoEncontrada {
		if (idCategoria == null || idCategoria.isEmpty())
			throw new IllegalArgumentException("idCategoria: no debe ser nulo ni vacío");
		if (nuevaDescripcion == null || nuevaDescripcion.isEmpty())
			throw new IllegalArgumentException("nuevaDescripcion: no debe ser nula ni vacía");

		Optional<Categoria> resultado = repositorioCategorias.findById(idCategoria);
		if (resultado.isPresent() == false)
			throw new EntidadNoEncontrada("No existe categoría con id: " + idCategoria);

		Categoria categoria = resultado.get();
		categoria.setDescripcion(nuevaDescripcion);
		repositorioCategorias.save(categoria);
	}

	@Override
	public List<Categoria> getCategoriasRaiz() {
		List<Categoria> todas = new ArrayList<>();
		for (Categoria c : repositorioCategorias.findAll()) {
			todas.add(c);
		}
		List<Categoria> raiz = new ArrayList<>();

		for (Categoria c : todas) {
			if (c.getCategoriaPadre() == null) {
				raiz.add(c);
			}
		}

		return raiz;
	}

	@Override
	public List<Categoria> getDescendientesCategoria(String idCategoria) throws EntidadNoEncontrada {
		if (idCategoria == null || idCategoria.isEmpty())
			throw new IllegalArgumentException("idCategoria: no debe ser nulo ni vacío");

		Optional<Categoria> resultado = repositorioCategorias.findById(idCategoria);
		if (resultado.isPresent() == false)
			throw new EntidadNoEncontrada("No existe categoría con id: " + idCategoria);

		Categoria categoria = resultado.get();
		List<Categoria> descendientes = new ArrayList<>();
		obtenerDescendientesRecursivos(categoria, descendientes);

		return descendientes;
	}

	private void obtenerDescendientesRecursivos(Categoria categoria, List<Categoria> lista) {
		if (categoria.getSubcategorias() == null)
			return;

		for (Categoria sub : categoria.getSubcategorias()) {
			lista.add(sub);
			obtenerDescendientesRecursivos(sub, lista);
		}
	}
}
