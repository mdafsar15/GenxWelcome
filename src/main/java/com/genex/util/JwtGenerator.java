package com.genex.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {

	public static final String tokenSecret = "afsar";

	public static String generateToken(Long id) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tokenSecret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(String.valueOf(id)).setIssuedAt(now).setSubject("JWT Token")
				.setIssuer(String.valueOf(id)).signWith(signatureAlgorithm, signingKey);

		builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3));

		return builder.compact();
	}

	public static Long decodeToken(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(tokenSecret))
				.parseClaimsJws(jwt).getBody();
		claims.getExpiration();
		return Long.parseLong(claims.getIssuer());
	}

}
