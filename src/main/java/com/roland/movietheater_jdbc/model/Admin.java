package com.roland.movietheater_jdbc.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {

    private int adminId;
    private String firstName;
    private String lastName;
    private String adminUserName;
    private String adminPassword;
}
