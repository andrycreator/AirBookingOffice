package com.bionic.edu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TICKET")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_flight")
	private Flight flight;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ordering")
	private Ordering ordering;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.REMOVE, CascadeType.PERSIST})
	@JoinColumn(name="id_customer")
	private Customer customer;
	
	private double price;
	
	public Ticket() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Ordering getOrdering() {
		return ordering;
	}

	public void setOrdering(Ordering ordering) {
		this.ordering = ordering;
	}	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
//		return id + " " + flight.getId() + " " + ordering.getId() + " " + ordering.getStatus() + " " + customer.getFirst_name() + 
//				" " + customer.getSecond_name() + " " + price;
		return customer.getFirst_name() + " " + customer.getSecond_name() + " " + price;
	}
	
	
}
