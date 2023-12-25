package com.roland.movietheater_jdbc.service.StaffService;

public class FailedToUpdateStaffInCinemaBranch extends Exception {
    public FailedToUpdateStaffInCinemaBranch(Throwable cause, int cinemaId, int staffId){
        super("Failed to update staff: " + staffId + " In Cinema Branch: " + cinemaId);
    }
}
