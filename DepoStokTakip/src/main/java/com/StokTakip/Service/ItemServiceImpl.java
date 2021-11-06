package com.StokTakip.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StokTakip.Model.Item;
import com.StokTakip.Repository.ItemDataRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDataRepository itemDataRepository;
	
	
	@Override
	public void createItem(Item item) {
		itemDataRepository.save(item);
	}

	@Override
	public Item getItemById(int id) {
		
		return itemDataRepository.getOne(id);
	}

	@Override
	public void updateItem(Item item) {
	try
            {
            if(this.isExists(item.getId()))
            {
                itemDataRepository.save(item);
            }
            }
            catch(Exception exception)
            {
                System.out.println("Güncellendi");
            }	
		
	}

	@Override
	public void deleteItem(int id) {
		itemDataRepository.deleteById(id);
		
	}

	@Override
	public List<Item> getAllItems() {
		List <Item> list =itemDataRepository.findAll();
		return list;
	}

	@Override
	public long getItemCount() {
		
		return itemDataRepository.count();
	}

	@Override
	public boolean isExists(int id) {
		return itemDataRepository.existsById(id);
	}
	
}