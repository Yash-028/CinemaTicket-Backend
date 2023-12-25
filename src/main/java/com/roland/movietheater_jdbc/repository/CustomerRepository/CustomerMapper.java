package com.roland.movietheater_jdbc.repository.CustomerRepository;

import com.roland.movietheater_jdbc.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getInt("customer_id"));
        customer.setCustomerFirstName(resultSet.getString("customer_fname"));
        customer.setCustomerLastName(resultSet.getString("customer_lname"));
        customer.setCustomerPhone(resultSet.getString("customer_phone"));
        customer.setCustomerAddress(resultSet.getString("customer_address"));
        customer.setCustomerGender(resultSet.getString("customer_gender"));
        customer.setCustomerEmail(resultSet.getString("customer_email"));
        customer.setCustomerUsername(resultSet.getString("customer_username"));
        customer.setCustomerPassword(resultSet.getString("customer_password"));

        return customer;
    }
}
