package com.bionic.edu.beans;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named("msg")
@Scope("session")
public class MessageBean {

	//message for Admin
	private final String numberNotEmptyRequired = "The fild \"Flight number\" can not be empty";
	private final String companyNotEmptyRequired = "The fild \"Company\" can not be empty";
	private final String toNotEmptyRequired = "The field \"To\" can not be empty";
	
	private final String dateDapartureNotEmptyRequired = "The field \"Date departure\" can not be empty";
	private final String timeDapartureNotEmptyRequired = "The field \"Time departure\" can not be empty";
	
	private final String dateArrivalNotEmptyRequired = "The field \"Date arrival\" can not be empty";
	private final String timeArrivalNotEmptyRequired = "The field \"Time arrival\" can not be empty";
	
	private final String ticketsNotEmptyRequired = "The field \"Tickets\" can not be empty";
	private final String ticketsNotFormatRequired = "The value of the field \"Tickets\" must be a number";
	private final String ticketsLessRequired = "The value of the field \"Tickets\" can not be less than \"1\"";
	private final String priceNotEmptyRequired = "The field \"Ticket price\" can not be empty";
	private final String priceNotFormatRequired = "The value of the field \"Ticket price\" must be a number";
	private final String priceLessMinRequired = "The value of the field \"Ticket price\" can not be less then \"0.01\"";
	
	private final String dateFromNotEmptyRequired = "The field \"Date from\" can not be empty";
	private final String dateToNotEmptyRequired = "The field \"Date to\" can not be empty";
	
	//message for Customer
	private final String placeArrivalNotEmpty = "The value of field \"Place arrival\" is not choosen";
	private final String countTicketNotEmpty = "The field \"Tickets\" can not be empty";
	private final String countTicketLessRequired = "The value of field \"Tickets\" can not be less than \"1\"";
	private final String countTicketNotFormatRequired = "The value of field \"Tickets\" has wrong format";
	private final String customerFieldsNotEmpty = "Some fields remain empty";
	
	// message for Analytic
	private final String analyticDateStartNotEmpty = "The field \"Date start\" can not be empty";
	private final String analyticDateEndNotEmpty = "The field \"Date end\" can not be empty";
	
	//message for Security
	private final String securityFirstNameNotEmpty = "The field \"First name\" can not be empty";
	private final String securitySecondNameNotEmpty = "The field \"Second name\" can not be empty";
	private final String securityMiddleNameNotEmpty = "The field \"Middle name\" can not be empty";
	private final String securityRoleNotEmpty = "The field \"Role\" can not be empty";
	private final String securityStatusNotEmpty = "The field \"Status\" can not be empty";
	private final String securityLoginNotEmpty = "The field \"Login\" can not be empty";
	private final String securityPasswordNotEmpty = "The field \"Password\" can not be empty";
	
	public MessageBean() {
	}
	
	public String getNumberNotEmptyRequired() {
		return numberNotEmptyRequired;
	}
	public String getCompanyNotEmptyRequired() {
		return companyNotEmptyRequired;
	}
	public String getToNotEmptyRequired() {
		return toNotEmptyRequired;
	}
	public String getDateDapartureNotEmptyRequired() {
		return dateDapartureNotEmptyRequired;
	}
	public String getTimeDapartureNotEmptyRequired() {
		return timeDapartureNotEmptyRequired;
	}
	public String getDateArrivalNotEmptyRequired() {
		return dateArrivalNotEmptyRequired;
	}
	public String getTimeArrivalNotEmptyRequired() {
		return timeArrivalNotEmptyRequired;
	}
	public String getTicketsNotEmptyRequired() {
		return ticketsNotEmptyRequired;
	}
	public String getTicketsNotFormatRequired() {
		return ticketsNotFormatRequired;
	}
	public String getTicketsLessRequired() {
		return ticketsLessRequired;
	}
	public String getPriceNotEmptyRequired() {
		return priceNotEmptyRequired;
	}
	public String getPriceNotFormatRequired() {
		return priceNotFormatRequired;
	}
	public String getPriceLessMinRequired() {
		return priceLessMinRequired;
	}
	public String getDateFromNotEmptyRequired() {
		return dateFromNotEmptyRequired;
	}
	public String getDateToNotEmptyRequired() {
		return dateToNotEmptyRequired;
	}
	public String getPlaceArrivalNotEmpty() {
		return placeArrivalNotEmpty;
	}
	public String getCountTicketNotEmpty() {
		return countTicketNotEmpty;
	}
	public String getCountTicketLessRequired() {
		return countTicketLessRequired;
	}
	public String getCountTicketNotFormatRequired() {
		return countTicketNotFormatRequired;
	}
	public String getCustomerFieldsNotEmpty() {
		return customerFieldsNotEmpty;
	}
	public String getAnalyticDateStartNotEmpty() {
		return analyticDateStartNotEmpty;
	}
	public String getAnalyticDateEndNotEmpty() {
		return analyticDateEndNotEmpty;
	}
	public String getSecurityFirstNameNotEmpty() {
		return securityFirstNameNotEmpty;
	}
	public String getSecuritySecondNameNotEmpty() {
		return securitySecondNameNotEmpty;
	}
	public String getSecurityMiddleNameNotEmpty() {
		return securityMiddleNameNotEmpty;
	}
	public String getSecurityRoleNotEmpty() {
		return securityRoleNotEmpty;
	}
	public String getSecurityStatusNotEmpty() {
		return securityStatusNotEmpty;
	}
	public String getSecurityLoginNotEmpty() {
		return securityLoginNotEmpty;
	}
	public String getSecurityPasswordNotEmpty() {
		return securityPasswordNotEmpty;
	}
}
