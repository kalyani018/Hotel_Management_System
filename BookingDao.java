package hotelManagentSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hotelManagentSystem.model.Booking;
import hotelManagentSystem.utility.DBconnection;

public class BookingDao implements BookingDaoInt {

	@Override
	public void bookRoom(Booking booking) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "INSERT INTO bookings(customer_id,room_id,days,total_bill) VALUES(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, booking.getCustomerId());
			ps.setInt(2, booking.getRoomId());
			ps.setInt(3, booking.getDays());
			ps.setDouble(4, booking.getTotalBill());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Room Booked Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Booking> viewBookings() {
		List<Booking> bookingList = new ArrayList<>();

		try {

			Connection con = DBconnection.getConnection();

			String query = "SELECT * FROM bookings";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Booking booking = new Booking();

				booking.setBookingId(rs.getInt("booking_id"));

				booking.setCustomerId(rs.getInt("customer_id"));

				booking.setRoomId(rs.getInt("room_id"));

				booking.setDays(rs.getInt("days"));

				booking.setTotalBill(rs.getDouble("total_bill"));

				bookingList.add(booking);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookingList;
	}

	@Override
	public void checkoutRoom(int bookingId) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "DELETE FROM bookings WHERE booking_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, bookingId);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Checkout Successful");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public int getRoomIdByBookingId(int bookingId) {
		int roomId = 0;

		try {

			Connection con = DBconnection.getConnection();

			String query = "SELECT room_id FROM bookings WHERE booking_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, bookingId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				roomId = rs.getInt("room_id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roomId;
	}

}
