package com.kandidato.util.parser;

import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by diykorey on 05.04.2015.
 */
public class DatetimeTest {
    @Test
    public void todayTest(){
        LocalDate today = LocalDate.now();
        System.out.println("Current Date="+today);
    }
}
