package com.roland.movietheater_jdbc.service.StaffService;

public class FailedToFindStaffInCinemaBranchException extends Exception {
    public FailedToFindStaffInCinemaBranchException(String message) {
        super(message);
    }
}
