package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> listAllAdmins(){
        return adminRepository.findAll();
    }

    public void saveAdmin(Admin admin){
        adminRepository.save(admin);
    }

    public Admin getAdmin(String id){
        return adminRepository.findById(id).get();
    }

    public void deleteAdmin(String id){
        adminRepository.deleteById(id);
    }
}
