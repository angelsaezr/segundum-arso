package segundum.usuarios.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.jsonwebtoken.Claims;
import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.rest.dto.UsuarioDTO;
import segundum.usuarios.rest.dto.UsuarioInputDTO;
import segundum.usuarios.rest.dto.UsuarioLoginInputDTO;
import segundum.usuarios.rest.dto.UsuarioLoginResponseDTO;
import segundum.usuarios.rest.dto.UsuarioResumenDTO;
import segundum.usuarios.servicio.FactoriaServicios;
import segundum.usuarios.servicio.ServicioUsuarios;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControladorUsuarios {

	private final ServicioUsuarios servicio = FactoriaServicios.getServicio(ServicioUsuarios.class);

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest servletRequest;

	// GET http://localhost:8080/api/usuarios/1

	@GET
	@Path("{id}")
	@RolesAllowed("USUARIO")
	public Response getUsuario(@PathParam("id") String id) throws Exception {
		return Response.ok(UsuarioDTO.fromEntity(servicio.recuperar(id))).build();
	}

	// curl -i -X POST -H "Content-type: application/json" -d @1.json
	// http://localhost:8080/api/usuarios/

	@POST
	@PermitAll
	public Response createUsuario(UsuarioInputDTO dto) throws Exception {
		String id = servicio.altaUsuario(dto);
		URI nuevaURL = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(nuevaURL).build();
	}

	// curl -i -X PUT -H "Content-type: application/json" -d @1.json
	// http://localhost:8080/api/usuarios/1

	@PUT
	@Path("{id}")
	@RolesAllowed("USUARIO")
	public Response update(@PathParam("id") String id, UsuarioInputDTO dto) throws Exception {
		Claims claims = getClaims();
		if (!claims.getSubject().equals(id)) {
			return Response.status(Response.Status.FORBIDDEN).entity("Solo puedes modificar tus propios datos").build();
		}

		servicio.modificarUsuario(id, dto.getEmail(), dto.getNombre(), dto.getApellidos(), dto.getClave(),
				dto.getFechaNacimiento(), dto.getTelefono(), dto.isAdministrador());
		return Response.noContent().build();
	}

	// curl -i http://localhost:8080/api/usuarios/

	@GET
	@RolesAllowed("USUARIO")
	public Response getUsuarios() throws Exception {
		String baseUri = uriInfo.getAbsolutePath().toString().replaceAll("/$", "");

		List<UsuarioResumenDTO> resumen = servicio.recuperarTodos().stream()
				.map(u -> UsuarioResumenDTO.fromEntity(u, baseUri)).collect(Collectors.toList());

		return Response.ok(resumen).build();
	}

	// GET http://localhost:8080/api/usuarios/1/nombre

	@GET
	@Path("{id}/nombre")
	@PermitAll
	public Response getNombreUsuario(@PathParam("id") String id) throws Exception {
		return Response.ok(servicio.recuperar(id).getNombre()).build();
	}

	// POST http://localhost:8080/api/usuarios/login
	// Operación pública para que la pasarela verifique credenciales.
	// Devuelve los datos del usuario sin generar token (eso lo hace la pasarela).

	@POST
	@Path("/login")
	@PermitAll
	public Response login(UsuarioLoginInputDTO dto) {
		try {
			Usuario usuario = servicio.login(dto.getEmail(), dto.getPassword());
			if (usuario == null) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
			}
			return Response.ok(UsuarioLoginResponseDTO.fromEntity(usuario)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("{\"error\": \"" + e.getMessage() + "\"}").build();
		}
	}

	private Claims getClaims() {
		return (Claims) servletRequest.getAttribute("claims");
	}

}