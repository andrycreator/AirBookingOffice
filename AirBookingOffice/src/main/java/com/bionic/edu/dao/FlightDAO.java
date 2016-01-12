package com.bionic.edu.dao;

import java.time.LocalDate;
import java.util.List;

import com.bionic.edu.entity.Flight;

public interface FlightDAO {
	
	//знайти рейс по заданому id
	public Flight findById(int id);
	
	//виведення переліку всіх рейсів з бази
	public List<Flight> getAllFlights();
	
	//виведення переліку рейсів з бази за період
	public List<Flight> getFlightByPeriod(LocalDate start, LocalDate end);
	
	//редагувати діючий рейс
	public void saveFlight(Flight flight);
	
	//видалити рейс по якому немає проданих/заброньованих квитків
	public String deleteFlight(Flight flight);
	
	//добавити квитки по заданому рейсу
	public void addTicket(Flight flight, int count);
	
	//вибір рейсу за датою відправки та місцем призначення
	public List<Flight> findFlight(LocalDate dateDeparture, String to);
	
}
