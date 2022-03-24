package com.formacionspringboot.apirest.service;

import java.util.List;

import com.formacionspringboot.apirest.entity.Cliente;
import com.formacionspringboot.apirest.entity.Moto;

public interface MotoService {
	
	public List<Moto> findAll();
	
	public Moto findById(Long id);
	
	public Moto save(Moto moto);
	
	public void delete(Long id);
	
	
	public List<Cliente> findAllClientes();

	
		
	

}