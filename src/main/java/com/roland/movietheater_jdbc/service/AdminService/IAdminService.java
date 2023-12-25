package com.roland.movietheater_jdbc.service.AdminService;

        import com.roland.movietheater_jdbc.model.Admin;

public interface IAdminService {
    Admin adminSignIn(String adminUsername, String adminPassword) throws AdminNotFoundException;
}
