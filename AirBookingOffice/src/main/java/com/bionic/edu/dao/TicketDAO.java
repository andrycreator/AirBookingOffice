package com.bionic.edu.dao;

import java.time.LocalDate;
import java.util.List;

import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Ticket;
import com.bionic.edu.service.AnalyticService.Result;

public interface TicketDAO {
			
	//пошук квитків, що входять в задане замовлення
	public List<Ticket> findByOrdering(Ordering ordering);
	
	//пошук квитків по заданому рейсу
	public List<Ticket> findByFlight(Flight flight);
	
	//продані квитки за період згрупований по даті
	public List<Result> getReportByDate(LocalDate from, LocalDate to);
	
	//продані квитики за період згруповані по місцю призначення
	public List<Result> getReportByDestinPlace(LocalDate from, LocalDate to);
	
}
