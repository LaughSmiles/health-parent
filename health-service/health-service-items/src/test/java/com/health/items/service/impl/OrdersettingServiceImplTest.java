package com.health.items.service.impl;

import java.util.Date;

public class OrdersettingServiceImplTest {

    public static void main(String[] args) {
        String date = "2021-12-10";
        String[] dateInfo = date.split("-");
        Date date1 = new Date(Integer.valueOf(dateInfo[0]) - 1900, Integer.valueOf(dateInfo[1]) - 1
                , Integer.valueOf(dateInfo[2]));

//        Ordersetting ordersetting = new Ordersetting();



/*        ordersetting.setOrderDate(new Date(Integer.valueOf(dateInfo[0]),Integer.valueOf(dateInfo[1])
                , Integer.valueOf(dateInfo[2])));*/
        System.out.println(date1);

    }

}