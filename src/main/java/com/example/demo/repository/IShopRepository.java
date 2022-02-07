package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Shop;

public interface IShopRepository extends CrudRepository<Shop, Long> {
	
}
