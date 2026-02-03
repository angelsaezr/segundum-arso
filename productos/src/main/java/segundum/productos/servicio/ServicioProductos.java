package segundum.productos.servicio;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import segundum.productos.modelo.Categoria;
import segundum.productos.modelo.EstadoProducto;
import segundum.productos.modelo.LugarDeRecogida;
import segundum.productos.modelo.Producto;
import segundum.productos.modelo.Usuario;
import segundum.productos.repositorio.EntidadNoEncontrada;
import segundum.productos.repositorio.FactoriaRepositorios;
import segundum.productos.repositorio.Repositorio;
import segundum.productos.repositorio.RepositorioException;
import segundum.productos.repositorio.RepositorioProductosAdHoc;

public class ServicioProductos implements IServicioProductos {

	private Repositorio<Usuario, String> repositorioUsuarios = FactoriaRepositorios.getRepositorio(Usuario.class);
	private Repositorio<Producto, String> repositorioProductos = FactoriaRepositorios.getRepositorio(Producto.class);
	private Repositorio<Categoria, String> repositorioCategorias = FactoriaRepositorios.getRepositorio(Categoria.class);

	@Override
	public String altaProducto(String titulo, String descripcion, double precio, EstadoProducto estado,
			boolean envioDisponible, String idCategoria, String idVendedor, String descripcionRecogida, double longitud,
			double latitud) throws RepositorioException, EntidadNoEncontrada {
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

		Categoria categoria = repositorioCategorias.getById(idCategoria);
		Usuario vendedor = repositorioUsuarios.getById(idVendedor);
		LugarDeRecogida lugarDeRecogida = new LugarDeRecogida(descripcionRecogida, longitud, latitud);
		Producto producto = new Producto(titulo, descripcion, precio, estado, envioDisponible, categoria, vendedor,
				lugarDeRecogida);
		return repositorioProductos.add(producto);
	}

	@Override
	public void modificarDatosProducto(String idProducto, Double nuevoPrecio, String nuevaDescripcion)
			throws RepositorioException, EntidadNoEncontrada {
		if (idProducto == null || idProducto.isEmpty())
			throw new IllegalArgumentException("idProducto: no debe ser nulo ni vacio");
		if (nuevoPrecio == null || nuevoPrecio < 0)
			throw new IllegalArgumentException("nuevoPrecio: debe ser mayor o igual que 0");
		if (nuevaDescripcion == null || nuevaDescripcion.isEmpty())
			throw new IllegalArgumentException("nuevaDescripcion: no debe ser nulo ni vacio");

		Producto producto = repositorioProductos.getById(idProducto);
		producto.setPrecio(nuevoPrecio);
		producto.setDescripcion(nuevaDescripcion);
		repositorioProductos.update(producto);
	}

	@Override
	public void asignarLugarRecogida(String idProducto, double longitud, double latitud, String descripcionLugar)
			throws RepositorioException, EntidadNoEncontrada {
		if (idProducto == null || idProducto.isEmpty())
			throw new IllegalArgumentException("idProducto: no debe ser nulo ni vacio");
		if (longitud < -180 || longitud > 180)
			throw new IllegalArgumentException("longitud: debe estar entre -180 y 180");
		if (latitud < -90 || latitud > 90)
			throw new IllegalArgumentException("latitud: debe estar entre -90 y 90");
		if (descripcionLugar == null || descripcionLugar.isEmpty())
			throw new IllegalArgumentException("descripcionLugar: no debe ser nulo ni vacio");

		Producto producto = repositorioProductos.getById(idProducto);
		LugarDeRecogida lugarDeRecogida = new LugarDeRecogida(descripcionLugar, longitud, latitud);
		producto.setLugarDeRecogida(lugarDeRecogida);
		repositorioProductos.update(producto);
	}

	@Override
	public void incrementarVisualizaciones(String idProducto) throws RepositorioException, EntidadNoEncontrada {
		Producto producto = repositorioProductos.getById(idProducto);
		producto.incrementarVisualizaciones();
		repositorioProductos.update(producto);
	}

	@Override
	public List<ProductoResumenMensual> getResumenMensual(String idVendedor, int mes, int anio)
			throws RepositorioException, EntidadNoEncontrada {
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
		repositorioUsuarios.getById(idVendedor);

		YearMonth ym = YearMonth.of(anio, mes);
		LocalDateTime inicio = ym.atDay(1).atStartOfDay();
		LocalDateTime fin = ym.atEndOfMonth().atTime(23, 59, 59, 999_999_999);

		// Se asume que el repositorio permite obtener todos los productos; si existe un
		// findByVendedor, úsalo.
		List<Producto> todos = repositorioProductos.getAll();

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
			Double precioMax) throws RepositorioException {
		// Normaliza texto
		String texto = descripcion != null ? descripcion.trim() : null;
		if (texto != null && texto.isEmpty())
			texto = null;

		// 1) Expandir categorías a descendientes usando subcategorias
		java.util.Set<String> idsCategoriasValidas = null;
		if (idCategoria != null && !idCategoria.trim().isEmpty()) {
			idsCategoriasValidas = new java.util.HashSet<>();

			// Índice id -> Categoria
			java.util.List<segundum.productos.modelo.Categoria> todas = repositorioCategorias.getAll();
			java.util.Map<String, segundum.productos.modelo.Categoria> porId = new java.util.HashMap<>();
			for (segundum.productos.modelo.Categoria c : todas) {
				porId.put(c.getId(), c);
			}

			segundum.productos.modelo.Categoria raiz = porId.get(idCategoria);
			if (raiz != null) {
				// DFS por subcategorias
				java.util.Deque<segundum.productos.modelo.Categoria> pila = new java.util.ArrayDeque<>();
				pila.push(raiz);
				while (!pila.isEmpty()) {
					segundum.productos.modelo.Categoria actual = pila.pop();
					if (idsCategoriasValidas.add(actual.getId())) {
						java.util.List<segundum.productos.modelo.Categoria> subs = actual.getSubcategorias();
						if (subs != null) {
							for (segundum.productos.modelo.Categoria h : subs) {
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
		java.util.List<EstadoProducto> estadosPermitidos = null;
		if (estado != null) {
			// ranking de negocio: NUEVO > COMO_NUEVO > BUEN_ESTADO > ACEPTABLE >
			// PARA_PIEZAS_O_REPARAR
			java.util.List<EstadoProducto> ranking = java.util.Arrays.asList(EstadoProducto.NUEVO,
					EstadoProducto.COMO_NUEVO, EstadoProducto.BUEN_ESTADO, EstadoProducto.ACEPTABLE,
					EstadoProducto.PARA_PIEZAS_O_REPARAR);
			int idx = ranking.indexOf(estado);
			if (idx >= 0) {
				estadosPermitidos = ranking.subList(0, idx + 1); // igual o mejor
			} else {
				estadosPermitidos = java.util.Collections.singletonList(estado);
			}
		}

		// 3) Delegar al repositorio ad hoc con IN
		RepositorioProductosAdHoc repoAdHoc = (RepositorioProductosAdHoc) this.repositorioProductos;

		return repoAdHoc.buscarProductos(idsCategoriasValidas, texto, estadosPermitidos, precioMax);
	}

}
