package segundum.productos;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import segundum.productos.repositorio.RepositorioCategorias;
import segundum.productos.servicio.ServicioCategorias;

@SpringBootApplication
public class ProductosApplication implements ApplicationRunner {

	private final ServicioCategorias servicioCategorias;
	private final RepositorioCategorias repositorioCategorias;

	public ProductosApplication(ServicioCategorias servicioCategorias, RepositorioCategorias repositorioCategorias) {
		this.servicioCategorias = servicioCategorias;
		this.repositorioCategorias = repositorioCategorias;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductosApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (repositorioCategorias.count() == 0) {
			System.out.println("Cargando categorías iniciales...");
			servicioCategorias.cargarJerarquiaCategorias("categoriasXML/Software.xml");
			System.out.println("Categorías cargadas correctamente.");
		}
	}
}