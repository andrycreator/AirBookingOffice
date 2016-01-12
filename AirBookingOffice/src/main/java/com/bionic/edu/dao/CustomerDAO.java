package com.bionic.edu.dao;

import com.bionic.edu.entity.Customer;

public interface CustomerDAO {
	
	//пошук покупця за вказаним id
	public Customer findById(int id);
}
