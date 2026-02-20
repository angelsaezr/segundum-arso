package segundum.usuarios.rest;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import segundum.usuarios.modelo.Usuario;
import segundum.usuarios.servicio.FactoriaServicios;
import segundum.usuarios.servicio.IServicioUsuarios;

@Path("usuarios")
public class ControladorUsuarios {

	private IServicioUsuarios servicio = FactoriaServicios.getServicio(IServicioUsuarios.class);

	@Context
	private UriInfo uriInfo;

	// http://localhost:8080/api/usuarios/1

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getUsuario(@PathParam("id") String id) throws Exception {

		return Response.status(Response.Status.OK).entity(servicio.recuperar(id)).build();
	}
	
	// curl -i -X POST -H "Content-type: application/xml" -d @1.xml http://localhost:8080/api/usuarios/

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response createUsuario(Usuario usuario) throws Exception {

		String id = servicio.altaUsuario(usuario.getEmail(), usuario.getNombre(), usuario.getApellidos(),
				usuario.getClave(), usuario.getFechaNacimiento(), usuario.getTelefono());
		URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(nuevaURL).build();
	}

}