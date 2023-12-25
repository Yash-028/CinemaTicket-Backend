package com.roland.movietheater_jdbc.repository.CustomerRepository;

import com.roland.movietheater_jdbc.model.Customer;
import com.roland.movietheater_jdbc.service.Customer.FailedToCreateAccountException;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import com.roland.movietheater_jdbc.service.Customer.FailedToUpdateAccountException;

import java.util.List;

public interface ICustomerRepositoryDAO {

    List<Customer> getAllCustomers();

    Customer createAccount(Customer customer) throws FailedToCreateAccountException;

    Customer updateAccount(int customerId, Customer customer) throws FailedToUpdateAccountException;

    Customer getCustomerById(int customerId) throws FailedToFindAccountException;

    Customer userSignIn(String username, String password) throws FailedToFindAccountException;
}
