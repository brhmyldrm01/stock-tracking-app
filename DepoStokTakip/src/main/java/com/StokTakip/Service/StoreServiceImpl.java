package com.StokTakip.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.StokTakip.Model.Store;
import com.StokTakip.Repository.StoreDataRepository;
@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDataRepository storeDataRepository;


	@Override
	public void createStore(Store store) {
		storeDataRepository.save(store);

	}

	@Override
	public Optional<Store> getStoreById(int id) {

		return storeDataRepository.findById(id);
	}

	@Override
	public void updateStore(Store store) {
		storeDataRepository.save(store);

	}

	@Override
	public void deleteStore(int id) {
		storeDataRepository.deleteById(id);

	}

	@Override
	public List<Store> getAllStores() {
		List <Store> list =storeDataRepository.findAll();
		return list;
	}

	@Override
	public long getStoreCount() {

		return storeDataRepository.count();
	}

	@Override
	public boolean isExists(int id) {

		return storeDataRepository.existsById(id);
	}

}