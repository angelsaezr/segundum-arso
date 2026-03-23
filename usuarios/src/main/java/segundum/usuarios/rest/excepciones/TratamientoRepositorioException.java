package segundum.usuarios.rest.excepciones;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import segundum.usuarios.repositorio.RepositorioException;

@Provider
public class TratamientoRepositorioException implements ExceptionMapper<RepositorioException> {

	@Override
	public Response toResponse(RepositorioException arg0) {
		Map<String, String> error = new HashMap<>();
		error.put("error", "Error interno del servidor");
		error.put("mensaje", arg0.getMessage());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON)
				.entity(error)
				.build();
	}
}
