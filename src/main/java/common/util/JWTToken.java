package common.util;

import java.security.Key;
import common.security.Sha256;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTToken {

	public static String getSubject(String token) {
		Key key = Sha256.generateKey();
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
