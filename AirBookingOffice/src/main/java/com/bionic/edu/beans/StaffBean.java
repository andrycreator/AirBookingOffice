package com.bionic.edu.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.Staff;
import com.bionic.edu.service.SecurityOffService;

@Named
@Scope("session")
public class StaffBean implements Serializable {

	@Inject
	private SecurityOffService securityOffService;
	
	@Inject
	private LoginBean loginBean;
	
	private static final long serialVersionUI = 3L;
	private List<Staff> listStaff;
	private String title;
	private Staff staff;
	private static final Logger log = LogManager.getLogger();
	
	public StaffBean() {
		
	}
	
	public String cancel() {
		staff = null;
		return "securityOfficer?faces-redirect=true";
	}
	
	public String saveStaff() {
		if (!securityOffService.checkLogin(staff.getLogin())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "This login is already in use");
		    RequestContext.getCurrentInstance().showMessageInDialog(message);
		    return null;
		} else {
		    securityOffService.addStaff(staff);
		    
		    Staff staffSession = loginBean.getStaff();
		    log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
		    		staffSession.getRole().getLabel() + "-saveStaffId: " + staff.getId());
		    
			return "securityOfficer?faces-redirect=true";
			
		}
	}
	
	public String changeStatus(String idString) {
		int id = Integer.valueOf(idString);
		staff = securityOffService.findById(id);
		securityOffService.changeStatus(staff);
		
		Staff staffSession = loginBean.getStaff();
	    log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
	    		staffSession.getRole().getLabel() + "-changeStatusStaffId: " + staff.getId());
		
		return "securityOfficer?faces-redirect=true";
	}
	
	public String editStaff(String idString) {
		int id = Integer.valueOf(idString);
		staff = securityOffService.findById(id);
		title = "Edit staff";
		
		Staff staffSession = loginBean.getStaff();
	    log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
	    		staffSession.getRole().getLabel() + "-editStaffId: " + staff.getId());
		
		return "addStaff?faces-redirect=true";
	}
	
	public String addStaff() {
		staff = new Staff();
		title = "Add new staff";
		
		Staff staffSession = loginBean.getStaff();
	    log.info("User: " + staffSession.getFirst_name() + " " + staffSession.getSecond_name() + " role: " + 
	    		staffSession.getRole().getLabel() + "-addStaffId: " + staff.getId());
		
		return "addStaff?faces-redirect=true";
	}
	
	public void fillListStaff() {
		listStaff = securityOffService.getAllStaff();
	}
	
	public List<Staff> getListStaff() {
		return listStaff;
	}
	
	public void setListStaff(List<Staff> listStaff) {
		this.listStaff = listStaff;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}
