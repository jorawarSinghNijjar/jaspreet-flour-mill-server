package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.CustomerAccount;
import com.jaspreetflourmill.server.model.License;
import com.jaspreetflourmill.server.repository.AdminRepository;
import com.jaspreetflourmill.server.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;

    public Optional<License> getLicense(String id){
        return licenseRepository.findById(id);
    }
}
