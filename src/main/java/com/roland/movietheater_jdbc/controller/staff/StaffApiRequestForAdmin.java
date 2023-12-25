package com.roland.movietheater_jdbc.controller.staff;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffApiRequestForAdmin {

    private String staffFirstName;
    private String staffLastName;
    private String staffPhone;
    private String staffAddress;
    private String staffRole;

}
