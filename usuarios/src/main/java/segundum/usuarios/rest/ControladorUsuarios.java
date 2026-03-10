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
import segundum.usuarios.rest.dto.UsuarioDTO;
import segundum.usuarios.rest.dto.UsuarioInputDTO;
import segundum.usuarios.rest.dto.UsuarioResumenDTO;
import segundum.usuarios.servicio.FactoriaServicios;
import segundum.usuarios.servicio.IServicioUsuarios;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControladorUsuarios {

	private final IServicioUsuarios servicio = FactoriaServicios.getServicio(IServicioUsuarios.class);

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest servletRequest;

	// GET http://localhost:8080/api/usuarios/1

	@GET
	@Path("{id}")
	@RolesAllowed("USUARIO")
	public Response getUsuario(@PathParam("id") String id) throws Exception {

		Claims claims = getClaims();
		if (claims != null) {
			System.out.println("Usuario autenticado: " + claims.getSubject());
			System.out.println("Roles: " + claims.get("roles"));
		}

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

	private Claims getClaims() {
		return (Claims) servletRequest.getAttribute("claims");
	}

}