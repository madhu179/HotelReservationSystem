package com.blz.hotelreservation;

public class Hotel {

	private String hotelName;
	private int hotelRateRegular;

	public Hotel() {
	}

	public Hotel(String hotelName, int hotelRateRegular) {
		this.hotelName = hotelName;
		this.hotelRateRegular = hotelRateRegular;
	}

	public String getHotelName() {
		return hotelName;
	}

	public int getHotelRateRegular() {
		return hotelRateRegular;
	}

}
