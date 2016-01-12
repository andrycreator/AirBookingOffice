package com.bionic.edu.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConverterBirthdayByCustomer implements AttributeConverter<java.util.Date, java.sql.Date> {

	@Override
	public java.sql.Date convertToDatabaseColumn(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	@Override
	public java.util.Date convertToEntityAttribute(java.sql.Date sqlDate) {
		java.util.Date date = new java.util.Date(sqlDate.getTime());
		return date;
	}
}
