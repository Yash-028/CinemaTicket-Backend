package com.roland.movietheater_jdbc.service.Customer;

public class FailedToUpdateAccountException extends  Exception{
    public FailedToUpdateAccountException(String msg) {
        super(msg);

    }
}
