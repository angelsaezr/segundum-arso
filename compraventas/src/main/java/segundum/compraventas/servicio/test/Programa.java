package segundum.compraventas.servicio.test;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import segundum.compraventas.CompraventasApplication;
import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.servicio.ServicioCompraventasImpl;

public class Programa {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext contexto = SpringApplication.run(CompraventasApplication.class, args);

		ServicioCompraventasImpl servicio = contexto.getBean(ServicioCompraventasImpl.class);

		System.out.println("=== PRUEBA compraventa ===");
		Compraventa c = servicio.compraventa("0791a01c-6f11-4581-8294-1571ac425d00", "153");
		System.out.println("ID: " + c.getId());
		System.out.println("Producto: " + c.getIdProducto());
		System.out.println("Título: " + c.getTitulo());
		System.out.println("Precio: " + c.getPrecio() + " €");
		System.out.println("Recogida: " + c.getRecogida());
		System.out.println("Vendedor: " + c.getNombreVendedor());
		System.out.println("Comprador: " + c.getNombreComprador());
		System.out.println("Fecha: " + c.getFecha());

		System.out.println("\n=== PRUEBA recuperarComprasUsuario ===");
		List<Compraventa> compras = servicio.recuperarComprasUsuario("153");
		compras.forEach(cv -> System.out.println(cv.getTitulo() + ", " + cv.getFecha()));

		System.out.println("\n=== PRUEBA recuperarVentasUsuario ===");
		List<Compraventa> ventas = servicio.recuperarVentasUsuario("152");
		ventas.forEach(cv -> System.out.println(cv.getTitulo() + ", " + cv.getFecha()));

		System.out.println("\n=== PRUEBA recuperarCompraventasEntreUsuarios ===");
		List<Compraventa> entreUsuarios = servicio.recuperarCompraventasEntreUsuarios("153", "152");
		entreUsuarios.forEach(cv -> System.out.println(cv.getTitulo() + ", " + cv.getFecha()));

		contexto.close();

		System.out.println("Fin de pruebas.");
	}
}