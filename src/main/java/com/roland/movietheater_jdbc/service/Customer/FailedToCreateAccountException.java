package com.roland.movietheater_jdbc.service.Customer;

public class FailedToCreateAccountException extends  Exception {
    public FailedToCreateAccountException(String msg) {
        super(msg);

    }
}
