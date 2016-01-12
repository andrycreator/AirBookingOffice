package com.bionic.edu.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Ticket;
import com.bionic.edu.entity.Ordering.StatusOrder;
import com.bionic.edu.service.AnalyticService.Result;

@Repository
public class TicketDAOImpl implements TicketDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Ticket> findByOrdering(Ordering ordering) {
		if (ordering.getId() != 0) {
			TypedQuery<Ticket> query = em.createQuery(
					"SELECT t FROM Ticket t WHERE t.ordering = :Ordering", Ticket.class);
			query.setParameter("Ordering", ordering);
			return query.getResultList();
		}
		return null;
	}

	@Override
	public List<Ticket> findByFlight(Flight flight) {
		if (flight.getId() != 0) {
			TypedQuery<Ticket> query = em.createQuery(
					"SELECT t FROM Ticket t WHERE t.flight = :Flight", Ticket.class);
			query.setParameter("Flight", flight);
			return query.getResultList();
		}
		return null;
	}

	@Override
	public List<Result> getReportByDate(LocalDate from, LocalDate to) {
		TypedQuery<Result> query = em.createQuery(
				"SELECT new com.bionic.edu.service.AnalyticService.Result " +
				"(FUNC('DAY',t.ordering.sold_date), FUNC('MONTH',t.ordering.sold_date), FUNC('YEAR',t.ordering.sold_date), COUNT(t), SUM(t.price)) " +
				"FROM Ticket t " +
				"WHERE t.ordering.sold_date >= :from " + 
				"AND t.ordering.sold_date < :to " +
				"AND t.ordering.status = :status " +
				"GROUP BY FUNC('DAY',t.ordering.sold_date), FUNC('MONTH',t.ordering.sold_date), FUNC('YEAR',t.ordering.sold_date)", Result.class);
		query.setParameter("from", java.sql.Timestamp.valueOf(from.atStartOfDay()));
		query.setParameter("to", java.sql.Timestamp.valueOf(to.plusDays(1).atStartOfDay()));
		query.setParameter("status", StatusOrder.PAID);
		return query.getResultList();
	}

	@Override
	public List<Result> getReportByDestinPlace(LocalDate from, LocalDate to) {
		TypedQuery<Result> query = em.createQuery(
				"SELECT new com.bionic.edu.service.AnalyticService.Result(t.flight.to_, COUNT(t), SUM(t.price)) " +
				"FROM Ticket t " +
				"WHERE t.ordering.sold_date >= :from " + 
				"AND t.ordering.sold_date < :to " +
				"AND t.ordering.status = :status " +
				"GROUP BY t.flight.to_", Result.class);
		query.setParameter("from", java.sql.Timestamp.valueOf(from.atStartOfDay()));
		query.setParameter("to", java.sql.Timestamp.valueOf(to.plusDays(1).atStartOfDay()));
		query.setParameter("status", StatusOrder.PAID);
		return query.getResultList();
	}		
}
