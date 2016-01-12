package com.bionic.edu.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.Ordering;
import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Ordering.StatusOrder;
import com.bionic.edu.entity.Ticket;
import com.bionic.edu.service.AccountantService;

@Named
@Scope("session")
public class OrdersBean implements Serializable {

	@Inject
	private AccountantService accountantService;
	
	@Inject
	private LoginBean loginBean;
	
	private static final long serialVersionUI = 3L;
	private List<Ordering> list = new ArrayList<>();
	private static final Logger log = LogManager.getLogger();
	
	public OrdersBean() {
		
	}
	
	public void init() {
		list = accountantService.showBookedOrdering();
	}
	
	public void paidOrder(String idString) {
		int id = Integer.valueOf(idString);
		Ordering ordering = accountantService.findById(id);
		accountantService.changeStatusOrder(id);
		
		Staff staffSession = loginBean.getStaff();
	    log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
	    		staffSession.getRole().getLabel() + "-paidOrder: " + ordering.getId());
		
		String mess = "The status of order № " + id + " was changed";	
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Change completed", mess);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
	
	public double amountOrder(Ordering ordering) {
		List<Ticket> listTickets =  (List<Ticket>) ordering.getTickets();
		double amount = 0d;
		for(Ticket ticket : listTickets) {
			amount += ticket.getPrice();
		}
		return amount;
	}
	
	public List<Ordering> getList() {
		return list;
	}
	
	public void setList(List<Ordering> list) {
		this.list = list;
	}
}
