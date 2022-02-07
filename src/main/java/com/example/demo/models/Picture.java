package com.example.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Picture {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private Double price;
	
	@Column
	private Date date;
	
	/* relationship Many to One 
	 * analogy library (one end) and books (many end)
	 * 
	 * in this case pictures would be like books,
	 * so pictures is our many end.
	 * so we need @ManyToOne here,
	 * and the link with @OneToMany in shop class
	 * 
	 * */
	@ManyToOne
	@JoinColumn(name="shop_id")
	private Shop shop;
	
	public Picture(String title, String author, Double price, Date date, Shop shop) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.date = date;
		this.shop = shop;
	}

	public Picture() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
