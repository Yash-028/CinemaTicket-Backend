package com.roland.movietheater_jdbc.repository.StaffRepository;

import com.roland.movietheater_jdbc.model.Staff;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToFindCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.StaffService.FailedToCreateStaffInCinemaBranch;
import com.roland.movietheater_jdbc.service.StaffService.FailedToDeleteStaffInCinemaBranch;
import com.roland.movietheater_jdbc.service.StaffService.FailedToFindStaffInCinemaBranchException;
import com.roland.movietheater_jdbc.service.StaffService.FailedToUpdateStaffInCinemaBranch;

import java.util.List;

public interface IStaffRepositoryDAO {

    List<Staff> findAllStaffInBranch(int cinemaId);

    Staff createStaffInCinemaBranch(int cinemaId, Staff staff) throws FailedToCreateStaffInCinemaBranch;

    String deleteStaffInCinemaBranch(int cinemaId, int staffId) throws FailedToDeleteStaffInCinemaBranch;

    Staff updateStaffInCinemaBranch(int cinemaId, int staffId, Staff staff) throws FailedToUpdateStaffInCinemaBranch;

    Staff getStaffCinemaBranchById(int cinemaId, int staffId) throws FailedToFindStaffInCinemaBranchException;


}
