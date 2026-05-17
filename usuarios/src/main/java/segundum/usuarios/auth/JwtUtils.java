package segundum.usuarios.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtils {
	private static final String SECRETO = "secreto";

	public static Claims validateToken(String token) {
		return Jwts.parser().setSigningKey(SECRETO).parseClaimsJws(token).getBody();
	}
}