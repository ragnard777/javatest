package com.nttdata.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.springboot.backend.apirest.model.entity.Trabajador;
import com.nttdata.springboot.backend.apirest.models.dao.ITrabajadorDao;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {
	
	@Autowired
	private ITrabajadorDao trabajadorDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Trabajador> findAll() {
		return (List<Trabajador>) trabajadorDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Trabajador findById(Long id) {
		return trabajadorDao.findById(id).orElse(null);
	}

	@Override
	public Trabajador save(Trabajador trabajador) {
		// TODO Auto-generated method stub
		return trabajadorDao.save(trabajador);
	}

	@Override
	public void delete(Long id) {
		trabajadorDao.deleteById(id);
	}

	
	
	
	
}
