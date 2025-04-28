package com.item.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.item.dto.request.ItemAddRequest;
import com.item.dto.request.ItemDeleteRequest;
import com.item.dto.request.ItemUpdateRequest;
import com.item.dto.response.ItemAddResponse;
import com.item.dto.response.ItemDeleteResponse;
import com.item.dto.response.ItemModifyResponse;
import com.item.dto.response.ItemSearchResponse;
import com.item.dto.response.ItemShowAllByCategoryResponse;
import com.item.dto.response.ItemShowAllByNameResponse;
import com.item.dto.response.ItemShowAllResponse;
import com.item.exception.ItemNotFoundException;
import com.item.model.Item;
import com.item.service.ItemService;


@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService service;
	
	@PostMapping(value="/add")
	public ResponseEntity<ItemAddResponse> f1(@RequestBody ItemAddRequest request) {
		Item item = this.service.addNewItem(request.getItem());
		ItemAddResponse addResponse = new ItemAddResponse();
		addResponse.setStatusCode(201);
		addResponse.setDescription("Item Added Successfully");
		addResponse.setItem(item);
		
		return new ResponseEntity<>(addResponse,HttpStatus.CREATED);
	}
	
	@PutMapping(value="/modify")
	public ResponseEntity<ItemModifyResponse> f2(@RequestBody ItemUpdateRequest request) {
		ItemModifyResponse modifyResponse = new ItemModifyResponse();
		Item item1 = this.service.searchItem(request.getItem());
		if(item1 != null) {
			Item item2 = this.service.updateItem(request.getItem());
			modifyResponse.setStatusCode(200);
			modifyResponse.setDescription("Item Modified Successfully");
			modifyResponse.setItem(item2);
			return ResponseEntity.ok(modifyResponse);
		}
		else {
			modifyResponse.setStatusCode(404);
			modifyResponse.setDescription("Item Not Found for Modification");
			modifyResponse.setItem(null);
			return new ResponseEntity<ItemModifyResponse>(modifyResponse,HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping(value="/find/{id}")
	public ResponseEntity<ItemSearchResponse> f3(@PathVariable(name="id") int id) throws Exception {
		ItemSearchResponse response = new ItemSearchResponse();
		Item item = this.service.searchItem(id);
		if(item!=null) {
			response.setStatusCode(200);
			response.setDescription("Item Fetched Successfully");
			response.setItem(item);
			return new ResponseEntity<ItemSearchResponse>(response, HttpStatus.OK);
		}
		else {
			Exception exception = new ItemNotFoundException("Item Not Found");
			throw exception;
		}
	}
	
	
	@GetMapping(value="/showAll")
	public ResponseEntity<ItemShowAllResponse> f4(){
		List<Item> items = this.service.getAllItems();
		ItemShowAllResponse response = new ItemShowAllResponse();
		response.setStatusCode(200);
		response.setDescription("All Items Fetched");
		response.setItems(items);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value="/delete")
	public ResponseEntity<ItemDeleteResponse> f5(@RequestBody ItemDeleteRequest request) {
		ItemDeleteResponse response = new ItemDeleteResponse();
		Item item1 = this.service.searchItem(request.getItem());
		if(item1!=null)
		{
			try {
				this.service.deleteItem(request.getItem());
				response.setStatusCode(200);
				response.setDescription("Item Deleted Successfully");
				response.setDeleteStatus(true);
				return ResponseEntity.ok().body(response);
			}
			catch(Exception e) {
				response.setStatusCode(500);
				response.setDescription("Item Not Deleted");
				response.setDeleteStatus(false);
				return ResponseEntity.internalServerError().body(response);
			}
		}
		else {
			response.setStatusCode(404);
			response.setDescription("Item Not Found");
			response.setDeleteStatus(false);
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
		
	@GetMapping("/showAllByName/{name}")
	public ResponseEntity<ItemShowAllByNameResponse> f6(@PathVariable(name="name") String name){
		ItemShowAllByNameResponse response = new ItemShowAllByNameResponse();
		List<Item> itemsBySameName = this.service.getAllItemsBySameName(name);
		if(itemsBySameName.isEmpty()) {
			response.setStatusCode(200);
			response.setDescription("There are no items by same name "+name);
			response.setItems(itemsBySameName);
		}
		else {
			response.setStatusCode(200);
			response.setDescription("There are "+ itemsBySameName.size()+" items with same name "+name);
			response.setItems(itemsBySameName);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/showAllByCategory")
	public ResponseEntity<ItemShowAllByCategoryResponse> f7(@RequestParam(name="category") String category){
		ItemShowAllByCategoryResponse response = new ItemShowAllByCategoryResponse();
		List<Item> itemsBySameCategory = this.service.getAllItemsBySameCategory(category);
		if(itemsBySameCategory.isEmpty()) {
			response.setStatusCode(200);
			response.setDescription("There are no items in the category - "+category);
			response.setItems(itemsBySameCategory);
		}
		else {
			response.setStatusCode(200);
			response.setDescription("There are "+ itemsBySameCategory.size()+" items in same category - "+category);
			response.setItems(itemsBySameCategory);
		}
		return ResponseEntity.ok(response);
	}
}
