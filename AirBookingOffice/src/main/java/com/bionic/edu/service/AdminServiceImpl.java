package com.bionic.edu.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.FlightDAO;
import com.bionic.edu.dao.OrderingDAO;
import com.bionic.edu.dao.StaffDAO;
import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Ordering.StatusOrder;
import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Ticket;

@Named
public class AdminServiceImpl implements AdminService {

	@Inject
	private FlightDAO flightDAO;
	
	@Inject
	private OrderingDAO orderingDAO;
	
	@Inject
	private StaffDAO staffDAO;
	
	@Override
	public Flight findById(int id) {
		return flightDAO.findById(id);
	}
	
	@Override
	public Staff getAuthorization(String login, String password) {
		return staffDAO.getAuthorization(login, password);
	}
	
	@Override
	public List<Flight> getAllFlights() {
		return flightDAO.getAllFlights();
	}
	
	@Override
	public List<Flight> getFlightByPeriod(LocalDate fromDate, LocalDate toDate) {
		return flightDAO.getFlightByPeriod(fromDate, toDate);
	}
	
	@Override
	@Transactional
	public void addFlight(Flight flight) {
		flightDAO.saveFlight(flight);
	}
	
	@Override
	@Transactional
	public void editFlight(Flight flight) {
		flightDAO.saveFlight(flight);
	}

	@Override
	@Transactional
	public String deleteFlight(int id) {
		Flight flight = flightDAO.findById(id);
		return flightDAO.deleteFlight(flight);
	}

	@Override
	@Transactional
	public void addTicket(int id, int count) {
		Flight flight = flightDAO.findById(id);
		flightDAO.addTicket(flight, count);
	}
	
	@Override
	@Transactional
	public void deleteNotPayOrdering() {
		LocalDate ld = LocalDate.now().minusDays(3);
		List<Ordering> ordering = orderingDAO.findToDate(ld, StatusOrder.NOT_PAID);
		List<Ticket> ticket = null;
		Flight flight = null;
		Map<Flight, Integer> map = new HashMap<>();
		
		for (Ordering or : ordering) {
			ticket = (List<Ticket>) or.getTickets();
			for(Ticket t : ticket) {
				flight = t.getFlight();
				if (map.containsKey(flight)) {
					map.replace(flight, map.get(flight) + 1);
				} else {
					map.put(flight, 1);
				}
			}
			orderingDAO.delete(or);
		}
		for (Map.Entry<Flight, Integer> pair : map.entrySet()) {
			flightDAO.addTicket(pair.getKey(), pair.getValue());
		}
	}
}
