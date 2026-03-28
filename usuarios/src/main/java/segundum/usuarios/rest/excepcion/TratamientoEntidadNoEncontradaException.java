package segundum.usuarios.rest.excepcion;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.usuarios.repositorio.EntidadNoEncontrada;

@Provider
public class TratamientoEntidadNoEncontradaException implements ExceptionMapper<EntidadNoEncontrada> {

	@Override
	public Response toResponse(EntidadNoEncontrada arg0) {
		Map<String, String> error = new HashMap<>();
		error.put("error", "Entidad no encontrada");
		error.put("mensaje", arg0.getMessage());
		return Response.status(Response.Status.NOT_FOUND)
				.type(MediaType.APPLICATION_JSON)
				.entity(error)
				.build();
	}
}
