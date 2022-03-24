package com.formacionspringboot.apirest.controller;



import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.formacionspringboot.apirest.entity.Cliente;
import com.formacionspringboot.apirest.entity.Moto;
import com.formacionspringboot.apirest.service.ClienteService;
import com.formacionspringboot.apirest.service.MotoService;



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class MotoRestController {
	
	@Autowired
	private MotoService servicio;
	
	@GetMapping({"/motos","/todos"})
	public List<Moto> index(){
		return servicio.findAll();
		
	}
	/*
	@GetMapping("/clientes/{id}")
	public Cliente findClienteById(@PathVariable Long id) {
		return servicio.findById(id);
	}*/
	
	@GetMapping("/motos/{id}")
	public ResponseEntity<?> findMotoById(@PathVariable Long id){
		Moto moto = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			moto = servicio.findById(id);
			
		}catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(moto == null) {
			
			response.put("mensaje", "El moto ID:" +id.toString()+("no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<Moto>(moto,HttpStatus.OK);
	}
	
	
	/*@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody Cliente cliente) {
		return servicio.save(cliente);
	}*/
	
	public ResponseEntity<?> saveMoto(@RequestBody Moto moto){
		Moto motoNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			motoNew = servicio.save(moto);
			
		}catch (DataAccessException e){
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","La moto ha sido creada con exito!");
		response.put("moto", motoNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	/*@PutMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente updateCliente(@RequestBody Cliente cliente,@PathVariable Long id) {
		Cliente clienteUpdate = servicio.findById(id);
		
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setApellido(cliente.getApellido());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreatedAt(cliente.getCreatedAt());
		
		return servicio.save(clienteUpdate);
	}*/
	
	
	@PutMapping("/moto/{id}")
	public ResponseEntity<?> updateMoto(@RequestBody Moto moto, @PathVariable Long id){
		Moto motoActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(motoActual == null) {
			response.put("mensaje", "Error: no se puedo editar, la moto con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			motoActual.setMarca(moto.getMarca());
			motoActual.setModelo(moto.getModelo());
			motoActual.setPotencia(moto.getPotencia());
			motoActual.setPrecio(moto.getPrecio());
			
			
			servicio.save(motoActual);
			
		}catch (DataAccessException e){
			
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje","La moto ha sido actualizada con exito!");
		response.put("moto", motoActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
		
	}
	
	
	/*@DeleteMapping("/cliente/{id}")
	public void deleteCliente(@PathVariable Long id) {
		servicio.delete(id);
		
	}*/
	
	@PostMapping("/moto")
	public ResponseEntity<?> save(@RequestBody Moto moto)
	{
	Moto motoNew = null;
	Map<String,Object> response = new HashMap<>();

	try
	{
	motoNew = servicio.save(moto);
	}
	catch(DataAccessException e)
	{
	response.put("mensaje", "Error al realizar una insert a base de datos");
	response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	response.put("mensaje", "La moto ".concat(motoNew.getMarca()).concat(" ha sido creado con éxito"));
	response.put("moto", motoNew);
	return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/moto/{id}")
	public ResponseEntity<?> deleteMoto(@PathVariable Long id) {
		
		Moto motoActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(motoActual == null) {
			response.put("mensaje", "Error: no se puedo editar, la moto con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		try {
		
		servicio.delete(id);
		
		
		
		}catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		response.put("mensaje","La moto ha sido eliminada con exito!");
		response.put("moto", motoActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}

	
	
	
	@GetMapping("/motos/clientes")
	public List<Cliente> listarClientes(){
		return servicio.findAllClientes();
	}
	
}