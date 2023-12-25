package com.roland.movietheater_jdbc.service.Customer;

import com.roland.movietheater_jdbc.model.Customer;
import com.roland.movietheater_jdbc.repository.CustomerRepository.CustomerRepositoryDAO;
import com.roland.movietheater_jdbc.repository.CustomerRepository.ICustomerRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private ICustomerRepositoryDAO customerRepository;

    @Autowired
    public CustomerService(CustomerRepositoryDAO customerService) {
        this.customerRepository = customerService;
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer createAccount(Customer customer) throws FailedToCreateAccountException {
        return customerRepository.createAccount(customer);
    }

    @Override
    public Customer upadteAccount(int customerId, Customer request) throws FailedToUpdateAccountException {
        return customerRepository.updateAccount(customerId, request);
    }

    @Override
    public Customer getCustomerById(int customerId) throws FailedToFindAccountException {
        return customerRepository.getCustomerById(customerId);
    }

    @Override
    public Customer userSignIn(String username, String password) throws FailedToFindAccountException {
        return customerRepository.userSignIn(username,password);
    }
}
