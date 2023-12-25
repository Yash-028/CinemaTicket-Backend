package com.roland.movietheater_jdbc.repository.CustomerRepository;

import com.roland.movietheater_jdbc.model.Customer;
import com.roland.movietheater_jdbc.service.Customer.FailedToCreateAccountException;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import com.roland.movietheater_jdbc.service.Customer.FailedToUpdateAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryDAO implements ICustomerRepositoryDAO {

    private static final String SQL_STATEMENT_TO_FIND_ALL_CUSTOMERS = "select * from customer";
    private static final String SQL_STATEMENT_TO_CREATE_ACCOUNT_FOR_CUSTOMER =
            "Insert into customer (customer_fname , customer_lname, customer_phone, customer_email, customer_address, customer_gender, customer_username, customer_password) values (?,?,?,?,?,?,?,?)";

    private static final String SQL_STATEMENT_TO_UPDATE_ACCOUNT_FOR_CUSTOMER =
            "update customer set customer_fname = ? , customer_lname = ?, customer_phone = ? , customer_email = ? ," +
                    "customer_address = ? ,customer_gender= ?, customer_username = ? , customer_password = ? where customer_id = ?";

    private static final String SQL_STATEMENT_TO_FIND_CUSTOMER_BY_ID = "SELECT * FROM customer where customer_id = ?";

    private static final String SQL_STATEMENT_TO_FIND_CUSTOMER_SIGNING_IN_BY_USERNAME_AND_PASSWORD=
            "select * from customer where customer_username = ? AND customer_password = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_ALL_CUSTOMERS, new CustomerMapper());
        return customerList;
    }

    @Override
    public Customer getCustomerById(int customerId) throws FailedToFindAccountException {
        try {
            Customer customer = jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_CUSTOMER_BY_ID, new CustomerMapper(), customerId);
            return customer;
        } catch (Exception e) {
            throw new FailedToFindAccountException("User Not Found");
        }
    }

    @Override
    public Customer userSignIn(String username, String password) throws FailedToFindAccountException {
        try {
            return jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_CUSTOMER_SIGNING_IN_BY_USERNAME_AND_PASSWORD
                    ,new CustomerMapper()
                    ,username
                    ,password);
        } catch (DataAccessException e) {
            throw new FailedToFindAccountException("User Not Found ! Wrong Username Or Password  ");
        }
    }

    @Override
    public Customer createAccount(Customer customer) throws FailedToCreateAccountException {

        if (IsUserNameTaken(customer.getCustomerUsername()))
            throw new FailedToCreateAccountException("Username already Exist ! Try Again ! ");

        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_CREATE_ACCOUNT_FOR_CUSTOMER
                    , customer.getCustomerFirstName()
                    , customer.getCustomerLastName()
                    , customer.getCustomerPhone()
                    , customer.getCustomerEmail()
                    , customer.getCustomerAddress()
                    , customer.getCustomerGender()
                    , customer.getCustomerUsername()
                    , customer.getCustomerPassword());
            return customer;
        } catch (DataAccessException e) {
            throw new FailedToCreateAccountException("Failed To Create Account ! Please Try Again");
        }
    }


    @Override
    public Customer updateAccount(int customerId, Customer customer) throws FailedToUpdateAccountException {

        if (IsUserNameTaken(customer.getCustomerUsername()))
            throw new FailedToUpdateAccountException("Username already Exist ! Try Again ! ");

        try {
            System.out.println(customer);
            jdbcTemplate.update(SQL_STATEMENT_TO_UPDATE_ACCOUNT_FOR_CUSTOMER
                    , customer.getCustomerFirstName()
                    , customer.getCustomerLastName()
                    , customer.getCustomerPhone()
                    , customer.getCustomerEmail()
                    , customer.getCustomerAddress()
                    , customer.getCustomerGender()
                    , customer.getCustomerUsername()
                    , customer.getCustomerPassword()
                    , customerId);
            return customer;
        } catch (DataAccessException e) {
            throw new FailedToUpdateAccountException("Failed To Update Your Account ! Please Try Again");
        }
    }



    private boolean IsUserNameTaken(String customerUsername) {
        List<Customer> customerList = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_ALL_CUSTOMERS, new CustomerMapper());
        for (Customer customer : customerList)
            if (customer.getCustomerUsername().equalsIgnoreCase(customerUsername))
                return true;
        return false;
    }
}
