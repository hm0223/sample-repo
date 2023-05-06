package com.hm;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * TimeDiffApp.
 *
 * @author huwenfeng
 */
@Slf4j
public class TimeDiffApp {
    public static void main(String[] args) {

        double sum = 0;
        while (true) {
            try {
                System.out.println(">>> Please Input start and end time (HH:mm:ss) use ' ; ' split");
                log.info(">>> Please Input start and end time (HH:mm:ss) use ' ; ' split");
                Scanner sc = new Scanner(System.in);
                String next = sc.next();
                if ("exit".equalsIgnoreCase(next)) {
                    break;
                }

                String[] split = next.split(";");
                LocalTime startTime = LocalTime.parse(split[0]);
                LocalTime endTime = LocalTime.parse(split[1]);
                Duration between = Duration.between(startTime, endTime);
                double perHour = Math.abs(between.getSeconds() / 3600.0) - 1;
                System.out.println(">>> per diff is: " + perHour + " hour.");
                log.info(">>> per diff is: " + perHour + " hour.");
                sum += perHour;
                System.out.println(">>> total diff is: " + sum + " hour.");
                log.info(">>> total diff is: " + sum + " hour.");
            } catch (Exception e) {
                System.out.println(">>> error is:  = " + e);
                log.info(">>> error is:  = " + e);
            }
        }
        System.out.println(">>> total diff is: " + sum + " hour.");
        log.info(">>> total diff is: " + sum + " hour.");
        System.out.println(">>> please come again next time");
        log.info(">>> please come again next time");
    }
}
