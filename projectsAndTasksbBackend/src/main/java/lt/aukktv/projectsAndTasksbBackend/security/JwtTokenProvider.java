package lt.aukktv.projectsAndTasksbBackend.security;

import static lt.aukktv.projectsAndTasksbBackend.security.SecurityConstants.EXPIRATION_TIME;
import static lt.aukktv.projectsAndTasksbBackend.security.SecurityConstants.SECRET;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lt.aukktv.projectsAndTasksbBackend.domain.User;

@Component
public class JwtTokenProvider {

	// generate the token
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());

		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

		String userId = Long.toString(user.getId());

		Map<String, Object> claims = new HashMap<>();

		claims.put("id", Long.toString(user.getId()));
		claims.put("username", user.getUsername());
		claims.put("fullName", user.getFullName());

		return Jwts.builder().setSubject(userId).setClaims(claims).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	// validate the token

	// get user id from token
}
