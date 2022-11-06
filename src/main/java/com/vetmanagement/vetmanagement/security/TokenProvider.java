package com.vetmanagement.vetmanagement.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vetmanagement.vetmanagement.config.AppProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);
	
	private AppProperties appProperties;

	public TokenProvider(AppProperties appProperties) {
		this.appProperties = appProperties;
	}
	
	public String createToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		Date date = new Date();
		
		Date expiryDate = new Date(date.getTime() + appProperties.getAuth().getTokenExp());
		
		return Jwts.builder()
				.setSubject(Long.toString(userPrincipal.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, appProperties.getAuth().getTokenSecret())
				.compact();		
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(appProperties.getAuth().getTokenSecret())
				.parseClaimsJws(token)
				.getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
	public Boolean validateToken(String authToken) {
		
		try {
			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
			return true;
		}catch(SignatureException ex) {
			LOGGER.error("Invalid Signature");
		}catch(MalformedJwtException ex) {
			LOGGER.error("Invalid Token");
		}catch (ExpiredJwtException ex) {
			LOGGER.error("Expired Token");
		}catch (UnsupportedJwtException ex) {
			LOGGER.error("Unsupported Token");
		}catch (IllegalArgumentException ex) {
			LOGGER.error("Token Claims is Empty");
		}
		
		return false;
		
	}
	
}
