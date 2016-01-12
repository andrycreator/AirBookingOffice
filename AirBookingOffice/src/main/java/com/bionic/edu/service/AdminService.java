package com.bionic.edu.service;

import java.time.LocalDate;
import java.util.List;

import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Ordering.StatusOrder;

public interface AdminService {
	
	public Flight findById(int id); 
	
	// авторизація в системі(id=1)
	public Staff getAuthorization(String login, String password);

	//виведення переліку всіх рейсів в базі (id=2)
	public List<Flight> getAllFlights();
	
	//виведення переліку рейсів з бази за період (id=2)
	public List<Flight> getFlightByPeriod(LocalDate fromDate, LocalDate toDate);
	
	//добавити новий рейс(id=3)
	public void addFlight(Flight flight);
	
	//редагувати діючий рейс(id=4,7)
	public void editFlight(Flight flight);
	
	//видалити діючий рейс(id=5)
	public String deleteFlight(int id);
	
	//добавити квитки по заданому рейсу(id=6)
	public void addTicket(int id, int count);
		
	//видалення неоплачених замовлень протягом 3-х днів(id=8)
	public void deleteNotPayOrdering();
}
