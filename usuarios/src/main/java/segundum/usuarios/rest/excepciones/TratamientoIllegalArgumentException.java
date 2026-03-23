package segundum.usuarios.rest.excepciones;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TratamientoIllegalArgumentException implements ExceptionMapper<IllegalArgumentException> {

	@Override
	public Response toResponse(IllegalArgumentException arg0) {
		Map<String, String> error = new HashMap<>();
		error.put("error", "Argumento no válido");
		error.put("mensaje", arg0.getMessage());
		return Response.status(Response.Status.BAD_REQUEST)
				.type(MediaType.APPLICATION_JSON)
				.entity(error)
				.build();
	}
}