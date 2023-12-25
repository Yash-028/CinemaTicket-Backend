package com.roland.movietheater_jdbc.service.StaffService;

import com.roland.movietheater_jdbc.model.Staff;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToFindCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;

import java.util.List;

public interface IStaffService {
    List<Staff> findAllStaffInBranch(int cinemaId);

    Staff createStaffInCinemaBranch(int cinemaId, Staff staff) throws FailedToCreateStaffInCinemaBranch;

    String deleteStaffInCinemaBranch(int cinemaId, int staffId) throws FailedToDeleteStaffInCinemaBranch;

    Staff updateStaffInCinemaBranch(int cinemaId, int staffId, Staff staff) throws FailedToUpdateStaffInCinemaBranch;

    Staff getStaffInCinemaBranchById(int cinemaId, int staffId) throws FailedToFindStaffInCinemaBranchException;
}
