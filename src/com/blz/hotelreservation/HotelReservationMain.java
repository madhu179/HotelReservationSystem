package com.blz.hotelreservation;

import java.time.LocalDate;
import java.util.*;

public class HotelReservationMain {
	
	private static ArrayList<Hotel> hotelsArrayList = new ArrayList<Hotel>();

	public static void main(String[] args) {
		Scanner scannerObject = new Scanner(System.in);
		System.out.println("Enter the hotel details to add a hotel:");
		for (int i = 0; i < 3; i++) {
			System.out.println("Enter Hotel Name :");
			String hotelName = scannerObject.nextLine();

			System.out.println("Enter hotel's weekday rate for regular customer");
			int hotelWeekdayRateRegular=Integer.parseInt(scannerObject.nextLine());
			
			System.out.println("Enter hotel's weekdend rate for regular customer");
			int hotelWeekendRateRegular=Integer.parseInt(scannerObject.nextLine());
			
			Hotel hotel=new Hotel(hotelName,hotelWeekdayRateRegular,hotelWeekendRateRegular);
			hotelsArrayList.add(hotel);
		}
		System.out.println("All hotel details addes successfully!");
	    
	    System.out.println("Enter the start date of stay in YYYY-MM-DD format");
		String date = scannerObject.nextLine();
		LocalDate firstDate = LocalDate.parse(date);
		System.out.println("Enter the end date of stay in YYYY-MM-DD format");
		date = scannerObject.nextLine();
		LocalDate lastDate = LocalDate.parse(date);
		
		findCheapHotel(firstDate,lastDate);
	}
	
	public static void findCheapHotel(LocalDate firstDate, LocalDate lastDate)
	{
		int minimumRent=2147483647;
		int rent =0;
		LocalDate localFirstDate = firstDate;
		lastDate = lastDate.plusDays(1);
		String hotelName="";
		for(Hotel h : hotelsArrayList)
		{
			rent = 0;
			for (localFirstDate = firstDate; localFirstDate.isBefore(lastDate); localFirstDate = localFirstDate.plusDays(1))
			{
				rent = rent + h.getHotelWeekendRateRegular();
			}
			
			if(rent<minimumRent)
			{
				minimumRent = rent;
				hotelName = h.getHotelName();
			}
		}
		
		System.out.println(hotelName+", Total Fare "+minimumRent);
	}
}
