package com.roland.movietheater_jdbc.service.Customer;

import com.roland.movietheater_jdbc.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();

    Customer createAccount(Customer customer) throws FailedToCreateAccountException;

    Customer upadteAccount(int customerId, Customer customer) throws FailedToUpdateAccountException, FailedToCreateAccountException;

    Customer getCustomerById(int customerId) throws FailedToFindAccountException;

    Customer userSignIn(String username, String password) throws FailedToFindAccountException;
}
