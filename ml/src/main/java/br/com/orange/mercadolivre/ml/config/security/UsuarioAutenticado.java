package br.com.orange.mercadolivre.ml.config.security;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.NotNull;

import br.com.orange.mercadolivre.ml.domain.Usuario;

public class UsuarioAutenticado implements UserDetails {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Usuario usuario;
	private User userDetails;

	public UsuarioAutenticado(@NotNull @Valid Usuario usuario) {
		this.usuario = usuario;
		this.userDetails = new User(usuario.getLogin(), usuario.getSenha(), List.of());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDetails.getAuthorities();
	}

	@Override
	public String getPassword() {
		return userDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return userDetails.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return userDetails.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return userDetails.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return userDetails.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return userDetails.isEnabled();
	}
}
