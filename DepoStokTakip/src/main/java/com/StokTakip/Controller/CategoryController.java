package com.StokTakip.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StokTakip.DTO.CategoryCreateDTO;
import com.StokTakip.Exception.Exceptions;
import com.StokTakip.Model.Category;
import com.StokTakip.Model.User;
import com.StokTakip.Service.CategoryService;
import com.StokTakip.Service.UserService;
import com.StokTakip.Util.BasicAuthentication;
import com.StokTakip.Util.ResponseHandler;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categories")
public class CategoryController {
   @Autowired
    CategoryService categoryService;
  
 
	ResponseHandler responseHandler=new ResponseHandler();

   
   @GetMapping("/all")
   public Object kisilistesi(HttpServletRequest req) throws IOException, ServletException
   {
	   if(BasicAuthentication.auth(req)) {
       return categoryService.getAllCategories();
       }
	   else
		   return ResponseEntity
		            .status(HttpStatus.UNAUTHORIZED)
		            .body(("Giriş Yapınız"));
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<Object> get(@PathVariable int id,HttpServletRequest req) throws ServletException, IOException, Exceptions
   {
       try
       {
    	   if(BasicAuthentication.auth(req)) {
           Optional<Category> category=categoryService.getCategoryById(id);
           return  ResponseEntity.status(HttpStatus.OK).body(category);
           }
    	   else
    		   return ResponseEntity
	            .status(HttpStatus.UNAUTHORIZED)
	            .body(("Giriş Yapınız"));
       }
       catch(NoSuchElementException ex)
       {
    	   return ResponseEntity
		            .status(HttpStatus.CONFLICT)
		            .body(("Kategori Bulunamadı"));
       }
       
   }
  
   @PostMapping("/add")
   public ResponseEntity<Object> addCategory(@RequestBody CategoryCreateDTO categoryDTO,HttpServletRequest req) throws ServletException, IOException
   {	try {
	   Category category=new Category(categoryDTO.getUser_id(),categoryDTO.getName(),categoryDTO.getDescription(),true,new Date());
	   		if(BasicAuthentication.auth(req)) {
	   			categoryService.createCategory(category);
	   			return ResponseEntity
				.status(HttpStatus.OK)
				.body(responseHandler.generateResult("Kayıt Başarılı",HttpStatus.OK.value()));
	   		}
	   		else {
	   			return ResponseEntity
	   		            .status(HttpStatus.UNAUTHORIZED)
	   		            .body(("Giriş Yapınız"));
	   		}
	   }catch(Exception e){
		   return  ResponseEntity
		            .status(HttpStatus.CONFLICT)
		            .body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
   }
   }
   
   @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category,HttpServletRequest req,@PathVariable int id) throws ServletException, IOException, Exceptions
    {
        try
        {	
        	Category categ=new Category(id,category.getName(),category.getDescription(),category.isDeletable(),category.getCreated_at());
        	
        	
        	if(BasicAuthentication.auth(req)) {
            categoryService.updateCategory(categ);
            return ResponseEntity
    				.status(HttpStatus.OK)
    				.body(responseHandler.generateResult("Güncelleme Başarılı",HttpStatus.OK.value()));
            }
        else {
        	return ResponseEntity
    	            .status(HttpStatus.UNAUTHORIZED)
    	            .body(("Giriş Yapınız"));
        	}
        }
        catch(NoSuchElementException e)
        {
        	 return  ResponseEntity
 		            .status(HttpStatus.CONFLICT)
 		            .body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
        }
    }
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id,HttpServletRequest req) throws ServletException, IOException
    {	 
    	try {
    		if(BasicAuthentication.auth(req)) {
    	        categoryService.deleteCategory(id);
    	        return ResponseEntity
        				.status(HttpStatus.OK)
        				.body(responseHandler.generateResult("Silme Başarılı",HttpStatus.OK.value()));
    	}
    		else {
    			return ResponseEntity
        	            .status(HttpStatus.UNAUTHORIZED)
        	            .body(("Giriş Yapınız"));
            	}
    		}catch(Exception e) {
    			return  ResponseEntity
     		            .status(HttpStatus.CONFLICT)
     		            .body(responseHandler.generateResult(e.getMessage(),HttpStatus.CONFLICT.value()));
    		}
    	
     }
    
    
    @RequestMapping(value="/counts/{id}",method=RequestMethod.GET)
     public long count (@PathVariable int id,HttpServletRequest req) throws ServletException, IOException
    {	 if(BasicAuthentication.auth(req)) {
        long counts=categoryService.getCategoryCount();
        return counts;
        }
    	return 0;
    }    


}
