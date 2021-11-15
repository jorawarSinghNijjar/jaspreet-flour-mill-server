package com.jaspreetflourmill.server.controller;

import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.User;

import com.jaspreetflourmill.server.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> list(){
        try {
            List<User> userList = userService.listAllUsers().orElseThrow();
            return new ResponseEntity<>(userList,HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable String id){
        try{
            User existUser = userService.getUser(id).orElseThrow();
            user.setId(id);
            User updatedUser = userService.saveUser(user).orElseThrow();
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<User> add(@RequestBody User user){
        try{
            User savedUser = userService.saveUser(user).orElseThrow();
            return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        userService.deleteUser(id);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String,String> data){
        try{
            User user = userService.findByEmailId(data.get("emailId")).orElseThrow();
            logger.info("Retrieved user: " + user.getId());
            return new ResponseEntity<>("Email Sent Successfully",HttpStatus.ACCEPTED);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reset-token")
    public ResponseEntity<User> resetToken(@RequestBody Map<String,String> data){
        try{
            String resetTokenInput = data.get("reset-token");

            User user = userService.getUserByToken(resetTokenInput).orElseThrow();
            userService.deleteResetToken(user);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);

        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
