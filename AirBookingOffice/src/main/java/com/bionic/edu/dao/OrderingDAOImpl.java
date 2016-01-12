package com.bionic.edu.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Ordering.StatusOrder;

@Repository
public class OrderingDAOImpl implements OrderingDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Ordering findById(int id) {
		Ordering ordering = em.find(Ordering.class, id);
		if (ordering != null) {
			return ordering;
		}
		return null;
	}
	
	@Override
	public List<Ordering> findByPeriod(LocalDate from, LocalDate to, StatusOrder statusOrder) {
		TypedQuery<Ordering> query = em.createQuery(
				"SELECT o FROM Ordering o " +
				"WHERE o.reserv_date >= :from " +
				"AND o.reserv_date < :to " +
				"AND o.status = :status", Ordering.class);
		query.setParameter("to", java.sql.Timestamp.valueOf(from.atStartOfDay()));
		query.setParameter("to", java.sql.Timestamp.valueOf(to.plusDays(1).atStartOfDay()));
		query.setParameter("status", statusOrder);
		return query.getResultList();
	}
	
	@Override
	public List<Ordering> findToDate(LocalDate to, StatusOrder statusOrder) {
		TypedQuery<Ordering> query = em.createQuery(
				"SELECT o FROM Ordering o " +
				"WHERE o.reserv_date < :to " +
				"AND o.status = :status", Ordering.class);
		query.setParameter("to", java.sql.Timestamp.valueOf(to.plusDays(1).atStartOfDay()));
		query.setParameter("status", statusOrder);
		return query.getResultList();
	}
	
	@Override
	public void delete(Ordering ordering) {
		if (ordering.getId() != 0) {
			em.remove(ordering);
		}
	}

	@Override
	public void saveOrdering(Ordering ordering) {
		if (ordering.getId() == 0) {
			em.persist(ordering);
		} else {
			em.merge(ordering);
		}
	}
	
	@Override
	public List<Ordering> showBookedOrdering() {
		TypedQuery<Ordering> query = em.createQuery(
				"SELECT o FROM Ordering o WHERE o.status = :status", Ordering.class);
		query.setParameter("status", StatusOrder.NOT_PAID);
		return query.getResultList();
	}
}
