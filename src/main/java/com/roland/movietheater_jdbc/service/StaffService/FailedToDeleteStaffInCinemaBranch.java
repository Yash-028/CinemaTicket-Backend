package com.roland.movietheater_jdbc.service.StaffService;

public class FailedToDeleteStaffInCinemaBranch extends Exception {
    public FailedToDeleteStaffInCinemaBranch(Throwable cause, int cinemaId, int staffId){
        super("Failed to delete staff: " + staffId + " In Cinema Branch: " + cinemaId);
    }
}
