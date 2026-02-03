package segundum.productos.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import segundum.productos.modelo.Categoria;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.FactoriaRepositorios;
import segundum.productos.repositorio.Repositorio;
import segundum.productos.repositorio.RepositorioException;

public class ServicioCategorias implements IServicioCategorias {

	private Repositorio<Categoria, String> repositorioCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);

	@Override
	public void cargarJerarquiaCategorias(String ruta) throws RepositorioException, JAXBException, IOException {

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
		repositorioCategorias.add(categoriaRaiz);
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
	public void modificarDescripcionCategoria(String idCategoria, String nuevaDescripcion)
			throws RepositorioException, EntidadNoEncontrada {
		if (idCategoria == null || idCategoria.isEmpty())
			throw new IllegalArgumentException("idCategoria: no debe ser nulo ni vacío");
		if (nuevaDescripcion == null || nuevaDescripcion.isEmpty())
			throw new IllegalArgumentException("nuevaDescripcion: no debe ser nula ni vacía");

		Categoria categoria = repositorioCategorias.getById(idCategoria);
		categoria.setDescripcion(nuevaDescripcion);
		repositorioCategorias.update(categoria);
	}

	@Override
	public List<Categoria> getCategoriasRaiz() throws RepositorioException {
		List<Categoria> todas = repositorioCategorias.getAll();
		List<Categoria> raiz = new ArrayList<>();

		for (Categoria c : todas) {
			if (c.getCategoriaPadre() == null) {
				raiz.add(c);
			}
		}

		return raiz;
	}

	@Override
	public List<Categoria> getDescendientesCategoria(String idCategoria)
			throws RepositorioException, EntidadNoEncontrada {
		if (idCategoria == null || idCategoria.isEmpty())
			throw new IllegalArgumentException("idCategoria: no debe ser nulo ni vacío");

		Categoria categoria = repositorioCategorias.getById(idCategoria);
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
