package segundum.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import segundum.productos.servicio.IServicioUsuarios;

public class PruebaRepositorio {

	public static void main(String[] args) {

		ConfigurableApplicationContext contexto = SpringApplication.run(ProductosApplication.class, args);

		IServicioUsuarios servicio = contexto.getBean(IServicioUsuarios.class);

		String nombre = "Angel";
		String apellidos = "Sanchez Lopez";
		String email = "angel@um.es";

		String id = servicio.altaUsuario(email, nombre, apellidos);

		System.out.println("id:" + id);

		contexto.close();

		System.out.println("fin.");
	}

}
