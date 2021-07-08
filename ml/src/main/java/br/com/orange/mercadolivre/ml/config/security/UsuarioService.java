package br.com.orange.mercadolivre.ml.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.orange.mercadolivre.ml.domain.Usuario;
import br.com.orange.mercadolivre.ml.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	private UsuarioRepository usuariorepository;

	private UserDetailsMapper mapper;

	public UsuarioService(UsuarioRepository usuariorepository, UserDetailsMapper mapper) {
		this.usuariorepository = usuariorepository;
		this.mapper = mapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optionalUsuario = usuariorepository.findByLogin(username);
		Assert.isTrue(optionalUsuario.isPresent(), "Usuário não encontrado");
		return mapper.map(optionalUsuario.get());
	}

}
