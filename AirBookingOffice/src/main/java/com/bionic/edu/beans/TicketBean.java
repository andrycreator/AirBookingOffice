package com.bionic.edu.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.Customer;
import com.bionic.edu.entity.Customer.Gender;
import com.bionic.edu.entity.Flight;
import com.bionic.edu.entity.NotEnoughTicket;
import com.bionic.edu.entity.Ticket;
import com.bionic.edu.service.AdminService;
import com.bionic.edu.service.CustomerService;

@Named
@Scope("session")
public class TicketBean implements Serializable {

	@Inject
	private CustomerService customerService;
	
	@Inject
	private AdminService adminService;
		
	private static final long serialVersionUI = 3L;
	private String to;
	private Date dateDepart;
	private Set<String> collectTo = new TreeSet<>();
	private List<Flight> selectedFlight = new ArrayList<>();
	private String msgNoFlights;
	private List<Customer> listCustomers = new ArrayList<>();
	private Flight flight;
	private int countTicket = 1;
	private List<Ticket> listTickets = new ArrayList<>();
	private double total;
	private static final Logger log = LogManager.getLogger();

	public TicketBean() {
	
	}
	
	public String redirectCustomer() {
		return "customer?faces-redirect=true";
	}
	
	public String redirectCart() {
		return "cart?faces-redirect=true";
	}
	
	public void makeBooking() {
		String title = null;
		String mess = null;
		try {
			mess = customerService.addOrdering(listTickets);
			title = "Completed";
			
			log.info("Customer: " + "-addBooking: " + listTickets.get(0).getOrdering().getId());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, mess);
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			listCustomers.clear();
			listTickets.clear();
		} catch (NotEnoughTicket e) {
			mess = e.getMessage();
			title = "Not completed";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, mess);
			RequestContext.getCurrentInstance().showMessageInDialog(message);
		}
	}
	
	public String deleteFromCart(String indexStr) {
		int index = Integer.valueOf(indexStr);
		listTickets.remove(index);
		return "cart?faces-redirect=true";
	}
	
	public void calcTotal() {
		total = listTickets.stream().mapToDouble(Ticket::getPrice).sum();
	}
	
	public String addToCart() {
		for (Customer customer : listCustomers) {
			Ticket ticket = new Ticket();
				ticket.setFlight(flight);
				ticket.setCustomer(customer);
				ticket.setPrice(flight.getPrice());
			listTickets.add(ticket);
		}
		return "cart?faces-redirect=true";
	}
	
	public String iconCart() {
		if (listTickets.size() != 0) {
			return "shoping48_full.png";
		}
		return "shoping48.png";
	}
	
	public String initCustomer(String id) {
		int idFlight =  Integer.valueOf(id);
		flight = adminService.findById(idFlight);
				
		listCustomers.clear();
		for (int i = 0; i < countTicket; i++) {
			listCustomers.add(new Customer());
		}
		return "inputCustomer?faces-redirect=true";
	}
	
	public String decrementCountTicket() {
		if (countTicket > 1) countTicket--;
		return "customer";
	}
	
	public String incrementCountTicket() {
		countTicket++;
		return "customer";
	}
	
	public String durationFlight(java.sql.Timestamp departure, java.sql.Timestamp arrival) {
		LocalDateTime ltDeparture = departure.toLocalDateTime();
		LocalDateTime ltArrival = arrival.toLocalDateTime();
		long value = ltDeparture.until(ltArrival, ChronoUnit.MINUTES);
        int hour = (int) value / 60;
        int min = (int) value % 60;
		return String.format("%d h %d min", hour, min);
	}
	
	public String convertToTime(java.sql.Timestamp timestamp) {
		LocalTime lt = timestamp.toLocalDateTime().toLocalTime();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		return lt.format(dtf);
	}
	
	public String getSelectFlight() {
		LocalDate ld = dateDepart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		selectedFlight = customerService.findFlight(ld, to);
		return "findFlight?faces-redirect=true";
	}
	
	public String convertToDayOfWeek() {
		LocalDate ld = dateDepart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return ld.getDayOfWeek().toString();
	}
	
	public String convertToDate() {
		LocalDate ld = dateDepart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return ld.format(dtf);	
	}
	
	public void getAllPlaceArrival() {
		List<Flight> list = customerService.getFlightByPeriod(LocalDate.now(), LocalDate.now().plusYears(3));
		for(Flight f : list) {
			collectTo.add(f.getTo_());
		}
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Set<String> getCollectTo() {
		return collectTo;
	}

	public void setCollectTo(Set<String> collectTo) {
		this.collectTo = collectTo;
	}

	public List<Flight> getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(List<Flight> selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public List<Ticket> getListTickets() {
		return listTickets;
	}

	public void setListTickets(List<Ticket> listTickets) {
		this.listTickets = listTickets;
	}

	public String getMsgNoFlights() {
		return msgNoFlights;
	}

	public void setMsgNoFlights(String msgNoFlights) {
		this.msgNoFlights = msgNoFlights;
	}

	public List<Customer> getListCustomers() {
		return listCustomers;
	}

	public void setListCustomers(List<Customer> listCustomers) {
		this.listCustomers = listCustomers;
	}

	public void setCountTicket(int countTicket) {
		this.countTicket = countTicket;
	}

	public int getCountTicket() {
		return countTicket;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
