package com.bionic.edu.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Customer findById(int id) {
		Customer customer = em.find(Customer.class, id);
		if (customer != null) {
			return customer;
		}
		return null;
	}

}
