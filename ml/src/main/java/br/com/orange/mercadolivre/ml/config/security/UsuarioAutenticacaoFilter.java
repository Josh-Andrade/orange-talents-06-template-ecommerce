package br.com.orange.mercadolivre.ml.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class UsuarioAutenticacaoFilter extends OncePerRequestFilter {

	private UsuarioService usuarioService;

	private TokenManager tokenManager;

	public UsuarioAutenticacaoFilter(UsuarioService usuarioService, TokenManager tokenManager) {
		this.usuarioService = usuarioService;
		this.tokenManager = tokenManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Optional<String> optionalToken = getTokenFromRequest(request);

		if(optionalToken.isPresent() && tokenManager.isTokenValid(optionalToken.get())) {
			String userName = tokenManager.getUserName(optionalToken.get());
			
			UserDetails userDetails = usuarioService.loadUserByUsername(userName);
			
			UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(autenticacao);
		}
		
		filterChain.doFilter(request, response);
	}

	private Optional<String> getTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		return Optional.ofNullable(token);
	}

}
