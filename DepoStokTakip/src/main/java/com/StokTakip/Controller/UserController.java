package com.StokTakip.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StokTakip.DTO.UserCreateDTO;
import com.StokTakip.Exception.Exceptions;
import com.StokTakip.Model.Category;
import com.StokTakip.Model.Store;
import com.StokTakip.Model.User;
import com.StokTakip.Service.CategoryServiceImpl;
import com.StokTakip.Service.StoreServiceImpl;
import com.StokTakip.Service.UserServiceImpl;
import com.StokTakip.Util.Base64Token;
import com.StokTakip.Util.BasicAuthentication;
import com.StokTakip.Util.ResponseHandler;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserServiceImpl  userService;
	
	@Autowired
	CategoryServiceImpl categoryService;
	
	@Autowired 
	StoreServiceImpl storeService; 
	
	ResponseHandler responseHandler=new ResponseHandler();
	
	@GetMapping("/{id}")
	  ResponseEntity<Object> getUser(@PathVariable int id) {
	   try {
	    	
		  Optional<User> user=userService.getUserById(id);
		  Map <String,Object> response=new HashMap<String, Object>();
		  response.put("data",responseHandler.generateUserResponse(user.get())) ;
		  response.put("result",responseHandler.generateResult("Success", HttpStatus.OK.value())) ;
		return  ResponseEntity
		            .status(HttpStatus.OK)
		            .body(response);
			
			
		} catch (Exception e) {
			return  ResponseEntity
		            .status(HttpStatus.CONFLICT)
		            .body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
		}
	  }
	
	@GetMapping("/all")
	  ResponseEntity<Object> getAllUser() {
	   try {
	    
		  
		  List<User> users=userService.getAllUsers();
		  
		  Map <String,Object> response=new HashMap<String, Object>();
		   
		  	for(int i=0;i<users.size();i++) {
		  		response.put("data "+i,responseHandler.generateUserResponse(users.get(i)));
		  	}
		  	response.put("result",responseHandler.generateResult("Success", HttpStatus.OK.value())) ;
		  
			return  ResponseEntity
		            .status(HttpStatus.OK)
		            .body(response);
			
			
		} catch (Exception e) {
			return  ResponseEntity
		            .status(HttpStatus.CONFLICT)
		            .body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
		}
	  }
	
	
	@PostMapping("register")
    public ResponseEntity<Object> addUser(@RequestBody UserCreateDTO Userdto) {
		
		User user=new User(Userdto.getName(),Userdto.getEmail(),Userdto.getPassword(),new Date(),null);
		
		try {
			
			userService.createUser(user);
			
			user=userService.findByEmail(Userdto.getEmail());
			
			Category category=new Category(user.getId(), "default", "default category",new Date(),false,user);
			
			Store store =new Store(user.getId(),"default","default","default store",new Date(),false,user);
			
			categoryService.createCategory(category);
			storeService.createStore(store);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(responseHandler.generateResult("Kayıt Başarılı",HttpStatus.OK.value()));
			
			
		} catch (Exceptions e) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
		}
		
		
		
	}
	
	
	
	@PostMapping("/login")
	 public ResponseEntity<Object> authUser(@RequestBody User user,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) {
		
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			
		    User newUser =userService.findByEmail(user.getEmail());
		    
			if(user.getPassword().equals(newUser.getPassword())) {
			
				String token=Base64Token.encode(newUser.getEmail(),newUser.getPassword());
				 headers.add(HttpHeaders.AUTHORIZATION,token);
				 BasicAuthentication.loggedIn(newUser.getEmail(),newUser.getPassword());
				
				 System.out.println(token);
				String[] pass=Base64Token.decode(token);
				System.out.println("username:"+pass[0]);
				System.out.println("pass:"+pass[1]);
				
				 Map <String,Object> response=new HashMap<String, Object>();
				 	
				  response.put("data",responseHandler.generateUserResponse(newUser)) ;
				  response.put("result",responseHandler.generateResult("Success", HttpStatus.OK.value()));
				 return  ResponseEntity
						 .status(HttpStatus.OK)
						 .headers(headers)
						 .body(response);
						 
			}else
			{
				return ResponseEntity
			            .status(HttpStatus.UNAUTHORIZED)
			            .body(responseHandler.generateResult("Parola Hatalı",HttpStatus.CONFLICT.value()));
			}
			
			} catch (Exception e) {
				return ResponseEntity
			            .status(HttpStatus.UNAUTHORIZED)
			            .body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
		}
		
		
		
	}
	
	 @PutMapping("/{id}")
	  ResponseEntity<Object> updateUser(@RequestBody User user,
			   @PathVariable int id) {
		 
		  user=new User(id,user.getName(),user.getEmail(),user.getPassword(),new Date());
		 
	    try {
	    	userService.updateUser(user);
			return  ResponseEntity
		            .status(HttpStatus.OK)
		            .body(responseHandler.generateResult("Kullanıcı Başarıyla Güncellendi",HttpStatus.OK.value()));
			
		} catch (Exception e) {
			return  ResponseEntity
		            .status(HttpStatus.CONFLICT)
		            .body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
		}
	  }
	

}
