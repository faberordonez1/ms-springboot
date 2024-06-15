package com.app.item.model.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.item.model.Item;
import com.app.item.model.Producto;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {//Parseo de array a lista de productos.🟦 url y el tipo de dato que deseamos obtener ([]Producto)
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
	    // Transforma lista de productos a lista de items.
		return productos.stream().map( p -> new Item(p,1)).collect(Collectors.toList());		  
		// return productos.stream().map(p -> new Item(p, 1)).toList(); //Recomendado codeium
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String,String> pathVariable = new HashMap<>();
		pathVariable.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://localhost:8001/listar/{id}", Producto.class, pathVariable);
		return new Item(producto,cantidad);
	}

}
