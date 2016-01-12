package com.bionic.edu.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STAFF")
public class Staff {
	
	public enum StatusStaff {
		ACTIVE("Active"), DEACTIVATE("Deactive");
		
		private String label;

		private StatusStaff(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}
	
	public enum Role {
		ADMIN("Admin"), ACCOUNTANT("Accountant"), ANALYTIC("Analytic"), SECURITY("Security");
		
		private String label;

		private Role(String label) {
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
	private Role role;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusStaff status;
	
	private String first_name;
	private String second_name;
	private String middle_name;
	private String login;
	private String password;
	
	public Staff() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public StatusStaff getStatus() {
		return status;
	}

	public void setStatus(StatusStaff status) {
		this.status = status;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
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
		
	public String toString() {
		return first_name + " " + second_name + " " + middle_name + " " + getStatus() + " " + getRole();
	}
}
