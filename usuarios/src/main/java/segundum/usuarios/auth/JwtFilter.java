package segundum.usuarios.auth;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class JwtFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		String jwt = extraerToken(ctx);
		if (jwt != null) {
			try {
				Claims claims = JwtUtils.validateToken(jwt);
				ctx.setProperty("claims", claims);
			} catch (Exception e) {
				// Token inválido, no se establecen claims
			}
		}
	}

	private String extraerToken(ContainerRequestContext ctx) {
		String auth = ctx.getHeaderString("Authorization");
		if (auth != null && auth.startsWith("Bearer ")) {
			return auth.substring(7).trim();
		}
		// Cookie
		String cookieHeader = ctx.getHeaderString("Cookie");
		if (cookieHeader != null) {
			for (String part : cookieHeader.split(";")) {
				part = part.trim();
				if (part.startsWith("jwt=")) {
					return part.substring(4);
				}
			}
		}
		return null;
	}
}