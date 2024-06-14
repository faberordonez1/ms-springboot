package com.app.productos.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.productos.models.entity.Producto;
import com.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;//Tipo a convertir a JSON
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return this.productoService.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public Producto detalle(@PathVariable Long id){//PathVariable es para obtener el Id en la URL
		return this.productoService.findById(id);
	}

}
