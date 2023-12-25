package com.roland.movietheater_jdbc.controller.Admin;


import com.roland.movietheater_jdbc.model.Admin;
import com.roland.movietheater_jdbc.service.AdminService.AdminNotFoundException;
import com.roland.movietheater_jdbc.service.AdminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping("/signIn/adminUsernames/{adminUsername}/adminPasswords/{adminPassword}")
    public ResponseEntity adminSignIn(@PathVariable("adminUsername") String adminUsername, @PathVariable("adminPassword") String adminPassword) {
        try {
            Admin admin = adminService.adminSignIn(adminUsername, adminPassword);
            AdminApiResponse response = buildAdminApiResponse(admin);
            return   ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AdminNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }

    }

    private AdminApiResponse buildAdminApiResponse(Admin admin) {
        return new AdminApiResponse().builder()
                .adminId(admin.getAdminId())
                .adminUserName(admin.getAdminUserName())
                .build();
    }


}
