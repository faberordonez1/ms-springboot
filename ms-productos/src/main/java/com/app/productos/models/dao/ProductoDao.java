package com.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.app.productos.models.entity.Producto;
//El segundo parametro es el tipo de dato de la PK
public interface ProductoDao extends CrudRepository<Producto, Long> {

}
