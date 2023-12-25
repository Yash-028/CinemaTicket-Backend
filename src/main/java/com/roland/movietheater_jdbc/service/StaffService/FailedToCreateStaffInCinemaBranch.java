package com.roland.movietheater_jdbc.service.StaffService;

public class FailedToCreateStaffInCinemaBranch extends Exception {
    public FailedToCreateStaffInCinemaBranch(Throwable cause, int cinemaId, int staffId){
        super("Failed to Create staff: " + staffId + " In Cinema Branch: " + cinemaId);
    }
}
