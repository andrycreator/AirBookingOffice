package com.bionic.edu.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.bionic.edu.entity.Staff;

public interface AnalyticService {
	
	//авторизація в системі(id=18)
	public Staff getAuthorization(String login, String password);
	
	//звіт за період згрупований по даті(id=19)
    public List<Result> getReportByDate(LocalDate from, LocalDate to);
	
	//отримання звіту за місцем призначення(id=20)
	public List<Result> getReportByDestinPlace(LocalDate from, LocalDate to);
	
	//клас для отримання результатів
	public static class Result {
		
		private LocalDate ld;
		private long count;
		private double amount;
		private String destinPlace;
		
		public Result(int day, int month, int year, long count, double amount) {
			this.ld = LocalDate.of(year, month, day);
			this.count = count;
			this.amount = amount;
		}
		
		public Result(String destinPlace, long count, double amount) {
			this.destinPlace = destinPlace;
			this.count = count;
			this.amount = amount;
		}
		
		public Result(LocalDate ld, long count, double amount) {
			this.ld = ld;
			this.count = count;
			this.amount = amount;
		}
		
		public LocalDate getLd() {
			return ld;
		}

		public void setLd(LocalDate ld) {
			this.ld = ld;
		}

		public long getCount() {
			return count;
		}

		public void setCount(long count) {
			this.count = count;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getDestinPlace() {
			return destinPlace;
		}

		public void setDestinPlace(String destinPlace) {
			this.destinPlace = destinPlace;
		}

		@Override
		public String toString() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			if (destinPlace == null) {
				return String.format("%s %d %.2f", ld.format(dtf).toString(), count, amount );
			} else {
				return String.format("%s %d %.2f", destinPlace, count, amount );
			}
		}
	}

}
