package com.bionic.edu.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FLIGHT")
public class Flight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String number;
	private String company;
	private String from_;
	private String to_;
	private java.sql.Timestamp date_departure;
	private java.sql.Timestamp date_arrival;
	private int free_ticket;
	private int booked_ticket;
	private int sold_ticket;
	private double price;
	
	@OneToMany(mappedBy="flight", fetch=FetchType.LAZY)
	private Collection<Ticket> tickets;
	
	public Flight() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFrom_() {
		return from_;
	}

	public void setFrom_(String from_) {
		this.from_ = from_;
	}

	public String getTo_() {
		return to_;
	}

	public void setTo_(String to_) {
		this.to_ = to_;
	}

	public java.sql.Timestamp getDate_departure() {
		return date_departure;
	}

	public void setDate_departure(java.sql.Timestamp date_departure) {
		this.date_departure = date_departure;
	}

	public java.sql.Timestamp getDate_arrival() {
		return date_arrival;
	}

	public void setDate_arrival(java.sql.Timestamp date_arrival) {
		this.date_arrival = date_arrival;
	}

	public int getFree_ticket() {
		return free_ticket;
	}

	public void setFree_ticket(int free_ticket) {
		this.free_ticket = free_ticket;
	}

	public int getBooked_ticket() {
		return booked_ticket;
	}

	public void setBooked_ticket(int booked_ticket) {
		this.booked_ticket = booked_ticket;
	}

	public int getSold_ticket() {
		return sold_ticket;
	}

	public void setSold_ticket(int sold_ticket) {
		this.sold_ticket = sold_ticket;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
		
	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return id+ " " + number + " " + company;
	}
}
