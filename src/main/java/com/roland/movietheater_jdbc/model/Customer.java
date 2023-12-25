package com.roland.movietheater_jdbc.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer {
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
