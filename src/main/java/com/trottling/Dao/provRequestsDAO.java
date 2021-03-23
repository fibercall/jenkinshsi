package com.trottling.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.trottling.model.ProvRequests;

@Repository
@PropertySource("classpath:application.properties")
@Transactional
public class provRequestsDAO {

	
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	@Transactional
	public ProvRequests saveOrUpdate(ProvRequests provRequests) {
		try {
			provRequests = getEm().merge(provRequests);
		}
		catch(Exception ex) {
			throw ex;
		}
		return provRequests;
	}
}
