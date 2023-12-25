package com.roland.movietheater_jdbc.repository.StaffRepository;

import com.roland.movietheater_jdbc.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet resultSet, int i) throws SQLException {
       Staff staff = new Staff();
       staff.setStaffId(resultSet.getInt("staff_id"));
       staff.setCinemaId(resultSet.getInt("cinema_id"));
       staff.setStaffFirstName(resultSet.getString("staff_fname"));
       staff.setStaffLastName(resultSet.getString("staff_lname"));
       staff.setStaffPhone(resultSet.getString("staff_phone"));
       staff.setStaffAddress(resultSet.getString("staff_address"));
       staff.setStaffRole(resultSet.getString("staff_role"));

       return staff;
    }
}
