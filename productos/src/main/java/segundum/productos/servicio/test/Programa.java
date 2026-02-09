package segundum.productos.servicio.test;

import segundum.productos.servicio.FactoriaServicios;
import segundum.productos.servicio.IServicioUsuarios;

public class Programa {

	public static void main(String[] args) throws Exception {

		IServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(IServicioUsuarios.class);

		// Configuración de datos de los usuarios
		String email = "angel@um.es";
		String nombre = "Angel";
		String apellidos = "Lopez Ramirez";

		String email2 = "javi@um.es";
		String nombre2 = "Javi";
		String apellidos2 = "Rosique Saez";

		// Alta de los usuarios
		System.out.println("Creando usuarios...");
		String id = servicioUsuarios.altaUsuario(email, nombre, apellidos);
		System.out.println("Usuario creado con id:  " + id);
		System.out.println("Datos: " + email + ", " + nombre + ", " + apellidos);

		String id2 = servicioUsuarios.altaUsuario(email2, nombre2, apellidos2);
		System.out.println("Usuario creado con id:  " + id2);
		System.out.println("Datos: " + email2 + ", " + nombre2 + ", " + apellidos2);

	}

}
