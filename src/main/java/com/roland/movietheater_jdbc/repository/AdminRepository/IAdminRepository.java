package com.roland.movietheater_jdbc.repository.AdminRepository;

import com.roland.movietheater_jdbc.model.Admin;
import com.roland.movietheater_jdbc.service.AdminService.AdminNotFoundException;

public interface IAdminRepository {

    Admin adminSignIn(String adminUsername, String adminPassword) throws AdminNotFoundException;
}
