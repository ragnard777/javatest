package com.nttdata.springboot.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nttdata.springboot.backend.apirest.model.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {

		//public Usuario findByEmail(String email);
		
		@Query("select u from Usuario as u where u.email=?1")
		public Usuario findByEmail(String email);
		
		
		
		
}
