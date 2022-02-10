package com.example.demo.map;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.PictureDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ShopDto;
import com.example.demo.models.Picture;
import com.example.demo.models.Shop;

public final class PictureMapper {
	public static final Picture dtoToEntity(PictureDto pic, Shop shop) {
		Picture temp = new Picture();
		temp.setId(pic.id);
		temp.setAuthor(pic.author);
		temp.setTitle(pic.title);
		temp.setPrice(pic.price);
		temp.setShop(shop);
		temp.setDate(null);
		
		return temp;
	}
	
	public static final PictureDto entityToDto(Picture pic) {
		PictureDto temp = new PictureDto();
		temp.title = pic.getTitle();
		temp.author = pic.getAuthor();
		temp.price = pic.getPrice();
		temp.id = pic.getId();
		temp.shop_id = pic.getShop().getId();
		
		return temp;
		
	}
	
	public static final List<ResponseDto> entityToDto(List<Picture> pictureList) {
		List<ResponseDto> list = new ArrayList<>();
		
		for(Picture elem : pictureList) {
			PictureDto dto = new PictureDto();
			
			dto.id = elem.getId();
			dto.title = elem.getTitle();
			dto.author = elem.getAuthor();
			dto.price = elem.getPrice();
			dto.shop_id = elem.getShop().getId();
			dto.date = elem.getDate();
			
			list.add(dto);
		}
 		
		return list;
	}
}

