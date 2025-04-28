package com.item.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.db.ItemRepository;
import com.item.model.Item;

@Service
public class ItemService {

	@Autowired
	ItemRepository repo;
	
	public Item addNewItem(Item item) {
		return repo.save(item);
	}
	
	public Item updateItem(Item item) {
		return repo.save(item);
	}
	
	public Item searchItem(Item item) {
		Optional<Item> optional = repo.findById(item.getItemId());
		if(optional.isPresent())
			return optional.get();
		else
			return null;
	}

	public Item searchItem(int id) {
		Optional<Item> optional = repo.findById(id);
		if(optional.isPresent())
			return optional.get();
		else
			return null;
	}
	
	public List<Item> getAllItems(){
		return repo.findAll();
	}
	
	public boolean deleteItem(Item item) {
		repo.delete(item);
		return true;
	}
	
	public List<Item> getAllItemsBySameName(String name){
		return repo.findByItemName(name);
	}
	
	public List<Item> getAllItemsBySameCategory(String category){
		return repo.findByCategory(category);
	}
}
