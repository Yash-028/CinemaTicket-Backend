package com.roland.movietheater_jdbc.controller.staff;

import com.roland.movietheater_jdbc.model.Staff;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToFindCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.StaffService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @GetMapping("/admin/cinemas/{cinemaId}/staffs")
    public ResponseEntity getAllStaffInCinemaBranch(@PathVariable("cinemaId") int cinemaId) {
        List<Staff> staffList = staffService.findAllStaffInBranch(cinemaId);
        List<StaffApiResponseForAdmin> responseList = buildResponse(staffList);
       return ResponseEntity.status(HttpStatus.OK).body(responseList);

    }

    @GetMapping("/admin/cinemas/{cinemaId}/staffs/{staffId}")
    public ResponseEntity getStaffInCinemaBranchById(@PathVariable("cinemaId") int cinemaId , @PathVariable("staffId") int staffId){
        try {
            Staff staff = staffService.getStaffInCinemaBranchById(cinemaId,staffId);
            StaffApiResponseForAdmin response = getStaffApiRepsonse(staff);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToFindStaffInCinemaBranchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/admin/cinemas/{cinemaId}/staffs")
    public ResponseEntity createStaffInCinemaBranch(@PathVariable("cinemaId") int cinemaId, @RequestBody StaffApiRequestForAdmin request){
        try {
            Staff staff = staffService.createStaffInCinemaBranch(cinemaId,getStaff(request));
            StaffApiResponseForAdmin response = getStaffApiRepsonse(staff);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToCreateStaffInCinemaBranch e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/admin/cinemas/{cinemaId}/staffs/{staffId}")
    public ResponseEntity createStaffInCinemaBranch(@PathVariable("cinemaId") int cinemaId, @PathVariable("staffId") int staffId){
        try {
            String staffIdDeleted = staffService.deleteStaffInCinemaBranch(cinemaId,staffId);
            return  ResponseEntity.status(HttpStatus.OK).body(staffIdDeleted);
        } catch (FailedToDeleteStaffInCinemaBranch e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/admin/cinemas/{cinemaId}/staffs/{staffId}")
    public ResponseEntity updateStaffInCinemaBranch(@PathVariable("cinemaId") int cinemaId, @PathVariable("staffId") int staffId, @RequestBody StaffApiRequestForAdmin request){
        try {
            Staff staff = staffService.updateStaffInCinemaBranch(cinemaId, staffId, getStaff(request));
            StaffApiResponseForAdmin response = getStaffApiRepsonse(staff);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToUpdateStaffInCinemaBranch e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }




    private Staff getStaff(StaffApiRequestForAdmin request) {
        return Staff.builder()
                .staffFirstName(request.getStaffFirstName())
                .staffLastName(request.getStaffLastName())
                .staffPhone(request.getStaffPhone())
                .staffAddress(request.getStaffAddress())
                .staffRole(request.getStaffRole())
                .build();
    }

    private List<StaffApiResponseForAdmin> buildResponse(List<Staff> staffList) {
        List<StaffApiResponseForAdmin> responseList = new ArrayList<>();
        for(Staff staff: staffList){
            responseList.add(getStaffApiRepsonse(staff));
        }
        return  responseList;
    }

    private StaffApiResponseForAdmin getStaffApiRepsonse(Staff staff) {
        return StaffApiResponseForAdmin.builder()
                .staffId(staff.getStaffId())
                .staffFirstName(staff.getStaffFirstName())
                .staffLastName(staff.getStaffLastName())
                .staffPhone(staff.getStaffPhone())
                .staffAddress(staff.getStaffAddress())
                .cinemaId(staff.getCinemaId())
                .staffRole(staff.getStaffRole())
                .build();
    }


}
