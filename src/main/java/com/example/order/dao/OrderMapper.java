package com.example.order.dao;

import com.example.order.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WTfoxS on 2017/9/9.
 */
@Repository
public interface OrderMapper {

    //添加订单
    boolean insertOrder(Order order);
    //取消订单
    boolean cancelOrder(Order order);
    //通过订单对象获取订单
    Order getByOrder(Order order);
    //获取所有订单对象
    List<Order> getAll();
    //通过日期获取订单对象
    List<Order> getByOrderDate(String date,char court);
    //通过场地查询
    List<Order> getByCourt(char court);

}
