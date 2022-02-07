package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/* 
 * an entity is nothing but POJO's representing data that can be persisted
 * to the database. it represents a table stored in a database.
 * every instance of an entity represents a row in a table.
 * 
 * an entity must have:
 * - no-arg constructor
 * - a primary key
 * 
 * the entity name defaults to the class. we can change it using the name element
 *   @Entity(name="name")
 *   
 * in cases (most) when the table and the entity name are not the same we use
 * @Table(name="name") to designate the name of the table.
 * default value is the name of the entity
 * 
 *  @Column
 *  we use it to mention the details of a column in the table.
 *  has many elements like: name, length, nullable, unique
 *
 */
@Entity
public class Shop {
	/* 
	 * each entity must have a primary key which uniquely identifies it. 
	 * @Id defines the primary key.
	 * 
	 * we can generate the identifiers in different ways which are specified by
	 * @GeneratedValue
	 * there are four id generation strategies that we can choose with the strategy element
	 * GenerationType.{AUTO, TABLE, SEQUENCE, IDENTITY}
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false)
	Long id;
	
	@Column(nullable = false)
	String name;
	
	@Column(nullable = false)
	int capacitat;
	
	@OneToMany(mappedBy = "shop")
	private List<Picture> pictures;
	
	public Shop(String name, int capacitat) {
		this.name = name;
		this.capacitat = capacitat;
	}
	
	public Shop(long id, String name, int capacitat) {
		this.id = id;
		this.name = name;
		this.capacitat = capacitat;
	}
	
	 /* must have for jpa entity */
	public Shop() {
		
	}
	
	/* getters and setters */
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCapacitat() {
		return capacitat;
	}
	
	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}
	
	
}
