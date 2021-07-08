package br.com.orange.mercadolivre.ml.config.security;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class AutenticacaoController {

	private AuthenticationManager authenticationManager;

	private TokenManager tokenManager;

	public AutenticacaoController(AuthenticationManager authenticationManager, TokenManager tokenManager) {
		this.authenticationManager = authenticationManager;
		this.tokenManager = tokenManager;
	}

	@PostMapping
	public ResponseEntity<String> autenticar(@RequestBody @Valid LoginRequest loginRequest) {

		UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuth();
		
		try {
			Authentication authenticate = authenticationManager.authenticate(authenticationToken);
			String tokenGerado = tokenManager.gerarToken(authenticate);
			return ResponseEntity.ok(tokenGerado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		
	}
}
