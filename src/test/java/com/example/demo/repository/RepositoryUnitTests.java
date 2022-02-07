package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.models.Picture;
import com.example.demo.models.Shop;

/* if using JUnit4
@
*/

/* 
 * @DataJpaTest it's an Spring Annotation that focuses only in JPA components.
 * This annotation disables full auto-configuration and applys only the configuration
 * relevant to JPA testing.
 * This tests are TRANSACTIONAL and ROLL BACK at the end of each test.
 * 
 * By default it scans for @Entity and @Repository classes
 * 
 * It must be used in combination with:
 * @RunWith(SpringRunner.class) -> if using JUnit 4
 * 
 * if using JUnit 5 this annotation:
 * @ExtendWith(SpringExtension.class)
 * its already contained in the @DataJpaTest  
 * 
 * https://howtodoinjava.com/spring-boot2/testing/datajpatest-annotation/
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryUnitTests {
	// using autowired in tests was a bad practice, have to find the resource
	// https://reflectoring.io/unit-testing-spring-boot/
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	IShopRepository shopRepository;
	
	@Autowired
	IPicturesRepository picRepository;
	
	@BeforeEach
	void init() {
		
	}
	
	/* cas: llistar botigues */
	@Test
	public void should_find_no_shops_if_repository_is_empty() {
		Iterable<Shop> shops = shopRepository.findAll();
		
		assertThat(shops).isEmpty();
	}
	
	/* cas: llistar botigas */
	@Test
	public void should_find_all_shops() {
		Shop shop1 = new Shop("Shop 1", 3);
		entityManager.persist(shop1);
		
		Shop shop2 = new Shop("Shop 2", 6);
		entityManager.persist(shop2);
		
		Iterable<Shop> shops = shopRepository.findAll();
		
		assertThat(shops).hasSize(2);
	}
	
	/* cas: crear botiga */
	@Test
	public void should_save_shop() {
		Shop shop = new Shop("Shop 1", 3);
		shop = shopRepository.save(shop);
		
		assertThat(shop.getId()).isNotNull();
		assertThat(shop).hasFieldOrPropertyWithValue("name", "Shop 1");
		assertThat(shop).hasFieldOrPropertyWithValue("capacitat", 3);
	}
	
	/* cas: no te cas especific, pero es necesari per altres accions */
	@Test
	public void should_find_shop_by_id() {
		Shop shop1 = new Shop("Shop 1", 3);
		entityManager.persist(shop1);
		
		Optional<Shop> shop = shopRepository.findById(shop1.getId());
		
		assertThat(shop1.equals(shop.get()));
		
	}
	
	/* cas: afegir quadres 	*/
	@Test
	public void should_save_picture() {
		Shop shop1 = new Shop("Shop 1", 3);		
		entityManager.persist(shop1);
		
		Optional<Shop> shop = shopRepository.findById((long) shop1.getId());
		
		Picture pic1 = new Picture("La obra", "Gustavo", 1200.0, new Date(), shop.get());
		
		pic1 = picRepository.save(pic1);
		
		assertThat(pic1.getId()).isNotNull();
		assertThat(pic1).hasFieldOrPropertyWithValue("author", "Gustavo");
		assertThat(pic1).hasFieldOrPropertyWithValue("title", "La obra");
		assertThat(pic1).hasFieldOrPropertyWithValue("price", 1200.0);
		
	}
	
	/* cas: llistar quadres */
	@Test
	public void should_find_no_pictures_if_repository_is_empty() {
		Iterable<Picture> pictures = picRepository.findAll();
		
		assertThat(pictures).isEmpty();
	}
	
	
	@Test
	public void should_find_all_pictures_of_existing_shop() {
		Shop shop1 = new Shop("Shop 1", 3);		
		entityManager.persist(shop1);
		
		Optional<Shop> shop = shopRepository.findById((long) shop1.getId());
		
		Picture pic1 = new Picture("La obra", "Gustavo", 1200.0, new Date(), shop.get());
		
		entityManager.persist(pic1);
		
		List<Picture> pics = picRepository.findAllPicsByShopId(shop.get().getId());
		
		assertThat(pics).hasSize(1);
	}
	
	/* cas: incendiar tots els quadres */
	@Test
	public void should_delete_all_pics_of_existing_shop() {
		Shop shop1 = new Shop("Shop 1", 3);
		entityManager.persist(shop1);
		
		Optional<Shop> shop = shopRepository.findById((long) shop1.getId());
		
		Picture pic1 = new Picture("La obra", "Gustavo", 1200.0, new Date(), shop.get());
		entityManager.persist(pic1);
		
		Picture pic2 = new Picture("La obra", "Gustavo", 1200.0, new Date(), shop.get());
		entityManager.persist(pic2);
		
		List<Picture> pics = picRepository.findAllPicsByShopId(shop.get().getId());
		
		assertThat(pics).hasSize(2);
		
		int qDeletedElements = picRepository.deleteAllPicturesByShopId(shop.get().getId());
		
		assertThat(qDeletedElements).isEqualTo(2);
		
		pics = picRepository.findAllPicsByShopId(shop.get().getId());
		
		assertThat(pics).isEmpty();
			
	}		
}

