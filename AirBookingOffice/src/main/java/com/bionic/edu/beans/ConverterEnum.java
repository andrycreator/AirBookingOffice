package com.bionic.edu.beans;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.edu.entity.Customer.Gender;
import com.bionic.edu.entity.Ordering.StatusOrder;
import com.bionic.edu.entity.Staff.Role;
import com.bionic.edu.entity.Staff.StatusStaff;

@Named
@Scope("session")
public class ConverterEnum {

	public Gender[] getGenders() {
		return Gender.values();
	}
	
	public Role[] getRole() {
		return Role.values();
	}
	
	public StatusStaff[] getStatusStaff() {
		return StatusStaff.values();
	}
	
	public StatusOrder[] getStatusOrder() {
		return StatusOrder.values();
	}
}
