package com.bionic.edu.entity;

public class NotEnoughTicket extends RuntimeException {

	public NotEnoughTicket(String mess) {
		super(mess);
	}
	
}
