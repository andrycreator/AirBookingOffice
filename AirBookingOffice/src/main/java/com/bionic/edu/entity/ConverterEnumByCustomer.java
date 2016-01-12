package com.bionic.edu.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.bionic.edu.entity.Customer.Gender;

@Converter
public class ConverterEnumByCustomer implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn(Gender gender) {
		if (gender == Gender.MALE) {
			return "M";
		} else {
			return "F";
		}
	}

	@Override
	public Gender convertToEntityAttribute(String ch) {
		if (ch.equals("M")) {
			return Gender.MALE;
		} else {
			return Gender.FEMALE;
		}
	}
}
