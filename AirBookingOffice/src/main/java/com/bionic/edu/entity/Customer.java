package com.bionic.edu.entity;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {
	
	public enum Gender {
		MALE("Male"), FEMALE("Female");
		
		private String label;
		
		private Gender(String label) {
			this.label = label;
		}
		
		public String getLabel () {
			return label;
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String first_name;
	private String second_name;
	
	@Convert(converter = ConverterEnumByCustomer.class)
	private Gender gender;
	
	private String passport_data;
	
	@Convert(converter = ConverterBirthdayByCustomer.class)
	private Date birthday;
	
	public Customer() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPassport_data() {
		return passport_data;
	}

	public void setPassport_data(String passport_data) {
		this.passport_data = passport_data;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
		
	@Override
	public String toString() {
		return first_name + " " + second_name + " " + gender + " " + birthday + " " + passport_data;
	}
}
