package com.blz.hotelreservation;
public class Hotel {

	private String hotelName;
	private int hotelWeekdayRateRegular;
	private int hotelWeekendRateRegular;

	public Hotel() {
	}

	public Hotel(String hotelName, int hotelWeekdayRateRegular, int hotelWeekendRateRegular) {
		this.hotelName = hotelName;
		this.hotelWeekdayRateRegular = hotelWeekdayRateRegular;
		this.hotelWeekendRateRegular = hotelWeekendRateRegular;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getHotelWeekdayRateRegular() {
		return hotelWeekdayRateRegular;
	}

	public int getHotelWeekendRateRegular() {
		return hotelWeekendRateRegular;
	}

}