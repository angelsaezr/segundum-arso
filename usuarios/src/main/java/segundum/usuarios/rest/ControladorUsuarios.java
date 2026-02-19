package segundum.usuarios.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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

}