package com.blz.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@FunctionalInterface
interface LambdaInterface {
	boolean validate(String regex, String date) throws Exception;
}

public class HotelReservationMain {

	static LambdaInterface function = (regex, date) -> {
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(date);
		if (m.matches())
			return true;
		throw new Exception("Inavlid Date Format");
	};

	private static ArrayList<Hotel> hotelsArrayList = new ArrayList<Hotel>();
	private static HashMap<Hotel, Integer> rentMapReward = new HashMap<Hotel, Integer>();

	public static void main(String[] args) {
		Scanner scannerObject = new Scanner(System.in);
		int customerType = 0;
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

			System.out.println("Enter hotel's weekday rate for reward customer");
			int hotelWeekdayRateReward = Integer.parseInt(scannerObject.nextLine());

			System.out.println("Enter hotel's weekdend rate for reward customer");
			int hotelWeekendRateReward = Integer.parseInt(scannerObject.nextLine());

			Hotel hotel = new Hotel(hotelName, hotelRating, hotelWeekdayRateRegular, hotelWeekendRateRegular,
					hotelWeekdayRateReward, hotelWeekendRateReward);
			hotelsArrayList.add(hotel);
		}

		System.out.println("All hotel details addes successfully!");

		System.out.println("Enter Customer Type : (Reward/Regular)");
		try {
			String customer = scannerObject.nextLine();
			if (customer.equals("Regular") || customer.equals("Reward"))
				customerType = customer.contentEquals("Regular") ? 1 : 2;
			else
				throw new Exception("Invalid Customer Type");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("Enter the start date of stay in YYYY-MM-DD format");
		String date = scannerObject.nextLine();
		try {
			boolean result = function.validate("[1-9][0-9][0-9][0-9][-][0-1][0-9][-][0-3][0-9]", date);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		LocalDate firstDate = LocalDate.parse(date);
		System.out.println("Enter the end date of stay in YYYY-MM-DD format");
		date = scannerObject.nextLine();
		try {
			boolean result = function.validate("[1-9][0-9][0-9][0-9][-][0-1][0-9][-][0-3][0-9]", date);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		LocalDate lastDate = LocalDate.parse(date);

		initializeRentMaps(firstDate, lastDate);

		int choice = 0;
		do {
			System.out.println("Please Enter your choice :");
			System.out.println("1.Find Cheapest Hotels");
			System.out.println("2.Find Cheapest and Best Rated Hotel");
			System.out.println("3.Find Best Rated Hotel");
			System.out.println("4.Find Cheapest and Best Rated Hotel for Reward Using Streams");
			System.out.println("5.Exit");
			choice = Integer.parseInt(scannerObject.nextLine());
			switch (choice) {
			case 1:
				findCheapHotelsWeekdayandWeekend(customerType, firstDate, lastDate);
				break;
			case 2:
				findCheapBestRatedHotelRewardORRegular(customerType, firstDate, lastDate);
				break;
			case 3:
				findBestRatedHotelRewardORRegular(customerType, firstDate, lastDate);
				break;
			case 4:
				findCheapBestRatedHotelRewardStreams(firstDate, lastDate);
				break;
			case 5:
				break;
			default:
				System.out.println("Invalid choice, Choose again");
			}

		} while (choice != 5);
	}

	private static void findCheapBestRatedHotelRewardStreams(LocalDate firstDate, LocalDate lastDate) {

		Integer minimumRent = rentMapReward.entrySet().stream()
				.min((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue())).get().getValue();

		List<Hotel> listOfHotelsWithMinRent = rentMapReward.entrySet().stream()
				.filter(map -> map.getValue().equals(minimumRent)).map(map -> map.getKey())
				.collect(Collectors.toList());

		Hotel maxRatingHotel = listOfHotelsWithMinRent.stream().max((entry1, entry2) -> Integer
				.valueOf(entry1.getHotelRating()).compareTo((Integer) entry2.getHotelRating())).get();

		System.out.println(maxRatingHotel.getHotelName() + " , Rating is " + maxRatingHotel.getHotelRating()
				+ " with Total rates $" + minimumRent);

	}

	private static void initializeRentMaps(LocalDate firstDate, LocalDate lastDate) {

		DayOfWeek dayName;
		int rent = 0;
		LocalDate localFirstDate = firstDate;
		lastDate = lastDate.plusDays(1);
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
					rent = rent + h.getHotelWeekdayRateReward();
					break;
				case SATURDAY:
				case SUNDAY:
					rent = rent + h.getHotelWeekendRateReward();
					break;
				}
			}
			rentMapReward.put(h, rent);
		}
	}

	private static void findCheapHotelsWeekdayandWeekend(int customerType, LocalDate firstDate, LocalDate lastDate) {
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
				if (customerType == 1) {
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
				} else {
					switch (dayName) {
					case MONDAY:
					case TUESDAY:
					case WEDNESDAY:
					case THURSDAY:
					case FRIDAY:
						rent = rent + h.getHotelWeekdayRateReward();
						break;
					case SATURDAY:
					case SUNDAY:
						rent = rent + h.getHotelWeekendRateReward();
						break;
					}
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

	private static void findCheapBestRatedHotelRewardORRegular(int customerType, LocalDate firstDate,
			LocalDate lastDate) {
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
				if (customerType == 1) {
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
				} else {
					switch (dayName) {
					case MONDAY:
					case TUESDAY:
					case WEDNESDAY:
					case THURSDAY:
					case FRIDAY:
						rent = rent + h.getHotelWeekdayRateReward();
						break;
					case SATURDAY:
					case SUNDAY:
						rent = rent + h.getHotelWeekendRateReward();
						break;
					}
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

	private static void findBestRatedHotelRewardORRegular(int customerType, LocalDate firstDate, LocalDate lastDate) {
		DayOfWeek dayName;
		int rent = 0;
		int hotelRatingVar = 0;
		LocalDate localFirstDate = firstDate;
		lastDate = lastDate.plusDays(1);
		String hotelName = "";
		for (Hotel h : hotelsArrayList) {

			if (h.getHotelRating() > hotelRatingVar) {
				rent = 0;
				hotelRatingVar = h.getHotelRating();
				hotelName = h.getHotelName();
				localFirstDate = firstDate;

				for (localFirstDate = firstDate; localFirstDate
						.isBefore(lastDate); localFirstDate = localFirstDate.plusDays(1)) {
					dayName = localFirstDate.getDayOfWeek();
					if (customerType == 1) {
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
					} else {
						switch (dayName) {
						case MONDAY:
						case TUESDAY:
						case WEDNESDAY:
						case THURSDAY:
						case FRIDAY:
							rent = rent + h.getHotelWeekdayRateReward();
							break;
						case SATURDAY:
						case SUNDAY:
							rent = rent + h.getHotelWeekendRateReward();
							break;
						}
					}

				}
			}
		}

		System.out.println(hotelName + " & Total rates $" + rent);
	}
}
