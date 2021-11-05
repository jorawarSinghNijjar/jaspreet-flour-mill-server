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
            List<Admin> adminList = adminService.listAllAdmins().orElseThrow(EntityNotFoundException::new);
            return new ResponseEntity<>(adminList,HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Admin admin, @PathVariable String id){
        try{
            Admin existAdmin = adminService.getAdmin(id).orElseThrow();
            admin.setId(id);
            adminService.saveAdmin(admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> add(@RequestBody Admin admin){
        try{
            System.out.println(admin.toString());
            adminService.saveAdmin(admin);
            return new ResponseEntity<String>("Admin Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        adminService.deleteAdmin(id);
    }
}
