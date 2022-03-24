package com.formacionspringboot.apirest.entity;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="motos")
public class Moto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String marca;
	private String modelo;
	private Long potencia;
	private Long precio;
	
	
	
	
	
	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getMarca() {
		return marca;
	}





	public void setMarca(String marca) {
		this.marca = marca;
	}





	public String getModelo() {
		return modelo;
	}





	public void setModelo(String modelo) {
		this.modelo = modelo;
	}





	public Long getPotencia() {
		return potencia;
	}





	public void setPotencia(Long potencia) {
		this.potencia = potencia;
	}





	public Long getPrecio() {
		return precio;
	}





	public void setPrecio(Long precio) {
		this.precio = precio;
	}





	
	

}
