package com.blz.hotelreservation;

public class Hotel {

	private String hotelName;
	private int hotelRating;
	private int hotelWeekdayRateRegular;
	private int hotelWeekendRateRegular;
	private int hotelWeekdayRateReward;
	private int hotelWeekendRateReward;

	public Hotel() {
	}

	public Hotel(String hotelName, int hotelRating, int hotelWeekdayRateRegular, int hotelWeekendRateRegular,
			int hotelWeekdayRateReward, int hotelWeekendRateReward) {
		this.hotelName = hotelName;
		this.hotelRating = hotelRating;
		this.hotelWeekdayRateRegular = hotelWeekdayRateRegular;
		this.hotelWeekendRateRegular = hotelWeekendRateRegular;
		this.hotelWeekdayRateReward = hotelWeekdayRateReward;
		this.hotelWeekendRateReward = hotelWeekendRateReward;
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

	public int getHotelWeekdayRateReward() {
		return hotelWeekdayRateReward;
	}

	public int getHotelWeekendRateReward() {
		return hotelWeekendRateReward;
	}

}