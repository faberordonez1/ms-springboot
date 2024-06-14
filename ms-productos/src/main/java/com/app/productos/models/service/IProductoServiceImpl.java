package com.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.productos.models.dao.ProductoDao;
import com.app.productos.models.entity.Producto;
@Service
public class IProductoServiceImpl implements IProductoService {
	
	@Autowired
	private ProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) this.productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		//con el punto despues del findById se puede especificar el tipo de dato a retornar, si no encuentra y demás
		return this.productoDao.findById(id).orElse(null);
	}

}
