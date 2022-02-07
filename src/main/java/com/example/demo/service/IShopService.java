package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ShopDto;

public interface IShopService {
	public ResponseDto create(ShopDto data);
	public List<ShopDto> findAll();
	public ShopDto findById(Long id);
}
