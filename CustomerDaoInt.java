package hotelManagentSystem.dao;

import java.util.List;

import hotelManagentSystem.model.Customer;

public interface CustomerDaoInt {

	void addCustomer(Customer customer);

    List<Customer> viewCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);
}
