package br.com.orange.mercadolivre.ml.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.orange.mercadolivre.ml.domain.Usuario;

@Component 
public class UserDetailsMapperImpl implements UserDetailsMapper{

	@Override
	public UserDetails map(Usuario usuario) {
		return new UsuarioAutenticado(usuario);
	}

}
