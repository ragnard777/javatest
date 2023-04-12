package com.nttdata.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nttdata.springboot.backend.apirest.model.entity.Trabajador;

public interface ITrabajadorDao extends CrudRepository<Trabajador,Long> {
		
}
