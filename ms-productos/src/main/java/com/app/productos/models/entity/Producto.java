package com.app.productos.models.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity //Indica a JPA que esta clase pertenece a una tabla BD
@Table(name = "productos") //Se indica el nombre de la tabla en BD, toma el nombre de la clase
public class Producto implements Serializable{

	@Id //Indica a JPA que este atributo es la clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Indica a JPA que el valor en tabla BD de este atributo es autoincremental
	private Long id;
	
	private String nombre;
	private Double precio;
	
	//Si la columna de BD no se llama igual que el atributo, se indica el nombre de la columna
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE) // Solo guarda el año, mes y dia
	private Date createAt;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	private static final long serialVersionUID = -385614459167395679L;

}
