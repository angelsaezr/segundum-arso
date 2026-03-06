package segundum.productos.servicio;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import segundum.productos.modelo.Categoria;
import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.LugarDeRecogida;
import segundum.productos.modelo.Producto;
import segundum.productos.modelo.Usuario;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.RepositorioCategorias;
import segundum.productos.repositorio.RepositorioProductos;
import segundum.productos.repositorio.RepositorioUsuarios;

public class ServicioProductos implements IServicioProductos {

	private RepositorioCategorias repositorioCategorias;
	private RepositorioUsuarios repositorioUsuarios;
	private RepositorioProductos repositorioProductos;

	@Autowired
	public ServicioProductos(RepositorioCategorias repositorioCategorias, RepositorioUsuarios repositorioUsuarios,
			RepositorioProductos repositorioProductos) {
		this.repositorioCategorias = repositorioCategorias;
		this.repositorioUsuarios = repositorioUsuarios;
		this.repositorioProductos = repositorioProductos;
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
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			boolean envioDisponible, String idCategoria, String idVendedor, String descripcionRecogida, double longitud,
			double latitud) throws EntidadNoEncontrada {
		if (titulo == null || titulo.isEmpty())
			throw new IllegalArgumentException("titulo: no debe ser nulo ni vacio");
		if (descripcion == null || descripcion.isEmpty())
			throw new IllegalArgumentException("descripcion: no debe ser nulo ni vacio");
		if (precio < 0)
			throw new IllegalArgumentException("precio: debe ser mayor o igual que 0");
		if (idCategoria == null || idCategoria.isEmpty())
			throw new IllegalArgumentException("idCategoria: no debe ser nulo ni vacio");
		if (idVendedor == null || idVendedor.isEmpty())
			throw new IllegalArgumentException("idVendedor: no debe ser nulo ni vacio");
		if (descripcionRecogida == null || descripcionRecogida.isEmpty())
			throw new IllegalArgumentException("descripcionRecogida: no debe ser nulo ni vacio");
		if (longitud < -180 || longitud > 180)
			throw new IllegalArgumentException("longitud: debe estar entre -180 y 180");
		if (latitud < -90 || latitud > 90)
			throw new IllegalArgumentException("latitud: debe estar entre -90 y 90");

		Optional<Categoria> resultadoCategoria = repositorioCategorias.findById(idCategoria);
		if (resultadoCategoria.isPresent() == false)
			throw new EntidadNoEncontrada("No existe categoría con id: " + idCategoria);
		Categoria categoria = resultadoCategoria.get();

		Optional<Usuario> resultadoVendedor = repositorioUsuarios.findById(idVendedor);
		if (resultadoVendedor.isPresent() == false)
			throw new EntidadNoEncontrada("No existe usuario vendedor con id: " + idVendedor);
		Usuario vendedor = resultadoVendedor.get();

		LugarDeRecogida lugarDeRecogida = new LugarDeRecogida(descripcionRecogida, longitud, latitud);
		Producto producto = new Producto(titulo, descripcion, precio, estado, envioDisponible, categoria, vendedor,
				lugarDeRecogida);
		return repositorioProductos.save(producto).getId();
	}

	@Override
	public void modificarDatosProducto(String idProducto, Double nuevoPrecio, String nuevaDescripcion)
			throws EntidadNoEncontrada {
		if (idProducto == null || idProducto.isEmpty())
			throw new IllegalArgumentException("idProducto: no debe ser nulo ni vacio");
		if (nuevoPrecio == null || nuevoPrecio < 0)
			throw new IllegalArgumentException("nuevoPrecio: debe ser mayor o igual que 0");
		if (nuevaDescripcion == null || nuevaDescripcion.isEmpty())
			throw new IllegalArgumentException("nuevaDescripcion: no debe ser nulo ni vacio");

		Optional<Producto> resultadoProducto = repositorioProductos.findById(idProducto);
		if (resultadoProducto.isPresent() == false)
			throw new EntidadNoEncontrada("No existe producto con id: " + idProducto);
		Producto producto = resultadoProducto.get();
		producto.setPrecio(nuevoPrecio);
		producto.setDescripcion(nuevaDescripcion);
		repositorioProductos.save(producto);
	}

