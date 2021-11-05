package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.model.User;
import com.jaspreetflourmill.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    public Optional<List<User>> listAllUsers(){
        return Optional.ofNullable(userRepository.findAll());
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public Optional<User> getUser(String id){
        return userRepository.getUser(id);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
