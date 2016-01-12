package com.bionic.edu.service;

import java.time.LocalDate;
import java.util.List;

import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.Ticket;

public interface CustomerService {

	//вибір рейсу за датою відправки та місцем призначення(id=10)
	public List<Flight> findFlight(LocalDate dateDeparture, String to);
	
	//виведення переліку рейсів з бази за період
	public List<Flight> getFlightByPeriod(LocalDate start, LocalDate end);
	
	//добавити нове замовлення (бронювання квитків)(id=11)
	public String addOrdering(List<Ticket> tickets);
	
	
	//test
		//знайти рейс по заданому id
		public Flight findById (int id);
	
	
}
