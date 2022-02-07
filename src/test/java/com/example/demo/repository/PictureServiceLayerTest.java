package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.PictureDto;
import com.example.demo.dto.PictureReqDto;
import com.example.demo.exceptions.ShopNotFoundException;
import com.example.demo.models.Picture;
import com.example.demo.models.Shop;
import com.example.demo.service.PicturesService;

@ExtendWith(MockitoExtension.class)
public class PictureServiceLayerTest {
	@Mock
	private IPicturesRepository picRepo;
	
	@Mock
	private IShopRepository shopRepo;
	
	PicturesService picService;
	
	@BeforeEach
	void initCase() {
		 picService = new PicturesService(shopRepo, picRepo);
	}
	
	@Test
	public void test() throws Exception {
		PictureReqDto req = new PictureReqDto();
		req.title = "Obra";
		req.author = "Gustavo";
		req.price = 1200.0;
		
		Optional<Shop> shop = Optional.of(new Shop(1,"Shop 1", 3));
		when(shopRepo.findById(shop.get().getId())).thenReturn(shop);
		
		when(picRepo.save(ArgumentMatchers.any(Picture.class))).then(AdditionalAnswers.returnsFirstArg());
		
		PictureDto picAss = picService.create(req, shop.get().getId());
		
		assertThat(picAss).hasFieldOrPropertyWithValue("title", "Obra");
		assertThat(picAss).hasFieldOrPropertyWithValue("author", "Gustavo");
		assertThat(picAss).hasFieldOrPropertyWithValue("shop_id", shop.get().getId());
		
	}
	
	@Test
	public void test2() throws ShopNotFoundException {
		Optional<Shop> shop = Optional.of(new Shop(1,"Shop 1", 3));
		when(shopRepo.findById(shop.get().getId())).thenReturn(shop);
		
		List<Picture> pics = new ArrayList<Picture>();
		
		pics.add(new Picture("Obra 1", "Gustavo", 1200.0, new Date(), shop.get()));
		pics.add(new Picture("Obra 2", "Gustavo", 1200.0, new Date(), shop.get()));
		
		when(picRepo.findAllPicsByShopId(shop.get().getId())).thenReturn(pics);
		
		List<PictureDto> picList = picService.findAll(shop.get().getId());
		
		assertThat(picList).hasSize(2);
		assertThat(picList.get(1)).hasFieldOrPropertyWithValue("title", "Obra 2");
	}
	
}
