package segundum.pasarela.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulJwtFilter extends ZuulFilter {

	private static final String[] RUTAS_PUBLICAS = { "/auth/", "/login/", "/oauth2/" };

	@Override
	public String filterType() {
		return "pre"; // se ejecuta antes de enrutar la petición
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String uri = ctx.getRequest().getRequestURI();
		for (String ruta : RUTAS_PUBLICAS) {
			if (uri.startsWith(ruta)) {
				return false; // rutas públicas no aplicar el filtro
			}
		}
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		String jwt = extraerToken(request);

		if (jwt != null) {
			// Reenvía el token al microservicio destino
			ctx.addZuulRequestHeader("Authorization", "Bearer " + jwt);
		}

		return null;
	}

	// Extrae el JWT de la cabecera Authorization o de la cookie "jwt"
	private String extraerToken(HttpServletRequest request) {
		// Buscar en cabecera
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Bearer ")) {
			return authorization.substring("Bearer ".length()).trim();
		}

		// Buscar en cookie http-only
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("jwt".equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}

		return null;
	}
}
