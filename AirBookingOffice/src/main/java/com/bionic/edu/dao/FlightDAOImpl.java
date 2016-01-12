package com.bionic.edu.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.bionic.edu.entity.Flight;

@Repository
public class FlightDAOImpl implements FlightDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Flight findById (int id) {
		Flight flight = em.find(Flight.class, id);
		if (flight != null) {
			return flight;
		}
		return null;
	}
	
	@Override
	public List<Flight> getAllFlights() {
		TypedQuery<Flight> query = em.createQuery(
				"SELECT f FROM Flight f " +
				"WHERE f.date_departure >= :today", Flight.class);
		query.setParameter("today", java.sql.Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		return query.getResultList();
	}
	
	@Override
	public List<Flight> getFlightByPeriod(LocalDate start, LocalDate end) {
		TypedQuery<Flight> query = em.createQuery(
				"SELECT f FROM Flight f " +
				"WHERE f.date_departure >= :start " +
				"AND f.date_departure < :end", Flight.class);
		query.setParameter("start", java.sql.Timestamp.valueOf(start.atStartOfDay()));
		query.setParameter("end", java.sql.Timestamp.valueOf(end.plusDays(1).atStartOfDay()));
		return query.getResultList();
	}
	
	@Override
	public void saveFlight(Flight flight) {
		if (flight.getId() == 0) {
			em.persist(flight);
		} else {
			em.merge(flight);
		}
	}

	@Override
	public String deleteFlight(Flight flight) {
		if (flight != null) {
			if (flight.getSold_ticket() == 0 && flight.getBooked_ticket() == 0 ) {
				em.remove(flight);
				return "Flight was deleted!";
			}
			return "You can not delete this flight, because there are sold or booked tickets!";
		} 
		return null;
	}

	@Override
	public void addTicket(Flight flight, int count) {
		flight.setFree_ticket(flight.getFree_ticket() + count);
		em.merge(flight);
	}

	@Override
	public List<Flight> findFlight(LocalDate dateDeparture, String to) {
		TypedQuery<Flight> query = em.createQuery(
				"SELECT f FROM Flight f " +
				"WHERE f.date_departure >= :dateWith " +
				"AND f.date_departure < :dateTo " +
				"AND f.date_departure >= :today " +
				"AND f.to_ = :to", Flight.class);
		query.setParameter("dateWith", java.sql.Timestamp.valueOf(dateDeparture.atStartOfDay()));
		query.setParameter("dateTo", java.sql.Timestamp.valueOf(dateDeparture.plusDays(1).atStartOfDay()));
		query.setParameter("today", java.sql.Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		query.setParameter("to", to);
		return query.getResultList();
	}

}
