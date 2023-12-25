package com.roland.movietheater_jdbc.controller.customer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerApiResponseForUser {

    private  int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerEmail;
    private String customerGender;
    private String customerAddress;
    private String customerUsername;
    private String customerPassword;
}
