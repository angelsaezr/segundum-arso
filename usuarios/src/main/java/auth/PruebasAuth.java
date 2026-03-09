package auth;

import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class PruebasAuth {

	public static void main(String[] args) {
		Map<String, Object> claims = new HashMap<String, Object>();

		claims.put("sub", "Juan");
		claims.put("roles", "USUARIO");

		String token = JwtUtils.generateToken(claims);

		System.out.println(token);

		Claims claims2 = Jwts.parser().setSigningKey("secreto").parseClaimsJws(token).getBody();

		System.out.println(claims2.getSubject());
	}
}
