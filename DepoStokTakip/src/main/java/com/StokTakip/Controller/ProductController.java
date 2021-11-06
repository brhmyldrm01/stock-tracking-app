package com.StokTakip.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StokTakip.Model.Item;
import com.StokTakip.Service.ItemService;
import com.StokTakip.Util.BasicAuthentication;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	ItemService itemService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable int id,HttpServletRequest req){
		try {
			 if(BasicAuthentication.auth(req)) {
			Item product = itemService.getItemById(id);
			return new ResponseEntity<Object>(product,HttpStatus.OK);
			
		}else {
			return ResponseEntity
		            .status(HttpStatus.UNAUTHORIZED)
		            .body(("Giriş Yapınız"));
		}
			 }
		catch(Exception exception){
			
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/list")
	public List<Item> productList(HttpServletRequest req) throws ServletException, IOException{
		 if(BasicAuthentication.auth(req)) {
		return itemService.getAllItems();
		}else {
			return null;
		}
	}
	
	
	@PostMapping("/")
	public void AddItem(@RequestBody Item product,HttpServletRequest req) throws ServletException, IOException {
		 if(BasicAuthentication.auth(req)) 
		itemService.createItem(product);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateItem(@RequestBody Item product,HttpServletRequest req){
		try {
			 if(BasicAuthentication.auth(req)) {
			itemService.updateItem(product);
			return new ResponseEntity<>(HttpStatus.OK);
			}
			 else {
				 return ResponseEntity
				            .status(HttpStatus.UNAUTHORIZED)
				            .body(("Giriş Yapınız"));
			 }
		}
		catch(Exception exception){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable int id,HttpServletRequest req) throws ServletException, IOException {
		 if(BasicAuthentication.auth(req)) 
		itemService.deleteItem(id);
	}
	
	@GetMapping("/count")
	public long count(	HttpServletRequest req) throws ServletException, IOException {
		 if(BasicAuthentication.auth(req)) 
		return itemService.getItemCount();
		 else
			 return 0;
	}
	
			
	    
	
	
}