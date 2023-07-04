package com.health.items.service.impl;

import junit.framework.TestCase;

import java.util.Date;


public class SetmealServiceImplTest extends TestCase {

    public static void main(String[] args) {
        String date = "2023-6-29";
        String[] split = date.split("-");
        Date date2 = new Date(Integer.valueOf(split[0]), Integer.valueOf(split[1]), Integer.valueOf(split[2]));
        System.out.println(date2);

    }
}