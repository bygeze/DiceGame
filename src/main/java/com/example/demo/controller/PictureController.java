package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PictureDto;
import com.example.demo.dto.PictureReqDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.exceptions.ShopNotFoundException;
import com.example.demo.map.PictureMapper;
import com.example.demo.repository.IPicturesRepository;
import com.example.demo.service.IPicturesService;
import com.example.demo.service.IShopService;

@RestController
@RequestMapping("/shops")
public class PictureController {
	private final IPicturesService picService;
	private final IShopService shopService;
	
	@Autowired
	public PictureController(IPicturesService picService, IShopService shopService) {
		this.picService = picService;
		this.shopService = shopService;
	}
	
    /* create picture */
    @PostMapping("/{id}/pictures")
    public ResponseEntity<ResponseDto> create(@PathVariable("id") Long shopId, @RequestBody PictureReqDto data) {
    	if(data.title == null || data.author == null || data.price == null) {
    		return new ResponseEntity<>(new MessageDto("La data que has introduit no es correcta"), HttpStatus.BAD_REQUEST);
    	}
    	
    	try {
			return new ResponseEntity<>(picService.create(data, shopId), HttpStatus.OK);
		} catch (ShopNotFoundException e) {
			return new ResponseEntity<>(new MessageDto("La botiga no existeix"), HttpStatus.BAD_REQUEST);
		}
    }
    
    
    @GetMapping("/{id}/pictures")
    public ResponseEntity<List<PictureDto>> findAll(@PathVariable("id") Long shopId) {
    	List<PictureDto> list = new ArrayList<>();
		try {
			list = picService.findAll(shopId);
			return ResponseEntity.ok(list);
		} catch (ShopNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}
    }
    
	@DeleteMapping("/{id}/pictures")
	public ResponseEntity<MessageDto> burn(@PathVariable("id") Long shopId) {
		try {
			Integer num = picService.burn(shopId);
			return new ResponseEntity<>(new MessageDto(num.toString()), HttpStatus.OK);
		} catch (ShopNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new MessageDto("La botiga no existeix"), HttpStatus.BAD_REQUEST);
		}
		
	}
}