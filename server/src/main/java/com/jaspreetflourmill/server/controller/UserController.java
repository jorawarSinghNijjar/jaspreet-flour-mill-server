package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.User;

import com.jaspreetflourmill.server.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> list(){
        try {
            List<User> userList = userService.listAllUsers().orElseThrow(EntityNotFoundException::new);
            return new ResponseEntity<>(userList,HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id){
        try{
            User user = userService.getUser(id).orElseThrow();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable String id){
        try{
            User existUser = userService.getUser(id).orElseThrow();
            user.setId(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public @ResponseBody ResponseEntity<String> add(@RequestBody User user){
        try{
            System.out.println(user.toString());
            userService.saveUser(user);
            return new ResponseEntity<String>("User Registered Successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        userService.deleteUser(id);
    }
}
