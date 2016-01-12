package com.bionic.edu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.persistence.internal.jpa.parsing.NotEqualsNode;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.FlightDAO;
import com.bionic.edu.dao.OrderingDAO;
import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.NotEnoughTicket;
import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Ordering.StatusOrder;
import com.bionic.edu.entity.Ticket;

@Named
public class CustomerServiceImpl implements CustomerService {
	
	@Inject
	private FlightDAO flightDAO;
	
	@Inject
	private OrderingDAO orderingDAO;

	@Override
	public List<Flight> findFlight(LocalDate dateDeparture, String to) {
		return flightDAO.findFlight(dateDeparture, to);
	}
	
	@Override
	public List<Flight> getFlightByPeriod(LocalDate start, LocalDate end) {
		return flightDAO.getFlightByPeriod(start, end);
	}

	@Override
	@Transactional
	public String addOrdering(List<Ticket> tickets) throws NotEnoughTicket {
				
		Map<Integer, Integer> map = new HashMap<>();
		Flight flight = null;
		int countTickets = 0;
		
		Ordering ordering = new Ordering();
			ordering.setStatus(StatusOrder.NOT_PAID);
			ordering.setReserv_date(java.sql.Timestamp.valueOf(LocalDateTime.now()));
			ordering.setTickets(tickets);
		
		for (Ticket t : tickets) {
			t.setOrdering(ordering);
			flight = t.getFlight();
			
			if (map.containsKey(flight.getId())) {
				map.replace(flight.getId(), map.get(flight.getId()) + 1);
			} else {
				map.put(flight.getId(), 1);
			}
		}
		
		for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
			flight = flightDAO.findById(pair.getKey());
			countTickets = pair.getValue();
			
			if (flight.getFree_ticket() < countTickets) {
				throw new NotEnoughTicket("The flight " + flight.getNumber() + " doesn`t have " + countTickets + " available tickets!");
			} else {
				flight.setFree_ticket(flight.getFree_ticket() - countTickets);
				flight.setBooked_ticket(flight.getBooked_ticket() + countTickets);
				flightDAO.saveFlight(flight);
			}
		}
		orderingDAO.saveOrdering(ordering);
		return "Your booking accepted!";
	}

	//test
	
	@Override
	public Flight findById(int id) {
		return flightDAO.findById(id);
	}

}
