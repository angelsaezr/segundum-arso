package segundum.compraventas.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String jwt = extraerToken(request);

		if (jwt != null) {
			try {
				Claims claims = JwtUtils.validateToken(jwt);
				String[] roles = claims.get("roles", String.class).split(",");

				// Establece el contexto de seguridad
				ArrayList<GrantedAuthority> authorities = new ArrayList<>();
				for (String rol : roles) {
					authorities.add(new SimpleGrantedAuthority(rol.trim()));
				}

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(),
						null, authorities);

				// Establecemos la autenticación en el contexto de seguridad
				// Se interpreta como que el usuario ha superado la autenticación
				SecurityContextHolder.getContext().setAuthentication(auth);

			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT inválido o expirado");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private String extraerToken(HttpServletRequest request) {
		// Cabecera Authorization: Bearer <token>
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Bearer ")) {
			return authorization.substring("Bearer ".length()).trim();
		}
		// Cookie http-only "jwt"
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