package com.nttdata.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.springboot.backend.apirest.model.entity.Usuario;
import com.nttdata.springboot.backend.apirest.models.dao.IUsuarioDao;
import org.slf4j.*;

@Service
public class UsuarioService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		System.out.println("Usuario "+email);
		Usuario usuario = usuarioDao.findByEmail(email);
		if(usuario == null) {
			logger.error("Error no existe el usuario");
			throw new UsernameNotFoundException("Error en el login no existe el usuario "+email+" en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getPhoneList()
				.stream()
				.map(phone -> new SimpleGrantedAuthority(phone.getNumber()))
				.peek(authority -> logger.info("Phone: "+authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getNombre(),usuario.getEmail(), authorities);
	}

}
