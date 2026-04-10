package segundum.usuarios.servicio.test;

import java.util.Date;

import segundum.usuarios.rest.dto.UsuarioInputDTO;
import segundum.usuarios.servicio.FactoriaServicios;
import segundum.usuarios.servicio.ServicioUsuarios;

public class Programa {

	public static void main(String[] args) throws Exception {

		System.out.println("----------- USUARIOS -----------\n");

		ServicioUsuarios servicioUsuarios = FactoriaServicios.getServicio(ServicioUsuarios.class);

		/*// Configuración de datos del usuario
		String email = "juan@um.es";
		String nombre = "Juan";
		String apellidos = "Perez Gomez";
		String clave = "1234";
		Date fechaNacimiento = new Date();

		// Alta del usuario
		System.out.println("Creando usuario...");
		UsuarioInputDTO usuarioDTO = new UsuarioInputDTO();
		usuarioDTO.setEmail(email);
		usuarioDTO.setNombre(nombre);
		usuarioDTO.setApellidos(apellidos);
		usuarioDTO.setClave(clave);
		usuarioDTO.setFechaNacimiento(fechaNacimiento);
		usuarioDTO.setAdministrador(false);
		String id = servicioUsuarios.altaUsuario(usuarioDTO);
		System.out.println("Usuario creado con id:  " + id);
		System.out
				.println("Datos: " + email + ", " + nombre + ", " + apellidos + ", " + clave + ", " + fechaNacimiento);*/

		// Modificacion del usuario
		System.out.println("Modificando el correo del usuario...");
		String nombreModificado = "Francisco";
		servicioUsuarios.modificarUsuario("1", null, nombreModificado, null, null, null, null, false);
		System.out.println("Nombre modificado: " + nombreModificado);

		System.out.println("\n------------ FIN -------------------");

	}

}
