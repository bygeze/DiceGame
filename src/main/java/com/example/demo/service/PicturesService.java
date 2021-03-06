package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PictureDto;
import com.example.demo.dto.PictureReqDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.exceptions.ShopIsFullException;
import com.example.demo.exceptions.ShopNotFoundException;
import com.example.demo.map.PictureMapper;
import com.example.demo.models.Shop;
import com.example.demo.repository.IPicturesRepository;
import com.example.demo.repository.IShopRepository;

@Service
public class PicturesService implements IPicturesService {
	/* instead of autowiring i provide a constructor */
	private final IShopRepository shopRepo;
	private final IPicturesRepository picRepo;
	
	// field if im not using spring 5
	@Autowired
	public PicturesService(IShopRepository shopRepo, IPicturesRepository picRepo) {
		this.shopRepo = shopRepo;
		this.picRepo = picRepo;
	}
	
	public PictureDto create(PictureReqDto data, Long shopId) throws ShopNotFoundException, ShopIsFullException {
		Shop shop = findShop(shopId);
		
		int size = shop.getPictures().size();
		
		if(size >= shop.getCapacitat()) {
			throw new ShopIsFullException("Shop is full: " + size + "/" + size);
		}
		
		PictureDto temp = new PictureDto();
		temp.title = data.title;
		temp.author = data.author;
		temp.price = data.price;
		temp.date = new Date();
		
		return PictureMapper.entityToDto(
				picRepo.save(PictureMapper.dtoToEntity(temp, shop)));
	}
	
	public List<ResponseDto> findAll(Long shopId) throws ShopNotFoundException {
		Shop shop = findShop(shopId);
		
		List<ResponseDto> list = PictureMapper.entityToDto(picRepo.findByShop(shop));
		
		return (List<ResponseDto>) list;
	}
	
	@Override
	public int burn(Long shopId) throws ShopNotFoundException {
		Shop shop = findShop(shopId);
		
		return picRepo.deleteByShop(shop);
	}
	
	private Shop findShop(long shopId) throws ShopNotFoundException {
		Optional<Shop> shop = shopRepo.findById(shopId);
		
		if(shop.isEmpty()) {
			throw new ShopNotFoundException("Shop not found.");
		} else {
			return shop.get();
		}
	}

	@Override
	public List<ResponseDto> findAllByShop(Long shopId) throws ShopNotFoundException {
		Shop shop = findShop(shopId);
		
		List<ResponseDto> list = PictureMapper.entityToDto(picRepo.findByShop(shop));
		
		// TODO Auto-generated method stub
		return list;
	}
}
