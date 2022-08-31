package com.cgm.bulletin.ojt.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.cgm.bulletin.ojt.bl.dto.UserDto;
import com.cgm.bulletin.ojt.bl.service.AuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * <h2>JwtTokenProvider Class</h2>
 * <p>
 * Process for Displaying JwtTokenProvider
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Component
public class JwtTokenProvider {
	/**
	 * <h2>LOGGER</h2>
	 * <p>
	 * LOGGER
	 * </p>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	/**
	 * <h2>jwtSecret</h2>
	 * <p>
	 * jwtSecret
	 * </p>
	 */
	@Value(value = "${app.jwtSecret}")
	private String jwtSecret;

	/**
	 * <h2>jwtExpirationInMs</h2>
	 * <p>
	 * jwtExpirationInMs
	 * </p>
	 */
	@Value(value = "${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthenticationService authService;

	/**
	 * <h2>generateToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param authentication
	 * @return
	 * @return String
	 */
	public String generateToken(Authentication authentication) {
		UserDto userPrincipal = this.authService.doGetLoggedInUser();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder().setSubject(Integer.toString(userPrincipal.getId())).setIssuedAt(new Date())
		        .setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * <h2>getUserIdFromJWT</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return
	 * @return Long
	 */
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return Long.valueOf(claims.getSubject());
	}

	/**
	 * <h2>validateToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param authToken
	 * @return
	 * @return boolean
	 */
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			LOGGER.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			LOGGER.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			LOGGER.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			LOGGER.error("JWT claims string is empty");
		}
		return false;
	}

	/**
	 * <h2>refreshToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param jtoken
	 * @return
	 * @return String
	 */
	public String refreshToken(String jtoken) {
		final Date createdDate = new Date();
		final Date expirationDate = calculateExpirationDate(createdDate);
		final String token = jtoken.substring(7, jtoken.length());

		final Claims claims = getAllClaimsFromToken(token);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * <h2>calculateExpirationDate</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param createdDate
	 * @return
	 * @return Date
	 */
	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() - 1);
	}

	/**
	 * <h2>getAllClaimsFromToken</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param token
	 * @return
	 * @return Claims
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}
}