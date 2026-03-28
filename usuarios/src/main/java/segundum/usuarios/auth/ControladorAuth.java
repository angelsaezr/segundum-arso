package segundum.usuarios.auth;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.servicio.FactoriaServicios;
import segundum.usuarios.servicio.ServicioUsuarios;

@Path("auth")
public class ControladorAuth {

	private final ServicioUsuarios servicio = FactoriaServicios.getServicio(ServicioUsuarios.class);

	// curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d
	// "username=juan&password=clave" http://localhost:8080/api/auth/login

	@POST
	@Path("/login")
	public Response login(@FormParam("username") String username, @FormParam("password") String password) {

		Map<String, Object> claims = verificarCredenciales(username, password);
		if (claims != null) {
			String token = JwtUtils.generateToken(claims);
			return Response.ok(token).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
		}

	}

	private Map<String, Object> verificarCredenciales(String username, String password) {

		try {
			Usuario usuario = servicio.login(username, password);
			if (usuario == null) {
				return null;
			}

			Map<String, Object> claims = new HashMap<>();
			claims.put("sub", usuario.getEmail());
			claims.put("roles", "USUARIO");

			return claims;

		} catch (Exception e) {
			return null;
		}
	}
}
