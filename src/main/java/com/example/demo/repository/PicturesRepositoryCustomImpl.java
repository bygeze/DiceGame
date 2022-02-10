/*package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Picture;

@Repository
@Transactional(readOnly = true)
public class PicturesRepositoryCustomImpl implements IPicturesRepositoryCustom {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Picture> findAllPicsByShopId(Long shopId) {
		// TODO Auto-generated method stub
		TypedQuery<Picture> query = em.createQuery(
				"SELECT * FROM Picture WHERE shop_id = ?1", Picture.class);
		
		List<Picture> pics = query.setParameter(1, shopId).getResultList();
		
		return pics;
	}

	@Override
	public int deleteAllPicturesByShopId(Long shopId) {
		// TODO Auto-generated method stub
		TypedQuery<Picture> query = em.createQuery(
				"DELETE * FROM Picture WHERE shop_id = ?1", Picture.class);
		
		return query.setParameter(1, shopId).executeUpdate();
	}
}

*/
