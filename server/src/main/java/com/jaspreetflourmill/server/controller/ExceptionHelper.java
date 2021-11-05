//package com.jaspreetflourmill.server.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.util.NoSuchElementException;
//
//@ControllerAdvice
//public class ExceptionHelper {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);
//
//
//    @ExceptionHandler(value = {NoSuchElementException.class})
//
//    public ResponseEntity<Object> handleInvalidInputException(NoSuchElementException ex) {
//
//        logger.error("Invalid Input Exception: ", ex.getMessage());
//
//        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//
//    }
//
//
//    @ExceptionHandler(value = {HttpClientErrorException.Unauthorized.class})
//
//    public ResponseEntity<Object> handleUnauthorizedException( HttpClientErrorException.Unauthorized ex) {
//
//        logger.error("Unauthorized Exception: ", ex.getMessage());
//
//        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//
//    }
//
//
//    @ExceptionHandler(value = {Exception.class})
//
//
//    public ResponseEntity<Object> handleException(Exception ex) {
//
//        logger.error("Exception: ", ex.getMessage());
//
//        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//}
