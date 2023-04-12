package com.nttdata.springboot.backend.apirest.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.springboot.backend.apirest.model.entity.Trabajador;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.springboot.backend.apirest.models.services.ITrabajadorService;


//@CrossOrigin(origins = {"http://localhost:4200"}) Cambiar de donde se vaya a consumir... Se deja el port de Angular por defecto.
@RestController
@RequestMapping("/api")
public class TrabajadoresRestController {
	
	@Autowired
	private ITrabajadorService trabajadorService;
	
	@GetMapping("/trabajadores")
	public List<Trabajador> index(){
		return trabajadorService.findAll();
	}
	
	@GetMapping("/trabajadores/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id){ //Como es un generic se coloca el signo de pregunta.
		
		Trabajador trabajador = null;
		Map<String,Object> response = new HashMap<>();
		//ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		try {
			trabajador = trabajadorService.findById(id);	
		} catch(DataAccessException e) {
			response.put("mensaje"," Error al realizar la consulta en la base de datos ");
			response.put("error",e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(trabajador == null) {
			response.put("mensaje","El cliente ID: ".concat(id.toString().concat(" No existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Trabajador>(trabajador, HttpStatus.OK);
	}
	
	@PostMapping("/Trabajadores")
	@ResponseStatus(HttpStatus.CREATED) // 
	public Trabajador create(@RequestBody Trabajador trabajador){
		return trabajadorService.save(trabajador);
	}
	
	@PutMapping("/trabajadores/{id}")
	@ResponseStatus(HttpStatus.CREATED) // 
	public ResponseEntity<Map<String, Object>> update(@RequestBody Trabajador trabajador, @PathVariable Long id){
		
		Trabajador trabajadorActual = trabajadorService.findById(id);
		Trabajador trabajadorUpdated = null;
		Map<String,Object> response = new HashMap<>();
		
		if(trabajadorActual == null) {
			//{"mensaje": "mensaje de error"}
			response.put("mensaje", "Error: no se pudo editar, el trabajador: ".concat(id.toString().concat(" No existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		

		try {
			trabajadorActual.setNombre(trabajador.getNombre());
			trabajadorActual.setEmail(trabajador.getEmail());
			trabajadorUpdated = trabajadorService.save(trabajadorActual);
		}catch(DataAccessException e) {
			//{"mensaje": "mensaje de error"}
			response.put("Error","Error al actualizar al cliente en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El trabajador ha sido creado con Exito");
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/trabajadores/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // retornar un 204 para indicar que se creo correctamente.
	public ResponseEntity<?> delete(@PathVariable Long id){	
		
		Map<String,Object> response = new HashMap<>();
		

		try {
			trabajadorService.delete(id);
		}catch(DataAccessException e) {
			//{"mensaje": "mensaje de error"}
			response.put("Error","Error al Eliminar al cliente en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Trabajador ha sido eliminado con Exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	
	

}
