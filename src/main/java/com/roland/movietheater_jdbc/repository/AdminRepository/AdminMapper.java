package com.roland.movietheater_jdbc.repository.AdminRepository;

import com.roland.movietheater_jdbc.model.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper  implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminId(resultSet.getInt("admin_id"));
        admin.setFirstName(resultSet.getString("admin_fname"));
        admin.setLastName(resultSet.getString("admin_lname"));
        admin.setAdminPassword(resultSet.getString("admin_password"));
        admin.setAdminUserName(resultSet.getString("admin_username"));
        return admin;
    }
}
