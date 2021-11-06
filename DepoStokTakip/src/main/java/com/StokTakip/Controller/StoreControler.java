package com.StokTakip.Controller;


import com.StokTakip.Model.Store;
import com.StokTakip.Service.StoreServiceImpl;
import com.StokTakip.Service.UserServiceImpl;
import com.StokTakip.Util.BasicAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("stores")
public class StoreControler {

    @Autowired
    StoreServiceImpl storeService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/{id}")
    ResponseEntity<Object> getStore(@PathVariable int id,HttpServletRequest req){
        HashMap<String, Object> ExeptionMap = new HashMap<String, Object>();
        try {
        	 if(BasicAuthentication.auth(req)) {
            if (storeService.isExists(id)) {
                Optional<Store> store = storeService.getStoreById(id);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(store);
            }
            else{
                ExeptionMap.put("code",HttpStatus.NOT_FOUND.value());
                ExeptionMap.put("message","DEPO BULUNAMADI");

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ExeptionMap);
            }
            }
        	 else {
        		 return ResponseEntity
        		            .status(HttpStatus.UNAUTHORIZED)
        		            .body(("Giriş Yapınız"));
        	 }

        }
        catch (Exception e) {
            ExeptionMap.put("eror",e.getMessage());
            ExeptionMap.put("code",HttpStatus.BAD_REQUEST.value());
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ExeptionMap);
        }
    }
    
    
    
    @PostMapping("/")
    ResponseEntity<Object> add(@RequestBody Store store,HttpServletRequest req) {
        HashMap<String, Object> ExeptionMap = new HashMap<String, Object>();
       
        try {
        	 if(BasicAuthentication.auth(req)) {
        		 if(userService.isExists(store.getUser_id())){
                     storeService.createStore(store);
                     ExeptionMap.put("code",HttpStatus.CREATED.value());
                     ExeptionMap.put("success","KAYIT BAŞARILI OLDU");

                     return  ResponseEntity
                             .status(HttpStatus.CREATED)
                             .body(ExeptionMap);}
                 else{
                     ExeptionMap.put("code",HttpStatus.CONFLICT.value());
                     ExeptionMap.put("message",store.getUser_id()+" İDYE AİT KULLANICI BULUNAMADI!");
                     ExeptionMap.put("eror","KAYIT YAPILAMADI");

                     return  ResponseEntity
                             .status(HttpStatus.CONFLICT)
                             .body(ExeptionMap);
                 }
        }
        	 else
        		 return ResponseEntity
        		            .status(HttpStatus.UNAUTHORIZED)
        		            .body(("Giriş Yapınız"));
        	 }
        catch (Exception e) {
            ExeptionMap.put("code",HttpStatus.BAD_REQUEST.value());
            ExeptionMap.put("message",e.getMessage());
            ExeptionMap.put("eror","KAYIT YAPILAMADI");

            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ExeptionMap);
        }
    }
    
    
    @PutMapping("/{id}")
    ResponseEntity<Object> update(@RequestBody Store store,HttpServletRequest req){

        HashMap<String, Object> ExeptionMap = new HashMap<String, Object>();
        try {
        	 if(BasicAuthentication.auth(req)) {
        		 if (storeService.isExists(store.getId())){
                     storeService.updateStore(store);
                     ExeptionMap.put("success","GÜNCELLEME BAŞARILI OLDU");
                     ExeptionMap.put("code",HttpStatus.CREATED.value());
                     return  ResponseEntity
                             .status(HttpStatus.CREATED)
                             .body(ExeptionMap);}
                 else{
                     ExeptionMap.put("code",HttpStatus.CONFLICT.value());
                     ExeptionMap.put("message",store.getId()+" İDYE AİT DEPO BULUNAMADI!");
                     ExeptionMap.put("eror","GÜNCELLEME YAPILAMADI");

                     return  ResponseEntity
                             .status(HttpStatus.CONFLICT)
                             .body(ExeptionMap);
                 }
        }
        	 else
        		 return ResponseEntity
        		            .status(HttpStatus.UNAUTHORIZED)
        		            .body(("Giriş Yapınız"));
        	 }
        catch (Exception e) {
            ExeptionMap.put("code",HttpStatus.BAD_REQUEST.value());
            ExeptionMap.put("message",e.getMessage());
            ExeptionMap.put("eror","GÜNCELLEME YAPILAMADI");


            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ExeptionMap);
        }
    }
    
    
    @DeleteMapping("/{id}")
    ResponseEntity<Object> delete(@RequestBody Store store,HttpServletRequest req){

        HashMap<String, Object> ExeptionMap = new HashMap<String, Object>();
        try {
        	 if(BasicAuthentication.auth(req)) {
            if(storeService.isExists(store.getId())){
                if(store.isDeletable()){
                    storeService.deleteStore(store.getId());
                    ExeptionMap.put("success","SİLME İŞLEMİ BAŞARILI OLDU");
                    ExeptionMap.put("code",HttpStatus.CREATED.value());
                    return  ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(ExeptionMap);}
                else {
                    ExeptionMap.put("code", HttpStatus.CONFLICT.value());
                    ExeptionMap.put("message", store.getId() + " İDYE AİT DEPO SİLİNEMEZ!");
                    ExeptionMap.put("eror", "SİLME İŞLEMİ YAPILAMADI");

                    return ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .body(ExeptionMap);
                }
            }
            else{
                ExeptionMap.put("code",HttpStatus.CONFLICT.value());
                ExeptionMap.put("message",store.getId()+" İDYE AİT DEPO BULUNAMADI!");
                ExeptionMap.put("eror","SİLME İŞLEMİ YAPILAMADI");

                return  ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(ExeptionMap);
            }
            }
        	 else {
        		 return ResponseEntity
        		            .status(HttpStatus.UNAUTHORIZED)
        		            .body(("Giriş Yapınız"));
        	 }

        }
        catch (Exception e) {
            ExeptionMap.put("code",HttpStatus.BAD_REQUEST.value());
            ExeptionMap.put("message",e.getMessage());
            ExeptionMap.put("eror","SİLME İŞLEMİ YAPILAMADI");


            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ExeptionMap);
        }
    }
    @GetMapping("/count")
    public long count(HttpServletRequest req) throws ServletException, IOException{
    	 if(BasicAuthentication.auth(req))
        return storeService.getStoreCount();
    	 else
    		 return 0;
    }
}
