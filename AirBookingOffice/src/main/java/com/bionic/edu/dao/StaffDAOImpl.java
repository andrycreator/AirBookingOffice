package com.bionic.edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Staff.StatusStaff;

@Repository
public class StaffDAOImpl implements StaffDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Staff findById(int id) {
		Staff staff = em.find(Staff.class, id);
		if (staff != null) {
			return staff;
		}
		return null;
	}
	
	@Override
	public Staff getAuthorization(String login, String password) {
		try {
			Staff staff = null;
			TypedQuery<Staff> query = em.createQuery(
					"SELECT s FROM Staff s WHERE s.login = :log AND s.password = :pass", Staff.class);
			query.setParameter("log", login);
			query.setParameter("pass", password);
			staff = query.getSingleResult();
			return staff;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Staff> getAllStaff() {
		TypedQuery<Staff> query = em.createQuery(
				"SELECT s FROM Staff s", Staff.class);
		return query.getResultList();
	}
	
	@Override
	public boolean checkLogin(String login) {
		Staff staff = null;
		try {
			TypedQuery<Staff> query = em.createQuery(
					"SELECT s FROM Staff s WHERE s.login = :login", Staff.class);
			query.setParameter("login", login);
			staff = query.getSingleResult();
		} catch (NoResultException e) {
			return true;
		} catch (NonUniqueResultException e) {
			return false;
		}
		return false;
	}
	
	@Override
	public void saveStaff(Staff staff) {
		if (staff.getId() == 0) {
			em.persist(staff);
		} else {
			em.merge(staff);
		}
	}
}
