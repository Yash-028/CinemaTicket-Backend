package com.roland.movietheater_jdbc.controller.customer;

import com.roland.movietheater_jdbc.model.Customer;
import com.roland.movietheater_jdbc.service.Customer.CustomerService;
import com.roland.movietheater_jdbc.service.Customer.FailedToCreateAccountException;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import com.roland.movietheater_jdbc.service.Customer.FailedToUpdateAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/admin/customers")
    public ResponseEntity getAllCustomersForAdmin(){
        List<Customer> customerList = customerService.getAllCustomers();
        List<CustomerApiResponseForAdmin> responseList = buildResponseListForAdmin(customerList);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);

    }

    @GetMapping("customers/{customerId}")
    public ResponseEntity getCustomerById(@PathVariable("customerId") int customerId){
        try {
            Customer customer = customerService.getCustomerById(customerId);
            CustomerApiResponseForUser response = getCustomerApiResponseForUser(customer);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToFindAccountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/signIn/usernames/{username}/passwords/{password}")
    public ResponseEntity userSignIn(@PathVariable("username") String username, @PathVariable("password") String password) {
        try {
            Customer admin = customerService.userSignIn(username, password);
            CustomerApiResponseForUser response = getCustomerApiResponseForUser(admin);
            return   ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToFindAccountException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }

    }

    @PostMapping("/createAccount")
    public ResponseEntity createAccount(@RequestBody CustomerApiRequestForUser request){
        try {
            Customer customer = customerService.createAccount(getCustomer(request));
            CustomerApiResponseForUser response = getCustomerApiResponseForUser(customer);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToCreateAccountException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping("/customer/{customerId}")
    public ResponseEntity updateAccount(@PathVariable("customerId") int customerId ,@RequestBody CustomerApiRequestForUser request){
        try {
            Customer customer = customerService.upadteAccount(customerId,getCustomer(request));
            CustomerApiResponseForUser response = getCustomerApiResponseForUser(customer);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToUpdateAccountException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


    private Customer getCustomer(CustomerApiRequestForUser request) {
        return  new Customer().builder()
                .customerFirstName(request.getCustomerFirstName())
                .customerLastName(request.getCustomerLastName())
                .customerPhone(request.getCustomerPhone())
                .customerEmail(request.getCustomerEmail())
                .customerGender(request.getCustomerGender())
                .customerAddress(request.getCustomerAddress())
                .customerUsername(request.getCustomerUsername())
                .customerPassword(request.getCustomerPassword())
                .build();
    }

    private List<CustomerApiResponseForAdmin> buildResponseListForAdmin(List<Customer> customerList) {
        List<CustomerApiResponseForAdmin> responseList = new ArrayList<>();
        for (Customer customer : customerList) {
            responseList.add(getCustomerApiResponseForAdmin(customer));
        }

        return responseList;
    }

    private CustomerApiResponseForAdmin getCustomerApiResponseForAdmin(Customer customer) {
        return new CustomerApiResponseForAdmin().builder()
                .customerId(customer.getCustomerId())
                .customerFirstName(customer.getCustomerFirstName())
                .customerLastName(customer.getCustomerLastName())
                .customerPhone(customer.getCustomerPhone())
                .customerEmail(customer.getCustomerEmail())
                .customerGender(customer.getCustomerGender())
                .customerAddress(customer.getCustomerAddress())
                .customerUsername(customer.getCustomerUsername())
                .build();

        // We will not be passing The password to the admin

    }




    private CustomerApiResponseForUser getCustomerApiResponseForUser(Customer customer) {
        return new CustomerApiResponseForUser().builder()
                .customerFirstName(customer.getCustomerFirstName())
                .customerLastName(customer.getCustomerLastName())
                .customerPhone(customer.getCustomerPhone())
                .customerEmail(customer.getCustomerEmail())
                .customerGender(customer.getCustomerGender())
                .customerAddress(customer.getCustomerAddress())
                .customerUsername(customer.getCustomerUsername())
                .customerPassword(customer.getCustomerPassword())
                .customerId(customer.getCustomerId())
                .build();

        // We will will be passing The password to the user

    }
}
