package com.StokTakip.Util;

import java.util.HashMap;
import java.util.Map;


import com.StokTakip.Model.Category;
import com.StokTakip.Model.Store;

import com.StokTakip.Model.User;

public class ResponseHandler {

	
	public Map<String, Object> generateUserResponse(User user) {
		
		Map<String,Object> temp = new HashMap<String,Object>();
		Map<String,Object> mapResponse = new HashMap<String,Object>();
		
		Map<String,Object> mapUser = new HashMap<String,Object>();
		Map<String,Object> mapCategories = new HashMap<String,Object>();
		Map<String,Object> mapStores = new HashMap<String,Object>();
		
		mapUser.put("id", user.getId());
		mapUser.put("name", user.getName());
		mapUser.put("email", user.getEmail());
		mapUser.put("password", user.getPassword());
		mapUser.put("created_at", user.getCreated_at());
		mapUser.put("updated_at", user.getUpdated_at());
		
		
		for(int i=0;i<user.getCategories().size();i++) {
			temp=generateCategoryResponse(user.getCategories().get(i));
			temp.remove("user id");
			if(i==0)
			mapCategories.put("default category "+i,temp);
			else
				mapCategories.put("category "+i,temp);
	}
		for(int i=0;i<user.getStores().size();i++) {
			temp=generateStoreResponse(user.getStores().get(i));
			temp.remove("user id");
			if(i==0)
			mapStores.put("default store "+i,temp);
			else
				mapStores.put("store "+i,temp);
		}
		
		
		mapResponse.put("categories", mapCategories);
		mapResponse.put("stores", mapStores);
		mapResponse.put("info", mapUser);
	
		
		return mapResponse;
	}
	
	
	public Map<String,Object> generateResult(String message,int code) {
		
		Map<String,Object> mapResult = new HashMap<String,Object>();
		
		mapResult.put("code", code);
		mapResult.put("message",message);
		
		
		return mapResult;
	}

	
	public Map<String, Object> generateStoreResponse(Store store) {
		Map<String,Object> mapResponse = new HashMap<String,Object>();
		mapResponse.put("user id", store.getUser_id());
		mapResponse.put("id", store.getId());
		mapResponse.put("name", store.getName());
		mapResponse.put("address",store.getAddress());
		mapResponse.put("description", store.getDescription());
		mapResponse.put("deletable", store.isDeletable());
		mapResponse.put("created_at", store.getCreated_at());
		return mapResponse;
	}
	
	
	
	public Map<String, Object> generateCategoryResponse(Category category) {
		Map<String,Object> mapResponse = new HashMap<String,Object>();
		mapResponse.put("user id", category.getUser_id());
		mapResponse.put("id", category.getId());
		mapResponse.put("name", category.getName());
		mapResponse.put("description", category.getDescription());
		mapResponse.put("deletable", category.isDeletable());
		mapResponse.put("created_at", category.getCreated_at());
		return mapResponse;
	}
	

}
