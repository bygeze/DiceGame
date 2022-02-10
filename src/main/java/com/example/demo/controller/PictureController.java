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
import com.example.demo.exceptions.ShopIsFullException;
import com.example.demo.exceptions.ShopNotFoundException;
import com.example.demo.service.IPicturesService;

@RestController
@RequestMapping("/shops")
public class PictureController {
	private final IPicturesService picService;
	
	/* inyectar las dependencias por el constructor es mejor para TDD.
	 * recordatorio: mirar lombok.
	 */
	
	@Autowired
	public PictureController(IPicturesService picService) {
		this.picService = picService;
	}
	
    /* CREATE PICTURE IN SHOP */
    @PostMapping("/{id}/pictures")
    public ResponseEntity<ResponseDto> create(@PathVariable("id") Long shopId, @RequestBody PictureReqDto data) {
    	if(data.title == null || data.price == null) {
    		return new ResponseEntity<>(
    				new MessageDto("La data que has introduit no es correcta"), 
    				HttpStatus.BAD_REQUEST);
    	}
    	
    	try {
			return new ResponseEntity<>(picService.create(data, shopId), HttpStatus.OK);
		} catch (ShopNotFoundException e1) {
			return new ResponseEntity<>(new MessageDto(e1.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (ShopIsFullException e2) {
			return new ResponseEntity<>(new MessageDto(e2.getMessage()), HttpStatus.BAD_REQUEST);
		}
    }
    
    /* FIND ALL PICTURES FROM SHOP */
    @GetMapping("/{id}/pictures")
    public ResponseEntity<List<ResponseDto>> findAll(@PathVariable("id") Long shopId) {
    	List<ResponseDto> list = new ArrayList<>();
    	
    	try {
			list = picService.findAllByShop(shopId);
		} catch (ShopNotFoundException e) {
			// TODO Auto-generated catch block
			list.add(new MessageDto(e.getMessage()));
			return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(list);
    	
		/*
		try {
			list = picService.findAllByShop(shopId);
			return ResponseEntity.ok(list);
		} catch (ShopNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}*/
    }
    
    /* BURN ALL PICTURES FROM SHOP */
	@DeleteMapping("/{id}/pictures")
	public ResponseEntity<MessageDto> burn(@PathVariable("id") Long shopId) {
		try {
			Integer num = picService.burn(shopId);
			return new ResponseEntity<>(new MessageDto(num.toString()), HttpStatus.OK);
		} catch (ShopNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
}