package com.bionic.edu.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.Staff;
import com.bionic.edu.entity.Staff.Role;
import com.bionic.edu.entity.Staff.StatusStaff;
import com.bionic.edu.service.SecurityOffService;

@Named
@Scope("session")
public class LoginBean implements Serializable {

	@Inject
	private SecurityOffService securityOffService;
	
	private static final long serialVersionUI = 3L;
	private String login;
	private String password;
	private Staff staff;
	private String msgWrong;
	private Staff.Role role;
	private static final Logger log = LogManager.getLogger();

	public LoginBean() {
		
	}
	
	public String logOut() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		log.info("User: " + staff.getFirst_name() + " " + staff.getSecond_name() + " role: " + role.getLabel() + "-logOut");
		return "/login?faces-redirect=true";
	}
	
	public String logIn() {
		staff = securityOffService.getAuthorization(login, password);
		
		if (staff != null) {
			
			if (staff.getStatus() == StatusStaff.ACTIVE) {
				msgWrong = null;
				
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				role = staff.getRole();
				session.setAttribute("Role", role);
				session.setAttribute("Staff", staff);
				
				log.info("User: " + staff.getFirst_name() + " " + staff.getSecond_name() + " role: " + 
						role.getLabel() + "-logIn");
				
				switch(role) {
					case ADMIN:
						return "admin/admin?faces-redirect=true";
					case ACCOUNTANT:
						return "accountant/accountant?faces-redirect=true";
					case ANALYTIC:
						return "analytic/analytic?faces-redirect=true";
					case SECURITY:
						return "securityOfficer/securityOfficer?faces-redirect=true";
					default:
						return "login";
				}
			} else {
				msgWrong = "This account has been deactivated!";
				return "login";
			}
		} else {
			msgWrong = "Your login or password is wrong!";
			return "login";
		}
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Staff getStaff() {
		return staff;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsgWrong() {
		return msgWrong;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
