package com.bionic.edu.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.edu.dao.StaffDAO;
import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Staff.StatusStaff;

@Named
public class SecurityOffServiceImpl implements SecurityOffService {

	@Inject
	private StaffDAO staffDAO;
	
	@Override
	public Staff getAuthorization(String login, String password) {
		return staffDAO.getAuthorization(login, password);
	}
	
	@Override
	public Staff findById(int id) {
		return staffDAO.findById(id);
	}
	
	@Override
	public List<Staff> getAllStaff() {
		return staffDAO.getAllStaff();
	}
	
	@Override
	public boolean checkLogin(String login) {
		return staffDAO.checkLogin(login);
	}
		
	@Override
	@Transactional
	public void addStaff(Staff staff) {
		staffDAO.saveStaff(staff);
	}

	@Override
	@Transactional
	public void editStaff(Staff staff) {
		staffDAO.saveStaff(staff);
	}

	@Override
	@Transactional
	public void changeStatus(Staff staff) {
		if (staff.getStatus() == StatusStaff.ACTIVE) {
			staff.setStatus(StatusStaff.DEACTIVATE);
		} else {
			staff.setStatus(StatusStaff.ACTIVE);
		}
		staffDAO.saveStaff(staff);
	}
}
