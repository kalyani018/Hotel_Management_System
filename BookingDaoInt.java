package hotelManagentSystem.dao;

import java.util.List;

import hotelManagentSystem.model.Booking;

public interface BookingDaoInt {
	
	void bookRoom(Booking booking);

    List<Booking> viewBookings();

    void checkoutRoom(int bookingId);
    
    int getRoomIdByBookingId(int bookingId);

}
