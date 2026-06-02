package hotelManagentSystem.dao;

import java.util.List;

import hotelManagentSystem.model.Room;

public interface RoomDaoInt {
	void addRoom(Room room);

	List<Room> viewRooms();

	void updateRoom(Room room);

	void deleteRoom(int roomId);
	
	double getRoomPrice(int roomId);
	
	boolean isRoomAvailable(int roomId);
	
	void updateRoomStatus(int roomId, String status);
	

}
