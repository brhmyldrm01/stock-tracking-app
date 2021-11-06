package com.StokTakip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StokTakip.Model.Item;

public interface ItemDataRepository extends JpaRepository<Item, Integer>{

}
