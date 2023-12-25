package com.roland.movietheater_jdbc.service.Customer;

public class FailedToFindAccountException extends Exception {
    public FailedToFindAccountException(String msg) {
        super(msg);

    }
}
