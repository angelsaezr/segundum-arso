package segundum.pasarela.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import segundum.pasarela.puerto.ClienteUsuarios;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

	private static final int JWT_TIEMPO_VALIDEZ = 3600;

	@Autowired
	private ClienteUsuarios clienteUsuarios;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		DefaultOAuth2User usuario = (DefaultOAuth2User) authentication.getPrincipal();

		// El login de GitHub (nombre de usuario único)
		// String githubLogin = (String) usuario.getAttributes().get("login");
		String githubEmail = (String) usuario.getAttributes().get("email"); // TODO hay que ver si GitHub da el email
		// String githubNombre = (String) usuario.getAttributes().get("name");

		Map<String, Object> claims = null;
		try {
			claims = fetchUserInfo(githubEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (claims != null) {
			// genera el token JWT y lo envía en la respuesta ...
			String token = JwtUtils.generateToken(claims);

			// Enviar token en cookie http-only
			Cookie cookie = new Cookie("jwt", token);
			cookie.setMaxAge(JWT_TIEMPO_VALIDEZ);
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie);

			// escribe el token en response
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{\"token\":\"" + token + "\"," + "\"id\":\"" + claims.get("sub") + "\","
					+ "\"nombre\":\"" + claims.get("nombre") + "\"," + "\"roles\":\"" + claims.get("roles") + "\"}");
		}

		// Usuario de GitHub no vinculado a una cuenta del sistema
		// generamos un token provisional con rol USUARIO
		// TODO revisar si esta bien hacer esto
		// claims = new HashMap<>();
		// claims.put("sub", "github:" + githubLogin);
		// claims.put("nombre", githubNombre != null ? githubNombre : githubLogin);
		// claims.put("roles", "USUARIO");
	}

	private Map<String, Object> fetchUserInfo(String githubEmail) throws Exception {

		UsuarioLoginResponseDTO usuario = clienteUsuarios.buscarPorEmail(githubEmail);
		if (usuario != null) {
			HashMap<String, Object> claims = new HashMap<String, Object>();
			claims.put("sub", usuario.getId());
			claims.put("nombre", usuario.getNombre() + " " + usuario.getApellidos());
			claims.put("roles", usuario.getRoles());

			return claims;
		}

		return null;
	}
}
