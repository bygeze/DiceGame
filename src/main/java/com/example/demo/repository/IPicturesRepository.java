package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Picture;
import com.example.demo.models.Shop;

public interface IPicturesRepository extends CrudRepository<Picture, Long> {
	public Integer deleteByShop(Shop shop);
	public List<Picture> findByShop(Shop shop);
}
