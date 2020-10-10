package com.blz.hotelreservation;

public class Hotel {

	private String hotelName;
	private int hotelRating;
	private int hotelWeekdayRateRegular;
	private int hotelWeekendRateRegular;

	public Hotel() {
	}

	public Hotel(String hotelName, int hotelRating, int hotelWeekdayRateRegular, int hotelWeekendRateRegular) {
		this.hotelName = hotelName;
		this.hotelRating = hotelRating;
		this.hotelWeekdayRateRegular = hotelWeekdayRateRegular;
		this.hotelWeekendRateRegular = hotelWeekendRateRegular;

	}

	public String getHotelName() {
		return hotelName;
	}

	public int getHotelRating() {
		return hotelRating;
	}

	public int getHotelWeekdayRateRegular() {
		return hotelWeekdayRateRegular;
	}

	public int getHotelWeekendRateRegular() {
		return hotelWeekendRateRegular;
	}

}