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
import segundum.usuarios.dto.UsuarioDTO;
import segundum.usuarios.dto.UsuarioInputDTO;
import segundum.usuarios.dto.UsuarioResumenDTO;
import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.servicio.FactoriaServicios;
import segundum.usuarios.servicio.IServicioUsuarios;

@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ControladorUsuarios {

	private IServicioUsuarios servicio = FactoriaServicios.getServicio(IServicioUsuarios.class);

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpServletRequest servletRequest;

	// GET http://localhost:8080/api/usuarios/1

	@GET
	@Path("{id}")
	@RolesAllowed("USUARIO")
	public Response getUsuario(@PathParam("id") String id) throws Exception {

		if (this.servletRequest.getAttribute("claims") != null) {
			Claims claims = (Claims) this.servletRequest.getAttribute("claims");
			System.out.println("Usuario autenticado: " + claims.getSubject());
			System.out.println("Roles: " + claims.get("roles"));
		}

		Usuario usuario = servicio.recuperar(id);
		UsuarioDTO dto = new UsuarioDTO(usuario);
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	// curl -i -X POST -H "Content-type: application/json" -d @1.json
	// http://localhost:8080/api/usuarios/

	@POST
	@PermitAll
	public Response createUsuario(UsuarioInputDTO dto) throws Exception {

		String id = servicio.altaUsuario(dto.getEmail(), dto.getNombre(), dto.getApellidos(), dto.getClave(),
				dto.getFechaNacimiento(), dto.getTelefono());
		URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(nuevaURL).build();
	}

	// curl -i -X PUT -H "Content-type: application/json" -d @1.json
	// http://localhost:8080/api/usuarios/1

	@PUT
	@Path("/{id}")
	@RolesAllowed("USUARIO")
	public Response update(@PathParam("id") String id, UsuarioInputDTO dto) throws Exception {

		Claims claims = (Claims) this.servletRequest.getAttribute("claims");
		if (!claims.getSubject().equals(id)) {
			return Response.status(Response.Status.FORBIDDEN).entity("Solo puedes modificar tus propios datos").build();
		}

		servicio.modificarUsuario(id, dto.getEmail(), dto.getNombre(), dto.getApellidos(), dto.getClave(),
				dto.getFechaNacimiento(), dto.getTelefono(), dto.isAdministrador());
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i http://localhost:8080/api/usuarios/

	@GET
	@RolesAllowed("USUARIO")
	public Response getUsuarios() throws Exception {

		String baseUri = uriInfo.getAbsolutePath().toString();
		// Eliminar barra final si existe
		if (baseUri.endsWith("/")) {
			baseUri = baseUri.substring(0, baseUri.length() - 1);
		}

		List<Usuario> usuarios = servicio.recuperarTodos();
		final String uri = baseUri;
		List<UsuarioResumenDTO> resumen = usuarios.stream().map(u -> new UsuarioResumenDTO(u, uri))
				.collect(Collectors.toList());

		return Response.status(Response.Status.OK).entity(resumen).build();
	}

}