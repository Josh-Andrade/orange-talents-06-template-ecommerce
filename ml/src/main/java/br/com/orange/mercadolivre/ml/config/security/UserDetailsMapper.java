package br.com.orange.mercadolivre.ml.config.security;

import org.springframework.security.core.userdetails.UserDetails;

import br.com.orange.mercadolivre.ml.domain.Usuario;

public interface UserDetailsMapper {
	
	public UserDetails map(Usuario usuario);
}
