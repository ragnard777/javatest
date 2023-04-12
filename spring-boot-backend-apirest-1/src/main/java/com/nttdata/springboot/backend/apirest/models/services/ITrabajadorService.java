package com.nttdata.springboot.backend.apirest.models.services;

import java.util.List;

import com.nttdata.springboot.backend.apirest.model.entity.Trabajador;

public interface ITrabajadorService {
	
	
	public List<Trabajador> findAll();
	
	public Trabajador findById(Long id);
	
	public Trabajador save(Trabajador trabajador);
	
	public void delete(Long id);
	
	
	
}
