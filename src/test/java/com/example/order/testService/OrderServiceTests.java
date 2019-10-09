package com.example.order.testService;

import com.example.order.service.OrderService;
import com.example.order.domain.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WTfoxS on 2017/9/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;
    private Order order = new Order();
//    private static final Logger LOG = LoggerFactory.getLogger(BadmintoncourtApplication.class);
    List<Order> orders = new ArrayList<Order>();


    @Before
    public void initOrder(){
        order.setUserId("U001");
        order.setOrderDate("2017-10-10");
        order.setStartTime("9:00");
        order.setEndTime("22:00");
        order.setCourt('A');
//        order.setMark('C');
    }

    @Test
    public void insertOrderTest(){
        boolean result = orderService.insertOrder(order);
        System.out.println(result);
    }

    @Test
    public void cancelOrderTest(){
        boolean result = orderService.insertOrder(order);
        System.out.println(result);
    }

    @Test
    public void getDayOfWeekTest(){
        //分别对应0,1,2,3,4,5,6
        String[] week = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        String date = "2017-10-01";
        int result = orderService.getDayOfWeek(date);
        System.out.println(week[result]);
    }

    @Test
    public void isConflictTest(){
        boolean result = orderService.isConflict(order);
        System.out.println(result);
    }

    @Test
    public void isExistTest(){
        boolean result = orderService.isExist(order);
        System.out.println(result);
    }

    @Test
    public void getAllTest(){
        orders = orderService.getAll();
        for(Order i : orders){
            System.out.println(i);
        }
    }
    @Test
    public void getByCourtTest(){
        char court = 'D';
        orders = orderService.getByCourt(court);
        for(Order i : orders){
            System.out.println(i);
        }
    }

}
