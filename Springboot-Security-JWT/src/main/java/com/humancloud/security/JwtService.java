package com.humancloud.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService
{
	private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
	
	public String generateToken(String username)
	{

	    return Jwts.builder()
	            .subject(username)
	            .issuedAt(new Date())
	            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
	            .signWith(secretKey)
	            .compact();
	}
	
	public String extractUsername(String token)
	{
	    return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver)
	{
	    Claims claims = extractAllClaims(token);
	    return resolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token)
	{

	    return Jwts.parser()
	            .verifyWith(secretKey)
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();
	}
	
	public Date extractExpiration(String token) 
	{
	    return extractClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token)
	{
	    return extractExpiration(token).before(new Date());
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) 
	{

	    String username = extractUsername(token);

	    return username.equals(userDetails.getUsername())
	            && !isTokenExpired(token);
	}
}
