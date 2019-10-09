package com.example.order.service;

import com.example.order.domain.Order;

import java.util.List;

/**
 * Created by WTfoxS on 2017/9/9.
 */
public interface OrderService {

    //插入订单
    boolean insertOrder(Order order);
    //取消订单
    boolean cancelOrder(Order order);
    //计算费用
    int CalculateFee(String date,int startTime,int endTime);
    //判断星期
    int getDayOfWeek(String date);
    //判断是否冲突
    boolean isConflict(Order order);
    //判断是否存在此订单
    boolean isExist(Order order);
    //获取所有订单
    List<Order> getAll();
    //根据场地获取订单
    List<Order> getByCourt(char court);

}
