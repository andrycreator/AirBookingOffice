package com.bionic.edu.service;

import java.util.List;

import com.bionic.edu.entity.Staff;

public interface SecurityOffService {
	
	// авторизація в системі(id =21)
	public Staff getAuthorization(String login, String password);

	//пошук користувача по заданому id
	public Staff findById(int id);
	
	//вивести список всіх працівників(id=22)
	public List<Staff> getAllStaff();
	
	//перевірка унікальності нового логіна(id=23)
	public boolean checkLogin(String login);
	
	//добавити нового користувача(id=24)
	public void addStaff(Staff staff);
	
	//редагування профілю користувача(id=25)
	public void editStaff(Staff staff);
	
	//зміна статусу користувача(id=26)
	public void changeStatus(Staff staff);
}
