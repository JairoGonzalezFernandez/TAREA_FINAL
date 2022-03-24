package com.formacionspringboot.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspringboot.apirest.entity.Cliente;
import com.formacionspringboot.apirest.entity.Moto;

@Repository
public interface MotoDao extends CrudRepository<Moto, Long>{
	
	@Query("from Cliente")
	public List<Cliente> findAllClientes();
	

}