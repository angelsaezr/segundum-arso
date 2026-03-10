package segundum.productos.servicio;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import segundum.productos.modelo.Categoria;
import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.Producto;
import segundum.productos.modelo.Usuario;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioCategorias;
import segundum.productos.repositorio.RepositorioProductos;
import segundum.productos.rest.dto.ProductoResumen;

@Service
public class ServicioProductos implements IServicioProductos {

	private RepositorioCategorias repositorioCategorias;
	private RepositorioProductos repositorioProductos;
	private ServicioUsuarios servicioUsuarios;
	private ServicioCategorias servicioCategorias;

	@Autowired
	public ServicioProductos(RepositorioCategorias repositorioCategorias, RepositorioProductos repositorioProductos,
			ServicioUsuarios servicioUsuarios, ServicioCategorias servicioCategorias) {
		this.repositorioCategorias = repositorioCategorias;
		this.repositorioProductos = repositorioProductos;
		this.servicioUsuarios = servicioUsuarios;
		this.servicioCategorias = servicioCategorias;
	}

	@Override
	public Producto getProducto(String id) throws EntidadNoEncontrada {
		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("id: no debe ser nulo ni vacio");

		Optional<Producto> resultado = repositorioProductos.findById(id);
		if (!resultado.isPresent())
			throw new EntidadNoEncontrada("No existe producto con id: " + id);

		return resultado.get();
	}

	@Override
	public String crear(String titulo, String descripcion, double precio, EstadoProducto estado,
			boolean envioDisponible, String idCategoria, String idVendedor) throws EntidadNoEncontrada {

		Categoria categoria = servicioCategorias.getCategoria(idCategoria);
		Usuario vendedor = servicioUsuarios.getUsuario(idVendedor);

		Producto producto = new Producto(titulo, descripcion, precio, estado, envioDisponible, categoria, vendedor);

		return repositorioProductos.save(producto).getId();
	}

	@Override
	public void modificarDatosProducto(String idProducto, Double nuevoPrecio, String nuevaDescripcion)
			throws EntidadNoEncontrada {
		Producto producto = getProducto(idProducto);
		producto.setPrecio(nuevoPrecio);
		producto.setDescripcion(nuevaDescripcion);
		repositorioProductos.save(producto);
	}

	@Override
	public void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcionLugar)
			throws EntidadNoEncontrada {
		Producto producto = getProducto(idProducto);
		producto.asignarLugarRecogida(descripcionLugar, longitud, latitud);
		repositorioProductos.save(producto);
	}

	@Override
	public void incrementarVisualizaciones(String idProducto) throws EntidadNoEncontrada {
		Producto producto = getProducto(idProducto);
		producto.incrementarVisualizaciones();
		repositorioProductos.save(producto);
	}

	@Override
	public Page<ProductoResumenMensual> getResumenMensual(String idVendedor, int mes, int anio, Pageable pageable)
			throws EntidadNoEncontrada {
		if (mes < 1 || mes > 12)
			throw new IllegalArgumentException("mes: debe estar entre 1 y 12");
		if (anio < 1)
			throw new IllegalArgumentException("anio: debe ser positivo");

		servicioUsuarios.getUsuario(idVendedor);

		YearMonth ym = YearMonth.of(anio, mes);
		LocalDateTime inicio = ym.atDay(1).atStartOfDay();
		LocalDateTime fin = ym.atEndOfMonth().atTime(23, 59, 59, 999_999_999);
		return this.repositorioProductos.findResumenMensual(idVendedor, inicio, fin, pageable).map(producto -> {
			return ProductoResumenMensual.fromEntity(producto);
		});
	}

	@Override
	public List<Producto> buscarProductos(String descripcion, String idCategoria, EstadoProducto estado,
			Double precioMax) {

		String texto = descripcion != null ? descripcion.trim() : null;
		if (texto != null && texto.isEmpty())
			texto = null;

		// Expandir categoría a descendientes usando el campo ruta — una sola query
		Set<String> idsCategoriasValidas = null;
		if (idCategoria != null && !idCategoria.trim().isEmpty()) {
			Categoria raiz = repositorioCategorias.findById(idCategoria).orElse(null);
			if (raiz != null) {
				// Trae todos los descendientes por ruta, sin cargar el árbol en memoria
				List<Categoria> descendientes = repositorioCategorias.findDescendientesByRuta(raiz.getRuta());
				idsCategoriasValidas = descendientes.stream().map(Categoria::getId)
						.collect(Collectors.toCollection(java.util.HashSet::new));
				idsCategoriasValidas.add(idCategoria); // incluye la propia raíz
			} else {
				idsCategoriasValidas = java.util.Collections.singleton(idCategoria);
			}
		}

		// Estados "igual o mejor"
		List<EstadoProducto> estadosPermitidos = null;
		if (estado != null) {
			List<EstadoProducto> ranking = Arrays.asList(EstadoProducto.NUEVO, EstadoProducto.COMO_NUEVO,
					EstadoProducto.BUEN_ESTADO, EstadoProducto.ACEPTABLE, EstadoProducto.PARA_PIEZAS_O_REPARAR);
			int idx = ranking.indexOf(estado);
			estadosPermitidos = idx >= 0 ? ranking.subList(0, idx + 1) : java.util.Collections.singletonList(estado);
		}

		return repositorioProductos.buscarProductos(idsCategoriasValidas, texto, estadosPermitidos, precioMax);
	}

	@Override
	public Page<ProductoResumen> getListadoPaginado(Pageable pageable) {
		return this.repositorioProductos.findAll(pageable).map(producto -> {
			return ProductoResumen.fromEntity(producto);
		});
	}

}
