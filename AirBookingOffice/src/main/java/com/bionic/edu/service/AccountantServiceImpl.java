package com.bionic.edu.service;

import java.time.LocalDateTime;
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
import com.bionic.edu.entity.Ticket;
import com.bionic.edu.entity.Ordering.StatusOrder;
import com.bionic.edu.entity.Staff;

@Named
public class AccountantServiceImpl implements AccountantService {

	@Inject
	private OrderingDAO orderingDAO;
	
	@Inject
	private FlightDAO flightDAO;
	
	@Inject
	private StaffDAO staffDAO;
	
	@Override
	public Staff getAuthorization(String login, String password) {
		return staffDAO.getAuthorization(login, password);
	}
	
	@Override
	public Ordering findById (int id) {
		return orderingDAO.findById(id);
	}
	
	@Override
	public List<Ordering> showBookedOrdering() {
		return orderingDAO.showBookedOrdering();
	}
	
	@Override
	@Transactional
	public void changeStatusOrder(int id) {
		Ordering ordering = orderingDAO.findById(id);
			ordering.setStatus(StatusOrder.PAID);
			ordering.setSold_date(java.sql.Timestamp.valueOf(LocalDateTime.now()));
			
		List<Ticket> ticket = (List<Ticket>) ordering.getTickets();
		Flight flight = null;
		Map<Integer, Integer> map = new HashMap<>();
		int countTicket = 0;
		
		for(Ticket t : ticket) {
			flight = t.getFlight();
			
			if (map.containsKey(flight.getId())) {
				map.replace(flight.getId(), map.get(flight.getId()) + 1);
			} else {
				map.put(flight.getId(), 1);
			}
		}
		for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
			flight = flightDAO.findById(pair.getKey()) ;
			countTicket = pair.getValue();
			
			flight.setBooked_ticket(flight.getBooked_ticket() - countTicket);
			flight.setSold_ticket(flight.getSold_ticket() + countTicket);
			flightDAO.saveFlight(flight);
		}
		orderingDAO.saveOrdering(ordering);
	}
	
}
