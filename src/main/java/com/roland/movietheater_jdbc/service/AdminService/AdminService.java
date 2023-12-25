package com.roland.movietheater_jdbc.service.AdminService;

        import com.roland.movietheater_jdbc.model.Admin;
        import com.roland.movietheater_jdbc.repository.AdminRepository.AdminRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {


    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin adminSignIn(String adminUsername, String adminPassword) throws AdminNotFoundException {
        return adminRepository.adminSignIn(adminUsername,adminPassword);
    }
}
