package com.bionic.edu.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORDERING")
public class Ordering {
	
	public enum StatusOrder {
		PAID("Paid"), NOT_PAID("Booked");
		
		private String label;
		
		private StatusOrder(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOrder status;
	
	private java.sql.Timestamp reserv_date;
	private java.sql.Timestamp sold_date;
	
	@OneToMany(mappedBy="ordering", fetch=FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private Collection<Ticket> tickets;
	
	public Ordering() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public StatusOrder getStatus() {
		return status;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public java.sql.Timestamp getReserv_date() {
		return reserv_date;
	}

	public void setReserv_date(java.sql.Timestamp reserv_date) {
		this.reserv_date = reserv_date;
	}

	public java.sql.Timestamp getSold_date() {
		return sold_date;
	}

	public void setSold_date(java.sql.Timestamp sold_date) {
		this.sold_date = sold_date;
	}
	
	public Collection<Ticket> getTickets() {
		return (List<Ticket>) tickets;
	}

	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = (List<Ticket>) tickets;
	}

	@Override
	public String toString() {
		return id + " " + status + " " + reserv_date + " " + sold_date;
	}
}
