package com.example.demo.map;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.ShopDto;
import com.example.demo.models.Shop;

public final class ShopMapper {
	
	public static final Shop shopToEntity(ShopDto dto) {
		Shop temp = new Shop();
		
		temp.setId(dto.id);
		temp.setName(dto.name);
		temp.setCapacitat(dto.capacitat);
		
		return temp;
	}
	
	public static final ShopDto shopToDto(Shop shop) {
		ShopDto temp = new ShopDto();
		
		temp.id = shop.getId();
		temp.name = shop.getName();
		temp.capacitat = shop.getCapacitat();
		
		return temp;
	}
	
	public static final List<ShopDto> shopToDto(Iterable<Shop> shopList) {
		List<ShopDto> list = new ArrayList<ShopDto>();
		
		for(Shop elem : shopList) {
			ShopDto dto = new ShopDto();
			
			dto.id = elem.getId();
			dto.name = elem.getName();
			dto.capacitat = elem.getCapacitat();
			
			list.add(dto);
		}
 		
		return list;
	}
	
	private ShopMapper() {};
}
