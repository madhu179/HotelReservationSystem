package com.blz.hotelreservation;

import java.util.*;

public class HotelReservationMain {

	public static void main(String[] args) {
		Scanner scannerObject = new Scanner(System.in);
		ArrayList<Hotel> hotelsArrayList = new ArrayList<Hotel>();
		System.out.println("Enter the hotel details to add a hotel:");
		for (int i = 0; i < 3; i++) {
			System.out.println("Enter Hotel Name :");
			String hotelName = scannerObject.next();

			System.out.println("Enter hotel's rate for regular customer");
			int hotelRateRegular = scannerObject.nextInt();

			Hotel hotel = new Hotel(hotelName, hotelRateRegular);
			hotelsArrayList.add(hotel);
		}
		System.out.print("All hotel details addes successfully!");
	}

}
