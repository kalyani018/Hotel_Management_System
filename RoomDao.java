package hotelManagentSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hotelManagentSystem.model.Room;
import hotelManagentSystem.utility.DBconnection;

public class RoomDao implements RoomDaoInt {

	@Override
	public void addRoom(Room room) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "INSERT INTO rooms(room_type,price,status) VALUES(?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, room.getRoomType());
			ps.setDouble(2, room.getPrice());
			ps.setString(3, room.getStatus());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Room Added Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Room> viewRooms() {
		List<Room> roomList = new ArrayList<>();

		try {

			Connection con = DBconnection.getConnection();

			String query = "SELECT * FROM rooms";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Room room = new Room();

				room.setRoomId(rs.getInt("room_id"));
				room.setRoomType(rs.getString("room_type"));
				room.setPrice(rs.getDouble("price"));
				room.setStatus(rs.getString("status"));

				roomList.add(room);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roomList;
	}

	@Override
	public void updateRoom(Room room) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "UPDATE rooms SET room_type=?,price=?,status=? WHERE room_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, room.getRoomType());
			ps.setDouble(2, room.getPrice());
			ps.setString(3, room.getStatus());
			ps.setInt(4, room.getRoomId());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Room Updated Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteRoom(int roomId) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "DELETE FROM rooms WHERE room_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, roomId);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Room Deleted Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public double getRoomPrice(int roomId) {
		double price = 0;

		try {

			Connection con = DBconnection.getConnection();

			String query = "SELECT price FROM rooms WHERE room_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, roomId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				price = rs.getDouble("price");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return price;
	}

	@Override
	public boolean isRoomAvailable(int roomId) {
		boolean available = false;

		try {

			Connection con = DBconnection.getConnection();

			String query = "SELECT status FROM rooms WHERE room_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, roomId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String status = rs.getString("status");

				if (status.equalsIgnoreCase("Available")) {
					available = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return available;
	}

	@Override
	public void updateRoomStatus(int roomId, String status) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "UPDATE rooms SET status=? WHERE room_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, status);
			ps.setInt(2, roomId);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
