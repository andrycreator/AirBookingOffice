package com.bionic.edu.dao;

import java.time.LocalDate;
import java.util.List;

import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Ordering.StatusOrder;

public interface OrderingDAO {
	
	//пошук замовлення по заданому id
	public Ordering findById (int id);
	
	//пошук замовлень за період з вказанням статусу
	public List<Ordering> findByPeriod(LocalDate from, LocalDate to, StatusOrder statusOrder);
	
	//пошук замовлень до вказаної дати з вказанням статусу
	public List<Ordering> findToDate(LocalDate to, StatusOrder statusOrder);

	//видалення замовлення
	public void delete(Ordering ordering);
	
	//добавити нове замовлення (бронювання квитків)
	public void saveOrdering(Ordering ordering);
	
	//вивести всі замовлення з статусом booked
	public List<Ordering> showBookedOrdering();
}
