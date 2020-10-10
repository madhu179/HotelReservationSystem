package com.blz.hotelreservation;

import java.time.DayOfWeek;
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

			System.out.println("Enter Hotel Rating :");
			int hotelRating = Integer.parseInt(scannerObject.nextLine());

			System.out.println("Enter hotel's weekday rate for regular customer");
			int hotelWeekdayRateRegular = Integer.parseInt(scannerObject.nextLine());

			System.out.println("Enter hotel's weekdend rate for regular customer");
			int hotelWeekendRateRegular = Integer.parseInt(scannerObject.nextLine());

			Hotel hotel = new Hotel(hotelName, hotelRating, hotelWeekdayRateRegular, hotelWeekendRateRegular);
			hotelsArrayList.add(hotel);
		}
		System.out.println("All hotel details addes successfully!");

		System.out.println("Enter the start date of stay in YYYY-MM-DD format");
		String date = scannerObject.nextLine();
		LocalDate firstDate = LocalDate.parse(date);
		System.out.println("Enter the end date of stay in YYYY-MM-DD format");
		date = scannerObject.nextLine();
		LocalDate lastDate = LocalDate.parse(date);

		int choice = 0;
		do {
			System.out.println("Please Enter your choice :");
			System.out.println("1.Find Cheapest Hotels");
			System.out.println("2.Find Cheapest and Best Rated Hotel");
			System.out.println("3.Exit");
			choice = Integer.parseInt(scannerObject.nextLine());
			switch (choice) {
			case 1:
				findCheapHotelsWeekdayandWeekend(firstDate, lastDate);
				break;
			case 2:
				findCheapBestRatedHotel(firstDate, lastDate);
				break;
			default:
				System.out.println("Invalid choice, Choose again");
			}

		} while (choice != 3);
	}

	public static void findCheapHotelsWeekdayandWeekend(LocalDate firstDate, LocalDate lastDate) {
		DayOfWeek dayName;
		int minimumRent = 2147483647;
		int rent = 0;
		LocalDate localFirstDate = firstDate;
		lastDate = lastDate.plusDays(1);
		String hotelName = "";
		ArrayList<String> listOfHotels = new ArrayList<String>();
		for (Hotel h : hotelsArrayList) {
			localFirstDate = firstDate;
			rent = 0;

			for (localFirstDate = firstDate; localFirstDate
					.isBefore(lastDate); localFirstDate = localFirstDate.plusDays(1)) {
				dayName = localFirstDate.getDayOfWeek();
				switch (dayName) {
				case MONDAY:
				case TUESDAY:
				case WEDNESDAY:
				case THURSDAY:
				case FRIDAY:
					rent = rent + h.getHotelWeekdayRateRegular();
					break;
				case SATURDAY:
				case SUNDAY:
					rent = rent + h.getHotelWeekendRateRegular();
					break;
				}
			}

			if (rent < minimumRent) {
				minimumRent = rent;
				hotelName = h.getHotelName();
				if (listOfHotels.isEmpty()) {
					listOfHotels.add(hotelName);
				} else {
					listOfHotels.clear();
					listOfHotels.add(hotelName);
				}

			} else if (rent == minimumRent)
				listOfHotels.add(h.getHotelName());
		}
		for (int i = 0; i < listOfHotels.size(); i++) {
			System.out.print(listOfHotels.get(i) + " ,");
		}
		System.out.println("with Total rates $" + minimumRent);
	}

	public static void findCheapBestRatedHotel(LocalDate firstDate, LocalDate lastDate) {
		DayOfWeek dayName;
		int minimumRent = 2147483647;
		int rent = 0;
		int hotelRatingVar = 0;
		LocalDate localFirstDate = firstDate;
		lastDate = lastDate.plusDays(1);
		String hotelName = "";
		for (Hotel h : hotelsArrayList) {
			localFirstDate = firstDate;
			rent = 0;

			for (localFirstDate = firstDate; localFirstDate
					.isBefore(lastDate); localFirstDate = localFirstDate.plusDays(1)) {
				dayName = localFirstDate.getDayOfWeek();
				switch (dayName) {
				case MONDAY:
				case TUESDAY:
				case WEDNESDAY:
				case THURSDAY:
				case FRIDAY:
					rent = rent + h.getHotelWeekdayRateRegular();
					break;
				case SATURDAY:
				case SUNDAY:
					rent = rent + h.getHotelWeekendRateRegular();
					break;
				}
			}

			if (rent < minimumRent) {
				minimumRent = rent;
				hotelName = h.getHotelName();
				hotelRatingVar = h.getHotelRating();

			} else if (rent == minimumRent) {
				if (h.getHotelRating() > hotelRatingVar) {
					hotelName = h.getHotelName();
					hotelRatingVar = h.getHotelRating();
				}
			}

		}

		System.out.println(hotelName + " , Rating is " + hotelRatingVar + " with Total rates $" + minimumRent);
	}
}
