package com.jaspreetflourmill.server.service;

import com.jaspreetflourmill.server.Util.Util;
import com.jaspreetflourmill.server.model.Admin;
import com.jaspreetflourmill.server.model.Employee;
import com.jaspreetflourmill.server.model.User;
import com.jaspreetflourmill.server.repository.AdminRepository;
import com.jaspreetflourmill.server.repository.EmployeeRepository;
import com.jaspreetflourmill.server.repository.UserRepository;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public Optional<List<User>> listAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    public Optional<User> saveUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> getUser(String id) {
        return userRepository.getUser(id);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    // Find admin or employee and call send method
    public Optional<User> findByEmailId(String emailId) {
        Optional<Admin> admin = adminRepository.findByEmailId(emailId);
        if (admin.isPresent()) {
            getUser(admin.get().getUser().getId()).ifPresent((user -> {
                try {
                    sendResetToken(admin.get().getEmailId(), user);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }));

            return getUser(admin.get().getUser().getId());

        } else {
            Optional<Employee> employee = employeeRepository.findByEmailId(emailId);
            if (employee.isPresent()) {

                getUser(employee.get().getUser().getId()).ifPresent((user) -> {
                    try {
                        sendResetToken(employee.get().getEmailId(), user);
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                });

                return getUser(employee.get().getUser().getId());
            }
        }
        return Optional.empty();
    }

    // Sends reset token to user email and saves reset token in users table DB
    public void sendResetToken(String emailId, User user) throws IOException {
        // Should be replaced by clients email id ( It has to be verified from Twilio)
        Email from = new Email("niajax1469@gmail.com");
        String subject = "Reset Token for: " + user.getId();
        Email to = new Email(emailId);
        String generatedResetToken = Util.generateOTP();
        Content content = new Content("text/plain", "Your Reset Token is : " + generatedResetToken);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());

            if (response.getStatusCode() == HttpStatus.SC_ACCEPTED) {
                user.setResetToken(generatedResetToken);
                saveUser(user);
            }
        } catch (IOException ex) {
            throw ex;
        }
    }

    public Optional<User> getUserByToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    public void deleteResetToken(User user){
        user.setResetToken(null);
        userRepository.save(user);
    }
}
