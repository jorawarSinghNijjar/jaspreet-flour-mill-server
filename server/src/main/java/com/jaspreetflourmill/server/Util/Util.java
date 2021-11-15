package com.jaspreetflourmill.server.Util;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Random;

public class Util {
    public static String generateOTP(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
