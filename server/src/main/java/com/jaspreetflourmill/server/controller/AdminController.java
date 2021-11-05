package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Admin;

import com.jaspreetflourmill.server.model.User;
import com.jaspreetflourmill.server.service.AdminService;

import com.jaspreetflourmill.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<Admin>> list(){
        try {
            List<Admin> adminList = adminService.listAllAdmins().orElseThrow();
            return new ResponseEntity<>(adminList,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> get(@PathVariable String id){
        try{
            Admin admin = adminService.getAdmin(id).orElseThrow();
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(@RequestBody Admin admin, @PathVariable String id){
        try{
            Admin existAdmin = adminService.getAdmin(id).orElseThrow();
            admin.setId(id);
            adminService.saveAdmin(admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public @ResponseBody ResponseEntity<Admin> add(@RequestBody Admin admin){
        try{
            Admin savedAdmin = adminService.saveAdmin(admin).orElseThrow();
            return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        try {
            adminService.deleteAdmin(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
