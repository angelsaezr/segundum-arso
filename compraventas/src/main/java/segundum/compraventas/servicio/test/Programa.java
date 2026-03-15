package segundum.compraventas.servicio.test;

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
		System.out.println("=== PRUEBA CREAR COMPRAVENTA ===");
		Compraventa c = servicio.crearCompraventa("Bicicleta de montaña", 350.0);
		System.out.println("Compraventa creada:");
		System.out.println("ID:     " + c.getId());
		System.out.println("Título: " + c.getTitulo());
		System.out.println("Precio: " + c.getPrecio() + " €");
		System.out.println("=== FIN PRUEBA ===");
	}
}