package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.License;
import com.jaspreetflourmill.server.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/licenses")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;

    @GetMapping("/{id}")
    public ResponseEntity<HttpStatus> get(@PathVariable String id){
        try{
            License license = licenseService.getLicense(id).orElseThrow();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
