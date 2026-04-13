package segundum.pasarela.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import segundum.pasarela.puerto.ClienteUsuarios;
import segundum.pasarela.puerto.dto.UsuarioLoginInputDTO;
import segundum.pasarela.puerto.dto.UsuarioLoginResponseDTO;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

	private static final int JWT_TIEMPO_VALIDEZ = 3600; // 1 hora

	@Autowired
	private ClienteUsuarios clienteUsuarios;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) {

		Map<String, Object> claims = verificarCredenciales(username, password);
		if (claims != null) {
			String token = JwtUtils.generateToken(claims);

			// ENVIO DE COOKIE
			Cookie cookie = new Cookie("jwt", token);
			cookie.setMaxAge(JWT_TIEMPO_VALIDEZ);
			// cookie.setSecure(true); // para https
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			response.addCookie(cookie);

			// RESPUESTA
			Map<String, Object> respuesta = new HashMap<>();
			respuesta.put("token", token);
			respuesta.put("id", claims.get("sub"));
			respuesta.put("nombre", claims.get("nombre"));
			respuesta.put("roles", claims.get("roles"));

			return ResponseEntity.ok(respuesta);
		} else {
			Map<String, Object> error = new HashMap<>();
			error.put("error", "Credenciales inválidas");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
	}

	private Map<String, Object> verificarCredenciales(String username, String password) {
		try {
			UsuarioLoginInputDTO dto = new UsuarioLoginInputDTO();
			dto.setEmail(username);
			dto.setPassword(password);
			UsuarioLoginResponseDTO usuario = clienteUsuarios.login(dto);
			if (usuario == null)
				return null;

			Map<String, Object> claims = new HashMap<>();
			claims.put("sub", usuario.getId());
			claims.put("nombre", usuario.getNombre() + " " + usuario.getApellidos());
			claims.put("roles", usuario.getRoles());

			return claims;
		} catch (Exception e) {
			return null;
		}
	}
}