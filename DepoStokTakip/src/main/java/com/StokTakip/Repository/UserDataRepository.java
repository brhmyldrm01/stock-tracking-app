package com.StokTakip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StokTakip.Model.User;

public interface UserDataRepository extends JpaRepository<User,Integer>{
	User findByEmail(String email);
	int isExists(String email);
}
