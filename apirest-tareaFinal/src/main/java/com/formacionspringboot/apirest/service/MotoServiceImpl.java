package com.formacionspringboot.apirest.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspringboot.apirest.dao.MotoDao;
import com.formacionspringboot.apirest.entity.Cliente;
import com.formacionspringboot.apirest.entity.Moto;



@Service
public class MotoServiceImpl implements MotoService{
	
	@Autowired
	private MotoDao motoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Moto> findAll() {
		return (List<Moto>) motoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Moto findById(Long id) {
		return motoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Moto save(Moto moto) {
		return motoDao.save(moto);
	}

	@Override
	@Transactional
	public void delete(Long id) 
	{
		motoDao.deleteById(id);
		
	}

	@Override
	public List<Cliente> findAllClientes() {
		return motoDao.findAllClientes();
	}




	
	


}
