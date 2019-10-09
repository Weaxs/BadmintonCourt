package com.example.order.controller;

import com.example.order.service.OrderService;
import com.example.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by WTfoxS on 2017/9/9.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    private Order order;
    private String result;

    //添加订单
    public String addOrder(String str){
        result = ">Error: the booking is invalid!";
        order = new Order();
        String element[] = str.split(" ");
        //赋值给Order对象
        order.setUserId(element[0]);
        order.setOrderDate(element[1]);
        String tmp[] = element[2].split("~");
        if(tmp.length != 2)
            return result;
        order.setStartTime(tmp[0]);
        order.setEndTime(tmp[1]);
        order.setCourt(element[3].charAt(0));

        String starter[] = tmp[0].split(":");
        int start = Integer.parseInt(starter[0]);
        String ender[] = tmp[1].split(":");
        int end = Integer.parseInt(ender[0]);
        //判断起始时间和结束时间，以及是否输出整点
        if(start >= end || start < 9 || end > 22 || Integer.parseInt(starter[1]) != 0 ||Integer.parseInt(ender[1]) != 0)
            return result;

        //判断场地是非非法
        char court = order.getCourt();
        if(court != 'A' && court != 'B' && court != 'C' && court != 'D')
            return result;

        //调用insert，在service层判断订单是否冲突
        boolean isSucceed = orderService.insertOrder(order);

        if(isSucceed)
            result = ">Success: the booking is accepted!";
        return result;
    }

    //取消订单
    public String cancelOrder(String str){
        result = ">Error: the booking is invalid!";
        order = new Order();
        String element[] = str.split(" ");
        //赋值给Order对象
        order.setUserId(element[0]);
        order.setOrderDate(element[1]);
        String tmp[] = element[2].split("~");
        if(tmp.length != 2)
            return result;
        order.setStartTime(tmp[0]);
        order.setEndTime(tmp[1]);
        order.setCourt(element[3].charAt(0));
        //设置取消标志
        order.setMark(element[4].charAt(0));

        String starter[] = tmp[0].split(":");
        int start = Integer.parseInt(starter[0]);
        String ender[] = tmp[1].split(":");
        int end = Integer.parseInt(ender[0]);
        //判断起始时间和结束时间，以及是否输出整点
        if(start >= end || start < 9 || end > 22 || Integer.parseInt(starter[1]) != 0 ||Integer.parseInt(ender[1]) != 0)
            return result;

        //判断场地是否非法
        char court = order.getCourt();
        if(court != 'A' && court != 'B' && court != 'C' && court != 'D')
            return result;

        //判断取消标志是否合法
        char mark = order.getMark();
        if(mark != 'C')
            return result;

        boolean isSucceed = orderService.cancelOrder(order);
        if(isSucceed)
            result = ">Success: the booking is accepted!";
        return result;
    }

    //根据场地获取订单
    public List<Order> getByCourt(char court){
        return orderService.getByCourt(court);
    }

}