	@Override
	public void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcionLugar)
			throws EntidadNoEncontrada {
		if (idProducto == null || idProducto.isEmpty())
			throw new IllegalArgumentException("idProducto: no debe ser nulo ni vacio");
		if (longitud < -180 || longitud > 180)
			throw new IllegalArgumentException("longitud: debe estar entre -180 y 180");
		if (latitud < -90 || latitud > 90)
			throw new IllegalArgumentException("latitud: debe estar entre -90 y 90");
		if (descripcionLugar == null || descripcionLugar.isEmpty())
			throw new IllegalArgumentException("descripcionLugar: no debe ser nulo ni vacio");

		Optional<Producto> resultadoProducto = repositorioProductos.findById(idProducto);
		if (resultadoProducto.isPresent() == false)
			throw new EntidadNoEncontrada("No existe producto con id: " + idProducto);
		Producto producto = resultadoProducto.get();

		LugarDeRecogida lugarDeRecogida = new LugarDeRecogida(descripcionLugar, longitud, latitud);
		producto.setLugarDeRecogida(lugarDeRecogida);
		repositorioProductos.save(producto);
	}

	@Override
	public void incrementarVisualizaciones(String idProducto) throws EntidadNoEncontrada {
		Optional<Producto> resultadoProducto = repositorioProductos.findById(idProducto);
		if (resultadoProducto.isPresent() == false)
			throw new EntidadNoEncontrada("No existe producto con id: " + idProducto);
		Producto producto = resultadoProducto.get();
		producto.incrementarVisualizaciones();
		repositorioProductos.save(producto);
	}

	@Override
	public List<ProductoResumenMensual> getResumenMensual(String idVendedor, int mes, int anio)
			throws EntidadNoEncontrada {
		if (idVendedor == null || idVendedor.isEmpty()) {
			throw new IllegalArgumentException("idVendedor: no debe ser nulo ni vacio");
		}
		if (mes < 1 || mes > 12) {
			throw new IllegalArgumentException("mes: debe estar entre 1 y 12");
		}
		if (anio < 1) {
			throw new IllegalArgumentException("anio: debe ser positivo");
		}

		// Verifica existencia del vendedor
		Optional<Usuario> resultadoVendedor = repositorioUsuarios.findById(idVendedor);
		if (resultadoVendedor.isPresent() == false)
			throw new EntidadNoEncontrada("No existe usuario vendedor con id: " + idVendedor);

		YearMonth ym = YearMonth.of(anio, mes);
		LocalDateTime inicio = ym.atDay(1).atStartOfDay();
		LocalDateTime fin = ym.atEndOfMonth().atTime(23, 59, 59, 999_999_999);

		// Se asume que el repositorio permite obtener todos los productos; si existe un
		// findByVendedor, úsalo.
		// List<Producto> todos = repositorioProductos.getAll().filter(p ->
		// p.getVendedor() != null &&
		// idVendedor.equals(p.getVendedor().getId())).collect(Collectors.toList());
		List<Producto> todos = new ArrayList<>();
		for (Producto p : repositorioProductos.findAll()) {
			todos.add(p);
		}

		return todos.stream().filter(p -> p.getVendedor() != null && idVendedor.equals(p.getVendedor().getId()))
				.filter(p -> {
					LocalDateTime f = p.getFechaPublicacion(); // es LocalDateTime [web:36][web:41]
					return f != null && !f.isBefore(inicio) && !f.isAfter(fin);
				}).sorted(Comparator.comparingInt(Producto::getVisualizaciones).reversed()) // desc por visualizaciones
																							// [web:20][web:26]
				.map(p -> new ProductoResumenMensual(p.getId(), p.getTitulo(), p.getPrecio(),
						p.getFechaPublicacion().toLocalDate(), // convertir a LocalDate para el DTO [web:36][web:41]
						p.getCategoria() != null ? p.getCategoria().getNombre() : null, p.getVisualizaciones()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Producto> buscarProductos(String descripcion, String idCategoria, EstadoProducto estado,
			Double precioMax) {
		// Normaliza texto
		String texto = descripcion != null ? descripcion.trim() : null;
		if (texto != null && texto.isEmpty())
			texto = null;

		// 1) Expandir categorías a descendientes usando subcategorias
		Set<String> idsCategoriasValidas = null;
		if (idCategoria != null && !idCategoria.trim().isEmpty()) {
			idsCategoriasValidas = new java.util.HashSet<>();

			// Índice id -> Categoria
			List<Categoria> todas = new ArrayList<>();
			for (Categoria c : repositorioCategorias.findAll()) {
				todas.add(c);
			}

			Map<String, Categoria> porId = new java.util.HashMap<>();
			for (Categoria c : todas) {
				porId.put(c.getId(), c);
			}

			Categoria raiz = porId.get(idCategoria);
			if (raiz != null) {
				// DFS por subcategorias
				Deque<Categoria> pila = new ArrayDeque<>();
				pila.push(raiz);
				while (!pila.isEmpty()) {
					Categoria actual = pila.pop();
					if (idsCategoriasValidas.add(actual.getId())) {
						List<Categoria> subs = actual.getSubcategorias();
						if (subs != null) {
							for (Categoria h : subs) {
								pila.push(h);
							}
						}
					}
				}
			} else {
				// si no existe en memoria, al menos filtra por la propia id
				idsCategoriasValidas.add(idCategoria);
			}
		}

		// 2) Estados “igual o mejor”
		List<EstadoProducto> estadosPermitidos = null;
		if (estado != null) {
			// ranking de negocio: NUEVO > COMO_NUEVO > BUEN_ESTADO > ACEPTABLE >
			// PARA_PIEZAS_O_REPARAR
			List<EstadoProducto> ranking = Arrays.asList(EstadoProducto.NUEVO, EstadoProducto.COMO_NUEVO,
					EstadoProducto.BUEN_ESTADO, EstadoProducto.ACEPTABLE, EstadoProducto.PARA_PIEZAS_O_REPARAR);
			int idx = ranking.indexOf(estado);
			if (idx >= 0) {
				estadosPermitidos = ranking.subList(0, idx + 1); // igual o mejor
			} else {
				estadosPermitidos = java.util.Collections.singletonList(estado);
			}
		}

		return repositorioProductos.buscarProductos(idsCategoriasValidas, texto, estadosPermitidos, precioMax);
	}

}
