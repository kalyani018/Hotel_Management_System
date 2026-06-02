package hotelManagentSystem.controller;

import java.util.List;
import java.util.Scanner;

import hotelManagentSystem.dao.BookingDao;
import hotelManagentSystem.dao.CustomerDao;
import hotelManagentSystem.dao.RoomDao;
import hotelManagentSystem.model.Booking;
import hotelManagentSystem.model.Customer;
import hotelManagentSystem.model.Room;

public class HotelManagentController {

	public static void main(String[] args) {
		CustomerDao customerDao = new CustomerDao();
		RoomDao roomDao = new RoomDao();
		BookingDao bookingDao = new BookingDao();

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n===== HOTEL MANAGEMENT SYSTEM =====");

			System.out.println("1. Add Customer");
			System.out.println("2. View Customers");
			System.out.println("3. Add Room");
			System.out.println("4. View Rooms");
			System.out.println("5. Book Room");
			System.out.println("6. View Bookings");
			System.out.println("7. Checkout");
			System.out.println("8. Exit");

			System.out.print("Enter Choice : ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:

				sc.nextLine();

				Customer customer = new Customer();

				System.out.print("Enter Name : ");
				customer.setName(sc.nextLine());

				System.out.print("Enter Phone : ");
				customer.setPhone(sc.nextLine());

				System.out.print("Enter Address : ");
				customer.setAddress(sc.nextLine());

				customerDao.addCustomer(customer);

				break;

			case 2:

				List<Customer> customers = customerDao.viewCustomers();

				for (Customer c : customers) {

					System.out
							.println(c.getCustomerId() + " " + c.getName() + " " + c.getPhone() + " " + c.getAddress());
				}

				break;

			case 3:

				sc.nextLine();

				Room room = new Room();

				System.out.print("Enter Room Type : ");
				room.setRoomType(sc.nextLine());

				System.out.print("Enter Price : ");
				room.setPrice(sc.nextDouble());

				sc.nextLine();

				System.out.print("Enter Status : ");
				room.setStatus(sc.nextLine());

				roomDao.addRoom(room);

				break;

			case 4:

				List<Room> rooms = roomDao.viewRooms();

				for (Room r : rooms) {

					System.out
							.println(r.getRoomId() + " " + r.getRoomType() + " " + r.getPrice() + " " + r.getStatus());
				}

				break;

			case 5:

				Booking booking = new Booking();

				System.out.print("Enter Customer Id : ");
				booking.setCustomerId(sc.nextInt());

				System.out.print("Enter Room Id : ");
				booking.setRoomId(sc.nextInt());

				if (!roomDao.isRoomAvailable(booking.getRoomId())) {

					System.out.println("Room Already Booked");

					break;
				}

				System.out.print("Enter Number Of Days : ");
				booking.setDays(sc.nextInt());

				double roomPrice = roomDao.getRoomPrice(booking.getRoomId());

				double totalBill = roomPrice * booking.getDays();

				booking.setTotalBill(totalBill);

				System.out.println("Total Bill : " + totalBill);

				bookingDao.bookRoom(booking);

				roomDao.updateRoomStatus(booking.getRoomId(), "Booked");

				break;

			case 6:

				List<Booking> bookings = bookingDao.viewBookings();

				for (Booking b : bookings) {

					System.out.println(b.getBookingId() + " " + b.getCustomerId() + " " + b.getRoomId() + " "
							+ b.getDays() + " " + b.getTotalBill());
				}

				break;

			case 7:

				System.out.print("Enter Booking Id : ");

				int bookingId = sc.nextInt();

				int roomId = bookingDao.getRoomIdByBookingId(bookingId);

				bookingDao.checkoutRoom(bookingId);

				roomDao.updateRoomStatus(roomId, "Available");

				System.out.println("Checkout Successful");

				break;

			case 8:

				System.out.println("********Thank You...**********8");

				sc.close();

				System.exit(0);

				break;

			default:

				System.out.println("Invalid Choice");

			}
		}

	}

}
