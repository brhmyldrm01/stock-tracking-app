package com.StokTakip.Service;

import java.util.List;
import java.util.Optional;

import com.StokTakip.Exception.Exceptions;
import com.StokTakip.Model.User;



public interface UserService {
	public void createUser(User user) throws Exceptions;
	public Optional<User> getUserById(int id) throws Exceptions;
	public void updateUser(User User) throws Exceptions;
	public void deleteUser(int id) throws Exceptions;
	public List<User> getAllUsers();
	public long getUserCount();
	public boolean isExists(int id);
	public User findByEmail(String email) throws Exceptions;
	public int isExists(String email);
}
