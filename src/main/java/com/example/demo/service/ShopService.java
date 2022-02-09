package com.example.demo.service;

import java.util.List;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ShopDto;
import com.example.demo.map.ShopMapper;
import com.example.demo.repository.IShopRepository;

@Service
public class ShopService implements IShopService {
	private final IShopRepository shopRepo;
	
	@Autowired
	public ShopService(IShopRepository shopRepo) {
		this.shopRepo = shopRepo;
	}
	
	public ResponseDto create(ShopDto data) {
		return ShopMapper.shopToDto(shopRepo.save(ShopMapper.shopToEntity(data)));	
	}
	
	public List<ResponseDto> findAll() {
		return ShopMapper.shopToDto(shopRepo.findAll());
	}
	
	public ShopDto findById(Long id) {
		return ShopMapper.shopToDto(shopRepo.findById(id).get());
	}
}
