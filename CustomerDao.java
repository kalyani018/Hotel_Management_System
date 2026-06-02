package hotelManagentSystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hotelManagentSystem.model.Customer;
import hotelManagentSystem.utility.DBconnection;

public class CustomerDao implements CustomerDaoInt {

	@Override
	public void addCustomer(Customer customer) {
		try {
			Connection con = DBconnection.getConnection();

			String query = "insert into customers(name,phone,address) values(?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, customer.getName());
			ps.setString(2, customer.getPhone());
			ps.setString(3, customer.getAddress());

			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("Customer added successfully ");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	public List<Customer> viewCustomers() {
		List<Customer> customerList = new ArrayList<>();
		try {
			Connection con = DBconnection.getConnection();

			String query = "select * from customers";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Customer c = new Customer();

				c.setCustomerId(rs.getInt("customer_id"));

				c.setName(rs.getString("name"));

				c.setPhone(rs.getString("phone"));

				c.setAddress(rs.getString("address"));

				customerList.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return customerList;
	}

	@Override
	public void updateCustomer(Customer customer) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "UPDATE customers SET name=?, phone=?, address=? WHERE customer_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, customer.getName());
			ps.setString(2, customer.getPhone());
			ps.setString(3, customer.getAddress());
			ps.setInt(4, customer.getCustomerId());

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Customer Updated Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCustomer(int customerId) {
		try {

			Connection con = DBconnection.getConnection();

			String query = "DELETE FROM customers WHERE customer_id=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1, customerId);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Customer Deleted Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
