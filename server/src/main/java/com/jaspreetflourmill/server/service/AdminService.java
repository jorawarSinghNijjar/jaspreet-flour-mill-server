package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.model.User;
import com.jaspreetflourmill.server.repository.AdminRepository;
import com.jaspreetflourmill.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<List<Admin>> listAllAdmins(){
        return Optional.ofNullable(adminRepository.findAll());
    }

    public Optional<Admin> saveAdmin(Admin admin){
       return Optional.of(adminRepository.save(admin));
    }

    public Optional<Admin> getAdmin(String userId){
        Optional<User> user = userRepository.getUser(userId);
        return adminRepository.findByUser(user.get());
    }

    public void deleteAdmin(String id){
        adminRepository.deleteById(id);
    }
}
