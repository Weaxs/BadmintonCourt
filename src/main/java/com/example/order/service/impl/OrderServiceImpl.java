package com.example.order.service.impl;

import com.example.order.service.OrderService;
import com.example.order.dao.OrderMapper;
import com.example.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by WTfoxS on 2017/9/9.
 */
@Service
public class OrderServiceImpl   implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public boolean insertOrder(Order order) {
        if(this.isConflict(order)){
            return false;
        }
        int start = 0;
        int end = 0;
        String tmp[] = order.getStartTime().split(":");
        start = Integer.parseInt(tmp[0]);
        tmp = order.getEndTime().split(":");
        end = Integer.parseInt(tmp[0]);
        order.setFee(this.CalculateFee(order.getOrderDate(),start,end));
        orderMapper.insertOrder(order);
        return true;
    }

    public boolean cancelOrder(Order order) {
        Order tmp = orderMapper.getByOrder(order);
        if(this.isExist(order) && tmp.getMark() != 'C'){
//            Order tmp = orderMapper.getByOrder(order);
            int week = this.getDayOfWeek(order.getOrderDate());
            if(week <= 5 && week >= 1){
                order.setFee(tmp.getFee()*50/100);
            }else{
                order.setFee(tmp.getFee()*25/100);
            }
            orderMapper.cancelOrder(order);
            return true;
        }
        return false;
    }

    public int CalculateFee(String date,int startTime, int endTime) {
        int week = this.getDayOfWeek(date);
        int tmp = startTime;
        int fee = 0;
        //周一到周五
        if(week <= 5 && week >= 1){
            //9点-12点 30元/时
            if(tmp < 12){
                if (endTime > 12){
                    fee += (12 - tmp) * 30;
                    tmp = 12;
                }else{
                    fee = (endTime - tmp) * 30;
                }
            }
            //12点-18点 50元/时
            if(tmp < 18 && tmp >= 12){
                if(endTime > 18){
                    fee += (18 - tmp) * 50;
                    tmp = 18;
                }else {
                    fee += (endTime - tmp) * 50;
                }
            }
            //18点-20点 80元/时
            if(tmp < 20 && tmp >= 18){
                if(endTime > 20){
                    fee += (20-tmp) * 80;
                    tmp = 20;
                }else {
                    fee += (endTime - tmp) * 80;
                }
            }
            //20点-22点 60元/时
            if(tmp < 22 && tmp >= 20){
                fee += (endTime - tmp) * 60;
            }
        } else{//周六到周日
            //9点-12点 40元/时
            if(tmp < 12){
                if (endTime > 12){
                    fee += (12 - tmp) * 40;
                    tmp = 12;
                }else{
                    fee = (endTime - tmp) * 40;
                }
            }
            //12点-18点 50元/时
            if(tmp < 18 && tmp >= 12){
                if(endTime > 18){
                    fee += (18 - tmp) * 50;
                    tmp = 18;
                }else {
                    fee += (endTime - tmp) * 50;
                }
            }
            //18点-22点 60元/时
            if(tmp < 20 && tmp >= 18){
                    fee += (endTime - tmp) * 60;
            }
        }
        return fee;
    }

    public int getDayOfWeek(String date) {
        Calendar cal = Calendar.getInstance();
        String tmp[] = date.split("-");
        //设置周一位第一天
        cal.setFirstDayOfWeek(cal.MONDAY);
        try {
            //设定周一为第一天

            cal.setTime(new SimpleDateFormat("yyyy-mm-dd").parse(date));
            //设定月份
            cal.set(cal.MONTH,Integer.parseInt(tmp[1]) - 1);
            return cal.get(Calendar.DAY_OF_WEEK) - 1;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean isConflict(Order order) {

        List<Order> orders = new ArrayList<Order>();
        //找到符合日期和场地的所有订单
        orders = orderMapper.getByOrderDate(order.getOrderDate(),order.getCourt());
        int start = 0,end = 0,iStart = 0,iEnd = 0;
        String tmp[] = order.getStartTime().split(":");
        start = Integer.parseInt(tmp[0]);
        tmp = order.getEndTime().split(":");
        end = Integer.parseInt(tmp[0]);

        for (Order i:orders){
            //已取消订单不参与冲突判断
            if(i.getMark() == 'C' || i.getMark() == 'c')
                continue;
            tmp = i.getStartTime().split(":");
            iStart = Integer.parseInt(tmp[0]);
            tmp = i.getEndTime().split(":");
            iEnd = Integer.parseInt(tmp[0]);
            //此种情况不冲突
            if( end <= iStart || start >= iEnd ){
                continue;
            }else{
                return  true;
            }
        }

        return false;
    }

    public boolean isExist(Order order) {
        Order tmp = orderMapper.getByOrder(order);
        if(StringUtils.isEmpty(tmp))
            return false;
        return true;
    }

    public List<Order> getAll() {
        return orderMapper.getAll();
    }

    public List<Order> getByCourt(char court) {
        return orderMapper.getByCourt(court);
    }
}
