package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.ShopDto;
import com.example.demo.service.IShopService;

@RestController
@RequestMapping("/shops")
public class ShopController {
	 private final IShopService shopService;
	 
	 @Autowired
	 public ShopController(IShopService shopService) {
		 this.shopService = shopService;
	 }
	
	/* FIND ALL SHOPS */
	@GetMapping
	public ResponseEntity<List<ResponseDto>> findAll() {
		return new ResponseEntity<>(shopService.findAll(), HttpStatus.OK);
	}
	
	/* CREATE SHOP */
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody ShopDto dto) {
    	/* podria validar mas con regex
    	 * por ejemplo, si pongo un nombre que sea un numero solo el programa lo guarda igual
    	 */
    	if(dto.name != null && (dto.capacitat > 0 && dto.capacitat < 20)) {
    		return new ResponseEntity<>(shopService.create(dto), HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(new MessageDto("Los datos introducidos no son correctos"), HttpStatus.BAD_REQUEST);
    	}
    }
	
    /*
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable("id") Long id) {
    	
    	return new ResponseEntity<>(shopService.findById(id), HttpStatus.OK);
    }
    */
}
