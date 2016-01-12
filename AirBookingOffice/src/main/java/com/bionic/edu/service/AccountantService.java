package com.bionic.edu.service;

import java.util.List;

import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Staff;

public interface AccountantService {
	
	//пошук замовлення по заданому id
	public Ordering findById (int id);
	
	// авторизація в системі(id=15)
	public Staff getAuthorization(String login, String password);
	
	//вивести всі замовлення з статусом booked(id=16)
	public List<Ordering> showBookedOrdering();
	
	//зміна статусу замовлення, яке оплачене(id=17)
	public void changeStatusOrder(int id);
}
