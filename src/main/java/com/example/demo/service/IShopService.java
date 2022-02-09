package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ShopDto;

public interface IShopService {
	public ResponseDto create(ShopDto data);
	public List<ResponseDto> findAll();
	public ShopDto findById(Long id);
}
