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



@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private ClienteService servicio;
	
	@GetMapping({"/clientes","/todos"})
	public List<Cliente> index(){
		return servicio.findAll();
		
	}
	/*
	@GetMapping("/clientes/{id}")
	public Cliente findClienteById(@PathVariable Long id) {
		return servicio.findById(id);
	}*/
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> findClienteById(@PathVariable Long id){
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			cliente = servicio.findById(id);
			
		}catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(cliente == null) {
			
			response.put("mensaje", "El cliente ID:" +id.toString()+("no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	
	/*@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente saveCliente(@RequestBody Cliente cliente) {
		return servicio.save(cliente);
	}*/
	
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente){
		Cliente clienteNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			clienteNew = servicio.save(cliente);
			
		}catch (DataAccessException e){
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El clinte ha sido creado con exito!");
		response.put("cliente", clienteNew);
		
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
	
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente clienteActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(clienteActual == null) {
			response.put("mensaje", "Error: no se puedo editar, el cliente con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		try {
			
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setCreatedAt(cliente.getCreatedAt());
			clienteActual.setMoto(cliente.getMoto());
			
			servicio.save(clienteActual);
			
		}catch (DataAccessException e){
			
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje","El clinte ha sido actualizao con exito!");
		response.put("cliente", clienteActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
		
	}
	
	
	/*@DeleteMapping("/cliente/{id}")
	public void deleteCliente(@PathVariable Long id) {
		servicio.delete(id);
		
	}*/
	
	@PostMapping("/cliente")
	public ResponseEntity<?> save(@RequestBody Cliente cliente)
	{
	Cliente clienteNew = null;
	Map<String,Object> response = new HashMap<>();

	try
	{
	clienteNew = servicio.save(cliente);
	}
	catch(DataAccessException e)
	{
	response.put("mensaje", "Error al realizar una insert a base de datos");
	response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	response.put("mensaje", "El cliente ".concat(clienteNew.getNombre()).concat(" ha sido creado con ??xito"));
	response.put("cliente", clienteNew);
	return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		
		Cliente clienteActual = servicio.findById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if(clienteActual == null) {
			response.put("mensaje", "Error: no se puedo editar, el cliente con ID: "+id.toString()+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
			
		}
		
		try {
		
		servicio.delete(id);
		
		
		
		}catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		response.put("mensaje","El cliente ha sido eliminado con exito!");
		response.put("cliente", clienteActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}

	
	
	@PostMapping("cliente/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente = servicio.findById(id);
		
		if(!archivo.isEmpty()) {
			//String nombreArchivo = archivo.getOriginalFilename();
			String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", " ");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			
		
		
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			
			
		}catch (IOException e){
			
			response.put("mensaje", "Error al subir la imagen");
			response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
		
		
		
		servicio.save(cliente);
		
		response.put("cliente", cliente);
		response.put("mensaje", "Has subido correctamente la imagen:" +nombreArchivo);
		
	
		
	}
	
	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	
	}
	
	
	
	
	@GetMapping("/clientes/motos")
	public List<Moto> listarMotos(){
		return servicio.findAllMotos();
	}
	
}