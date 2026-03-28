package segundum.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import segundum.productos.modelo.EstadoProducto;
import segundum.productos.servicio.ServicioCategorias;
import segundum.productos.servicio.ServicioProductos;
import segundum.productos.servicio.ServicioUsuarios;

public class PruebaRepositorio {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext contexto = SpringApplication.run(ProductosApplication.class, args);

		ServicioUsuarios servicioUsuarios = contexto.getBean(ServicioUsuarios.class);
		ServicioProductos servicioProductos = contexto.getBean(ServicioProductos.class);
		ServicioCategorias servicioCategorias = contexto.getBean(ServicioCategorias.class);

		System.out.println("----------- USUARIOS -----------\n");

		// Configuración de datos del usuario
		String email = "juan@um.es";
		String nombre = "Juan";
		String apellidos = "Perez Gomez";

		// Alta del usuario
		System.out.println("Creando usuario...");
		String id = servicioUsuarios.altaUsuario(email, nombre, apellidos);
		System.out.println("Usuario creado con id:  " + id);
		System.out.println("Datos: " + email + ", " + nombre + ", " + apellidos);

		System.out.println("\n----------- CATEGORIAS -----------\n");

		System.out.println("Cargando jerarquia de categorias desde XML Software...");
		servicioCategorias.cargarJerarquiaCategorias("categoriasXML/Software.xml");
		System.out.println("Categorias cargadas.");

		System.out.println("\n----------- PRODUCTOS -----------\n");

		String idCategoria = "4951";

		// Alta de productos
		System.out.println("Dando de alta varios productos para pruebas...");

		String idProd1 = servicioProductos.crear("Teclado mecánico", "Teclado switch azul con iluminación RGB", 45.90,
				EstadoProducto.COMO_NUEVO, true, idCategoria, id);
		System.out.println("Producto creado con id: " + idProd1);

		String idProd2 = servicioProductos.crear("Monitor 27\" 144Hz", "Panel IPS 144Hz 1ms, HDR", 189.99,
				EstadoProducto.BUEN_ESTADO, false, idCategoria, id);
		System.out.println("Producto creado con id: " + idProd2);

		String idProd3 = servicioProductos.crear("Portátil i7 16GB RAM",
				"Perfecto estado, batería cambiada recientemente", 650.00, EstadoProducto.NUEVO, true, idCategoria, id);
		System.out.println("Producto creado con id: " + idProd3);

		System.out.println("\n------------ FIN -------------------");

		contexto.close();

		System.out.println("fin.");
	}

}
