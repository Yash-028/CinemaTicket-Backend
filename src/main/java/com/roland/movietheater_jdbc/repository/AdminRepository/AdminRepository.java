package com.roland.movietheater_jdbc.repository.AdminRepository;

import com.roland.movietheater_jdbc.model.Admin;
import com.roland.movietheater_jdbc.service.AdminService.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository implements  IAdminRepository {

    private static final String SQL_STATEMENT_TO_FIND_ADMIN_BY_PASSWORD =
            "SELECT * FROM admins WHERE admin_username = ? AND admin_password = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Admin adminSignIn(String adminUsername, String adminPassword) throws AdminNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_ADMIN_BY_PASSWORD
                    ,new AdminMapper()
                    ,adminUsername
                    ,adminPassword);
        } catch (DataAccessException e) {
           throw new AdminNotFoundException("Admin Not Found ! Wrong Username Or Password  ");
        }
    }
}
