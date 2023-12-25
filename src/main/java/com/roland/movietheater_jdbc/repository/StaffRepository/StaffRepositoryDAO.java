package com.roland.movietheater_jdbc.repository.StaffRepository;

import com.roland.movietheater_jdbc.model.Staff;
import com.roland.movietheater_jdbc.service.CinemaService.FailedToFindCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.StaffService.FailedToCreateStaffInCinemaBranch;
import com.roland.movietheater_jdbc.service.StaffService.FailedToDeleteStaffInCinemaBranch;
import com.roland.movietheater_jdbc.service.StaffService.FailedToFindStaffInCinemaBranchException;
import com.roland.movietheater_jdbc.service.StaffService.FailedToUpdateStaffInCinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffRepositoryDAO implements IStaffRepositoryDAO {


    private static final String SQL_STATEMENT_TO_FIND_STAFF_IN_CINEMA =
            "select * from staff where cinema_id = ?";

    private static final String SQL_STATEMENT_TO_INSERT_STAFF_IN_CINEMA =
            "insert into staff (staff_fname, staff_lname, staff_phone, staff_address, staff_role, cinema_id) values (?,?,?,?,?,?)";

    private static final String SQL_STATEMENT_TO_DELETE_STAFF_IN_CINEMA =
            "delete from staff where staff_id =?";


    private static final String SQL_STATEMENT_TO_UPDATE_STAFF_IN_CINEMA =
            " update staff set staff_fname = ? , staff_lname = ? , staff_phone = ?, staff_address =?  , staff_role = ?, cinema_id = ?  where staff_id = ?";

    private static final String SQL_STATEMENT_TO_FIND_STAFF_IN_CINEMA_BRANCH_BY_ID =
            "select * from staff where cinema_id = ? and staff_id = ?";



    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Staff> findAllStaffInBranch(int cinemaId) {
        return jdbcTemplate.query(SQL_STATEMENT_TO_FIND_STAFF_IN_CINEMA, new StaffMapper(), cinemaId);
    }

    @Override
    public Staff createStaffInCinemaBranch(int cinemaId, Staff staff) throws FailedToCreateStaffInCinemaBranch {

        try {

            jdbcTemplate.update(SQL_STATEMENT_TO_INSERT_STAFF_IN_CINEMA,
                    staff.getStaffFirstName()
                    , staff.getStaffLastName()
                    , staff.getStaffPhone()
                    , staff.getStaffAddress()
                    , staff.getStaffRole()
                    , cinemaId);

            staff.setCinemaId(cinemaId);
            return staff;

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToCreateStaffInCinemaBranch(e, cinemaId, staff.getStaffId());
        }
    }


    @Override
    public String deleteStaffInCinemaBranch(int cinemaId, int staffId) throws FailedToDeleteStaffInCinemaBranch {
        try {


            jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_STAFF_IN_CINEMA, staffId);
            return "Staff: " + staffId + " has been removed from cinema branch:  " + cinemaId;

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToDeleteStaffInCinemaBranch(e, cinemaId, staffId);
        }
    }

    @Override
    public Staff updateStaffInCinemaBranch(int cinemaId, int staffId, Staff staff) throws FailedToUpdateStaffInCinemaBranch {

        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_UPDATE_STAFF_IN_CINEMA,
                    staff.getStaffFirstName(),
                    staff.getStaffLastName(),
                    staff.getStaffPhone(),
                    staff.getStaffAddress(),
                    staff.getStaffRole(),
                    cinemaId,
                    staffId);
            staff.setCinemaId(cinemaId);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToUpdateStaffInCinemaBranch(e, cinemaId, staffId);
        }

        return staff;
    }

    @Override
    public Staff getStaffCinemaBranchById(int cinemaId, int staffId) throws FailedToFindStaffInCinemaBranchException {
        try {
            Staff staff = jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_STAFF_IN_CINEMA_BRANCH_BY_ID, new StaffMapper(), cinemaId, staffId);
            return staff;
        } catch (Exception e) {
            throw new FailedToFindStaffInCinemaBranchException("Staff Not Found !");
        }
    }



}
