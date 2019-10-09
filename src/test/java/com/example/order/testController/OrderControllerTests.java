package com.example.order.testController;

import com.example.order.controller.OrderController;
import com.example.order.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WTfoxS on 2017/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTests {

    @Autowired
    private OrderController orderController ;


    @Test
    public void addOrderTest(){
        String  str = "U006 2017-09-09 14:00~15:00 C ";
//        str = ">Error: the booking is";
        String result = orderController.addOrder(str);
        System.out.println(result);
    }

    @Test
    public void cancelOrderTest(){
        String  str = "U006 2017-09-09 14:00~15:00 C C";
//        str = ">Error: the booking is invalid!";
        String result = orderController.cancelOrder(str);
        System.out.println(result);
    }

    @Test
    public void getByCourtTest(){
        char court = 'C';
        String result = null;
        List<Order> orders = new ArrayList<Order>();
        orders = orderController.getByCourt(court);
        for(Order i:orders){
            System.out.println(i.toString());
        }
    }

}
