package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PictureDto;
import com.example.demo.dto.PictureReqDto;
import com.example.demo.exceptions.ShopNotFoundException;

public interface IPicturesService {
	public PictureDto create(PictureReqDto data, Long shopId) throws ShopNotFoundException;
	public List<PictureDto> findAll(Long shopId) throws ShopNotFoundException;
	@Transactional
	public int burn(Long shopId) throws ShopNotFoundException;
}
