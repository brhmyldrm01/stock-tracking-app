package com.StokTakip.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StokTakip.Exception.Exceptions;
import com.StokTakip.Model.User;
import com.StokTakip.Repository.UserDataRepository;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDataRepository userDataRepository;

	
	@Override
	public void createUser(User user) throws Exceptions {
		if(this.isExists(user.getEmail())>0) {
			throw new Exceptions(" Kullanıcı Mevcut");
		}else
		{
			userDataRepository.save(user);
			
		}
	}

	@Override
	public Optional<User> getUserById(int id) throws Exceptions {
		if(this.isExists(id))
		return userDataRepository.findById(id);
		else
			throw new Exceptions("Kullanıcı Bulunamadı");
	}

	@Override
	public void updateUser(User user) throws Exceptions {
		if(this.isExists(user.getId())) {
			userDataRepository.save(user);
		}else
		{
			throw new Exceptions("Güncellenecek Kullanıcı Bulunamadı");
		}
		
				
				
		
	}

	@Override
	public void deleteUser(int id) throws Exceptions {
		if(this.isExists(id)) {
			userDataRepository.deleteById(id);	
		}else {
			throw new Exceptions("Silinecek Kullanıcı Bulunamadı");
		}
			
		
		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list =userDataRepository.findAll();
		return list;
	}

	@Override
	public long getUserCount() {
		
		return userDataRepository.count();
	}

	@Override
	public boolean isExists(int id) {
		
		return userDataRepository.existsById(id);
	}

	@Override
	public User findByEmail(String email) throws Exceptions {
		if(isExists(email)>0) {
			return userDataRepository.findByEmail(email);
		}
		else {
			throw new Exceptions("Girdiğiniz mail ile kullanıcı bulunamadı.");
		}
		
	}

	@Override
	public int isExists(String email) {
		return userDataRepository.isExists(email);
	}

}
