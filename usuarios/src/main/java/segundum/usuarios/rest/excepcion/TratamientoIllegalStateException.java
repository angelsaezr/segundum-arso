package segundum.usuarios.rest.excepcion;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TratamientoIllegalStateException implements ExceptionMapper<IllegalStateException> {

	@Override
	public Response toResponse(IllegalStateException arg0) {
		Map<String, String> error = new HashMap<>();
		error.put("error", "Conflicto");
		error.put("mensaje", arg0.getMessage());
		return Response.status(Response.Status.CONFLICT)
				.type(MediaType.APPLICATION_JSON)
				.entity(error)
				.build();
	}
}
