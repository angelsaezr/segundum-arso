package segundum.compraventas.servicio.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import segundum.compraventas.modelo.Compraventa;
import segundum.compraventas.servicio.ServicioCompraventas;

@Component
public class Programa implements CommandLineRunner {

	@Autowired
	private ServicioCompraventas servicio;

	@Override
	public void run(String... args) throws Exception {

		// 1. Crear una compraventa
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

		// 2. Recuperar compras del usuario comprador
		System.out.println("\n=== PRUEBA recuperarComprasUsuario ===");
		List<Compraventa> compras = servicio.recuperarComprasUsuario("153");
		compras.forEach(cv -> System.out.println(cv.getTitulo() + ", " + cv.getFecha()));

		// 3. Recuperar ventas del usuario vendedor
		System.out.println("\n=== PRUEBA recuperarVentasUsuario ===");
		List<Compraventa> ventas = servicio.recuperarVentasUsuario("152");
		ventas.forEach(cv -> System.out.println(cv.getTitulo() + ", " + cv.getFecha()));

		// 4. Recuperar compraventas entre comprador y vendedor
		System.out.println("\n=== PRUEBA recuperarCompraventasEntreUsuarios ===");
		List<Compraventa> entreUsuarios = servicio.recuperarCompraventasEntreUsuarios("153", "152");
		entreUsuarios.forEach(cv -> System.out.println(cv.getTitulo() + ", " + cv.getFecha()));
	}
}