package br.com.orange.mercadolivre.ml.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	@Value("${ml.jwt.expiration}")
	private Long expirationMilli;

	@Value("${ml.jwt.secret}")
	private String secret;

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public String getUserName(String token) {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
	}

	public String gerarToken(Authentication authenticate) {
		UserDetails usuario = (UserDetails) authenticate.getPrincipal();

		Date geradoEm = new Date();
		Date expiraEm = new Date(geradoEm.getTime() + this.expirationMilli);
		return Jwts.builder().setSubject(usuario.getUsername()).setIssuer("Desafio Mercado Livre").setIssuedAt(geradoEm)
				.setExpiration(expiraEm).signWith(SignatureAlgorithm.HS256, this.secret).compact();
	}
}
