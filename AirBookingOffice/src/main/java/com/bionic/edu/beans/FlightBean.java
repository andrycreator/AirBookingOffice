package com.bionic.edu.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.Staff;
import com.bionic.edu.service.AdminService;

@Named
@Scope("session")
public class FlightBean implements Serializable {

	@Inject
	private AdminService adminService;
	
	@Inject
	private LoginBean loginBean;
	
	private static final long serialVersionUI = 3L;
	private List<Flight> listFlight;
	private Flight flight;
	private Date dDeparture;
	private Date tDeparture;
	private Date dArrival;
	private Date tArrival;
	private String operationFlight;
	private Date from;
	private Date to;
	private static final Logger log = LogManager.getLogger();

	public FlightBean() {
		flight = new Flight();
	}
	
	public void removeNotPaidOrders() {
		adminService.deleteNotPayOrdering();
		
		Staff staffSession = loginBean.getStaff();
		log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
				staffSession.getRole().getLabel() + "-removeOrders");
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Removed not paid orders.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public void deleteFlight(String id) {
		int n = Integer.valueOf(id);
		flight = adminService.findById(n);
		String mess = adminService.deleteFlight(n);
		
		if (mess.equals("Flight was deleted!")) {
			
			Staff staffSession = loginBean.getStaff();
			log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
					staffSession.getRole().getLabel() + "-removeFlightId: " + flight.getId());
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", mess);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
		
		if (from != null && to != null) {
			LocalDate ldFrom = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate ldTo = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			listFlight = adminService.getFlightByPeriod(ldFrom, ldTo);
		} else {
			listFlight = adminService.getAllFlights();
		}
	}
	
	public String editFlight(String id) {
		operationFlight = "Edit Flight";
		int n = Integer.valueOf(id);
		flight = adminService.findById(n);
		
		Staff staffSession = loginBean.getStaff();
		log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
				staffSession.getRole().getLabel() + "-editFlightId: " + flight.getId());
		
		dDeparture = new Date(flight.getDate_departure().getTime());
		tDeparture = new Date(flight.getDate_departure().getTime());
		dArrival = new Date(flight.getDate_arrival().getTime());
		tArrival = new Date(flight.getDate_arrival().getTime());
		return "addFlight?faces-redirect=true";
	}
	
	public String titleAddFlight() {
		operationFlight = "Add new Flight";
		return "addFlight?faces-redirect=true";
	}
	
	public String saveFlight() {
		flight.setFrom_("Kyiv");
		flight.setDate_departure(java.sql.Timestamp.valueOf(compilationDate(dDeparture, tDeparture)));
		flight.setDate_arrival(java.sql.Timestamp.valueOf(compilationDate(dArrival, tArrival)));
		adminService.addFlight(flight);
		
		Staff staff = loginBean.getStaff();
		log.info("User: " + staff.getFirst_name() + " " + staff.getSecond_name() + " role: " + 
				staff.getRole().getLabel() + "-saveFlightId: " + flight.getId());
		
		if (from != null && to != null) {
			LocalDate ldFrom = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate ldTo = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			listFlight = adminService.getFlightByPeriod(ldFrom, ldTo);
		} else {
			listFlight = adminService.getAllFlights();
		}
		
		flight = new Flight();
		dDeparture = null;
		tDeparture = null;
		dArrival = null;
		tArrival = null;
		return "admin?faces-redirect=true";
	}
	
	public String cancel() {
		flight = new Flight();
		dDeparture = null;
		tDeparture = null;
		dArrival = null;
		tArrival = null;
		return "admin?faces-redirect=true";
	}
	
	public String filterFlightByPeriod() {
		LocalDate ldFrom = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ldTo = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		listFlight = adminService.getFlightByPeriod(ldFrom, ldTo);
		return "admin";
	}
	
	public String resetFlight() {
		from = null;
		to = null;
		return "admin";
	}
	
	public void refreshList() {
		if(from == null && to == null) listFlight = adminService.getAllFlights();
	}
	
	private LocalDateTime compilationDate(Date date, Date time) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.setTime(time);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		
		return LocalDateTime.of(year, month, day, hour, minute);
	}
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<Flight> getListFlight() {
		return listFlight;
	}

	public void setListFlight(List<Flight> listFlight) {
		this.listFlight = listFlight;
	}

	public Date getdDeparture() {
		return dDeparture;
	}

	public void setdDeparture(Date dDeparture) {
		this.dDeparture = dDeparture;
	}

	public Date gettDeparture() {
		return tDeparture;
	}

	public void settDeparture(Date tDeparture) {
		this.tDeparture = tDeparture;
	}

	public Date getdArrival() {
		return dArrival;
	}

	public void setdArrival(Date dArrival) {
		this.dArrival = dArrival;
	}

	public Date gettArrival() {
		return tArrival;
	}

	public void settArrival(Date tArrival) {
		this.tArrival = tArrival;
	}

	public String getOperationFlight() {
		return operationFlight;
	}

	public void setOperationFlight(String operationFlight) {
		this.operationFlight = operationFlight;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

}
