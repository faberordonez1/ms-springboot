package com.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.item.model.Producto;

//Se define que la interface es un cliente feign con la anotacion.
@FeignClient(name = "ms-productos", url="localhost:8001")//  Se indica el nombre del microservicio y la url, Corresponde a spring.application.name=ms-productos del aplication.properties del otro ms
public interface ProductoClienteRest {
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/listar/{id}")
	public Producto detalle(@PathVariable Long id);
}
