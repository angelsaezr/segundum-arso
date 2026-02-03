package segundum.servicio.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import segundum.modelo.EstadoProducto;
import segundum.modelo.Producto;
import segundum.servicio.FactoriaServicios;
import segundum.servicio.IServicioCategorias;
import segundum.servicio.IServicioProductos;
import segundum.servicio.IServicioUsuarios;
import segundum.servicio.ProductoResumenMensual;

public class Programa {

	public static void main(String[] args) throws Exception {

		System.out.println("----------- USUARIOS -----------\n");

		IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);

		// Configuración de datos del usuario
		String email = "juan@um.es";
		String nombre = "Juan";
		String apellidos = "Perez Gomez";
		String clave = "1234";
		LocalDate fechaNacimiento = LocalDate.of(2000, 1, 1);

		// Alta del usuario
		System.out.println("Creando usuario...");
		String id = servicioUsuarios.altaUsuario(email, nombre, apellidos, clave, fechaNacimiento, null);
		System.out.println("Usuario creado con id:  " + id);
		System.out
				.println("Datos: " + email + ", " + nombre + ", " + apellidos + ", " + clave + ", " + fechaNacimiento);

		// Modificacion del usuario
		System.out.println("Modificando el correo del usuario...");
		String emailModificado = "juan2@um.es";
		servicioUsuarios.modificarUsuario(id, emailModificado, null, null, null, null, null, false);
		System.out.println("Correo modificado: " + emailModificado);

		System.out.println("\n----------- CATEGORIAS -----------\n");

		IServicioCategorias servicioCategorias = FactoriaServicios.getServicio(IServicioCategorias.class);

		System.out.println("Cargando jerarquia de categorias desde XML Software...");
		servicioCategorias.cargarJerarquiaCategorias("categoriasXML/Software.xml");
		System.out.println("Categorias cargadas.");

		System.out.println("Modificando la descripcion de la categoria con ID 4951...");
		servicioCategorias.modificarDescripcionCategoria("4951", "Nueva descripcion de la categoria 4951");
		System.out.println("Descripcion modificada.");

		System.out.println("Listando todas las categorias raíz:");
		servicioCategorias.getCategoriasRaiz().forEach(c -> {
			System.out.println(" - " + c.getNombre() + " (ID: " + c.getId() + ")");
		});

		System.out.println("Listando los descendientes de la categoria con ID 1279:");
		servicioCategorias.getDescendientesCategoria("1279").forEach(c -> {
			System.out.println(" - " + c.getNombre() + " (ID: " + c.getId() + ")");
		});
		System.out.println(" - Ok no tiene ninguna");

		System.out.println("\n----------- PRODUCTOS -----------\n");

		IServicioProductos servicioProductos = FactoriaServicios.getServicio(IServicioProductos.class);

		String idCategoria = "4951";

		// Alta de productos
		System.out.println("Dando de alta varios productos para pruebas...");

		String idProd1 = servicioProductos.altaProducto("Teclado mecánico", "Teclado switch azul con iluminación RGB",
				45.90, EstadoProducto.COMO_NUEVO, true, idCategoria, id, "Puerta principal Biblioteca UM", -1.1300,
				37.9870);
		System.out.println("Producto creado con id: " + idProd1);

		String idProd2 = servicioProductos.altaProducto("Monitor 27\" 144Hz", "Panel IPS 144Hz 1ms, HDR", 189.99,
				EstadoProducto.BUEN_ESTADO, false, idCategoria, id, "Entrada principal Edificio Rectorado", -1.1290,
				37.9880);
		System.out.println("Producto creado con id: " + idProd2);

		String idProd3 = servicioProductos.altaProducto("Portátil i7 16GB RAM",
				"Perfecto estado, batería cambiada recientemente", 650.00, EstadoProducto.NUEVO, true, idCategoria, id,
				"Cafetería Campus", -1.1315, 37.9865);
		System.out.println("Producto creado con id: " + idProd3);

		// Modificar datos de un producto
		System.out.println("\nModificando datos del producto " + idProd2 + "...");
		servicioProductos.modificarDatosProducto(idProd2, 179.99, "Panel IPS 144Hz 1ms, HDR10, peana regulable");
		System.out.println("Precio y descripcion modificados.");

		// Asignar lugar de recogida
		System.out.println("\nAsignando nuevo lugar de recogida al producto " + idProd1 + "...");
		servicioProductos.asignarLugarRecogida(idProd1, -1.1322, 37.9855, "Parada Tranvia Universidad");
		System.out.println("Lugar de recogida actualizado.");

		// Incrementar visualizaciones
		System.out.println("\nIncrementando visualizaciones...");
		servicioProductos.incrementarVisualizaciones(idProd1);
		servicioProductos.incrementarVisualizaciones(idProd1);
		servicioProductos.incrementarVisualizaciones(idProd2);
		System.out.println("Visualizaciones incrementadas: prod1 (+2), prod2 (+1).");

		// Historial del mes (resumen mensual)
		System.out.println("\nObteniendo resumen mensual del vendedor...");
		YearMonth ahora = YearMonth.from(LocalDateTime.now());
		List<ProductoResumenMensual> resumen = servicioProductos.getResumenMensual(id, ahora.getMonthValue(),
				ahora.getYear());
		System.out.println("Resumen mensual (" + ahora.getMonthValue() + "/" + ahora.getYear() + "):");
		for (ProductoResumenMensual r : resumen) {
			System.out.println(" - ID: " + r.getIdProducto() + " | Titulo: " + r.getTitulo() + " | Precio: "
					+ r.getPrecio() + " | Publicado: " + r.getFechaPublicacion() + " | Categoria: "
					+ r.getNombreCategoria() + " | Visualizaciones: " + r.getVisualizaciones());
		}

		// Búsqueda de productos a la venta
		System.out.println("\nBuscando productos por categoria, texto, estado y precio maximo...");
		List<Producto> encontrados = servicioProductos.buscarProductos("IPS", idCategoria, // pasa el id como String
				EstadoProducto.BUEN_ESTADO, 200.00);
		System.out.println("Resultados de búsqueda:");
		for (Producto p : encontrados) {
			System.out.println(" - " + p.getTitulo() + " (ID: " + p.getId() + ", Estado: " + p.getEstado()
					+ ", Precio: " + p.getPrecio() + ", Categoria: " + p.getCategoria().getNombre() + ")");
		}

		System.out.println("\n------------ FIN -------------------");

	}

}
