package com.item.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.item.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	List<Item> findByItemName(String itemName);
	List<Item> findByCategory(String category);
}
