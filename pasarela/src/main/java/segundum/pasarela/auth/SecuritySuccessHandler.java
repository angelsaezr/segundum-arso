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
import segundum.pasarela.puerto.dto.UsuarioGithubInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

@Component
public class SecuritySuccessHandler implements AuthenticationSuccessHandler {

	private static final int JWT_TIEMPO_VALIDEZ = 3600;

	@Autowired
	private ClienteUsuarios clienteUsuarios;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		DefaultOAuth2User githubUser = (DefaultOAuth2User) authentication.getPrincipal();

		String githubId = String.valueOf(githubUser.getAttributes().get("id"));

		// Datos opcionales que GitHub puede facilitar
		String githubLogin = (String) githubUser.getAttributes().get("login"); // nombre de usuario
		String githubNombre = (String) githubUser.getAttributes().get("name"); // nombre real (puede ser null)
		String githubEmail = (String) githubUser.getAttributes().get("email"); // email (puede ser null)

		Map<String, Object> claims = null;
		try {
			claims = fetchUserInfo(githubId, githubNombre, githubLogin, githubEmail);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Error al procesar el inicio de sesión con GitHub");
			return;
		}

		if (claims == null) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"No se pudo resolver el usuario de GitHub");
			return;
		}

		// Generar JWT
		String token = JwtUtils.generateToken(claims);

		// Enviar token en cookie http-only
		Cookie cookie = new Cookie("jwt", token);
		cookie.setMaxAge(JWT_TIEMPO_VALIDEZ);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		// Escribir el token en la respuesta JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"token\":\"" + token + "\"," + "\"id\":\"" + claims.get("sub") + "\","
				+ "\"nombre\":\"" + claims.get("nombre") + "\"," + "\"roles\":\"" + claims.get("roles") + "\"}");
	}

	private Map<String, Object> fetchUserInfo(String githubId, String githubNombre, String githubLogin,
			String githubEmail) throws Exception {

		// Intenta encontrar el usuario vinculado a este GitHub ID
		UsuarioLoginResponseDTO usuario = clienteUsuarios.buscarPorGithubId(githubId);

		// Si no existe, crear automáticamente con los datos que GitHub proporciona
		if (usuario == null) {
			// Usar el nombre real si está disponible, si no el login de GitHub
			String nombre = (githubNombre != null && !githubNombre.isEmpty()) ? githubNombre : githubLogin;

			String apellidos = "";

			UsuarioGithubInputDTO nuevoUsuario = new UsuarioGithubInputDTO(githubId, nombre, apellidos, githubEmail);

			usuario = clienteUsuarios.crearUsuarioGithub(nuevoUsuario);
		}

		// Construir claims para el JWT
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", usuario.getId());
		claims.put("nombre", usuario.getNombre() + " " + usuario.getApellidos());
		claims.put("roles", usuario.getRoles());

		return claims;
	}
}