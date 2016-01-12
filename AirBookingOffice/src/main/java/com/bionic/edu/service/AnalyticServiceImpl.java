package com.bionic.edu.service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.bionic.edu.dao.StaffDAO;
import com.bionic.edu.dao.TicketDAO;
import com.bionic.edu.entity.Staff;

@Named
public class AnalyticServiceImpl implements AnalyticService {
	
	@Inject
	private StaffDAO staffDAO;
	
	@Inject
	private TicketDAO ticketDAO;
	
	@Override
	public Staff getAuthorization(String login, String password) {
		return staffDAO.getAuthorization(login, password);
	}

	@Override
	public List<Result> getReportByDate(LocalDate from, LocalDate to) {
		 return ticketDAO.getReportByDate(from, to);
		
	}

	@Override
	public List<Result> getReportByDestinPlace(LocalDate from, LocalDate to) {
		return ticketDAO.getReportByDestinPlace(from, to);
	}

}
