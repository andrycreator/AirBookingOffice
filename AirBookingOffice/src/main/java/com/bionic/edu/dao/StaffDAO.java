package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.entity.Staff;

public interface StaffDAO {
	
	//пошук користувача по заданому id
	public Staff findById(int id);
	
	// авторизація в системі всіх зареєстрованих користувачів
	public Staff getAuthorization(String login, String password);
	
	//вивести список всіх працівників
	public List<Staff> getAllStaff();
	
	//перевірка унікальності нового логіна(id=23)
	public boolean checkLogin(String login);
	
	//добавити нового користувача
	public void saveStaff(Staff staff);
}
