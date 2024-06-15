package com.app.item.model.service;

import java.util.List;

import com.app.item.model.Item;

public interface ItemService {
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);

}
