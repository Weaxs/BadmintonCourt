package com.example.order;

import com.example.order.controller.OrderController;
import com.example.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

/**
 * Created by WTfoxS on 2017/9/11.
 */
@Component
public class ApplicationStart {

    @Autowired
    private OrderController orderController;
    File file = new File("target/classes/file/generalIncome");

    public  void start() throws Exception{

        Scanner sc = new Scanner(System.in);
        System.out.println(">欢迎进入羽毛球馆管理系统");
        String result = null;
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            if (str.split(" ").length == 4){
                result = orderController.addOrder(str);
                System.out.println(result);
            }else if(str.split(" ").length == 5){
                result = orderController.cancelOrder(str);
                System.out.println(result);
            }else if(StringUtils.isEmpty(str)){
                getIncome();
            }else{
                System.out.println(">Error: the booking is invalid!");
            }
        }
        sc.close();
    }

    public void getIncome() throws Exception{
        FileWriter fileWriter = new FileWriter(file);
        char courts[] = {'A','B','C','D'};
        List<Order> orders;
        double sum[] = new double[5];
        int i = 1;
        int tmp = 0;
        System.out.println(">收入汇总");
        fileWriter.write("收入汇总\n");
        System.out.println(">---");
        fileWriter.write("---\n");
        for(char court:courts){
            System.out.println(">场地:" + court);
            fileWriter.write("场地:" + court + "\n");
            orders = orderController.getByCourt(court);
            for (Order order:orders){
                System.out.println(">" + order.toString());
                fileWriter.write(order.toString() + "\n");
                sum[i] += order.getFee();
                sum[0] += order.getFee();
            }
            tmp = (int)sum[i];
            if (tmp == sum[i]) {
                System.out.println(">小计：" + tmp + "元");
                fileWriter.write("小计：" + tmp + "元\n");
            }else {
                System.out.println(">小计：" + sum[i] + "元");
                fileWriter.write("小计：" + sum[i] + "元\n");
            }
            if(court != 'D'){
                System.out.println(">");
                fileWriter.write("\n");
            }
            i++;
        }
        System.out.println(">---");
        fileWriter.write("---\n");
        tmp = (int)sum[0];
        if (tmp == sum[0]) {
            System.out.println(">总计：" + tmp + "元");
            fileWriter.write("总计：" + tmp + "元\n");
        }else {
            System.out.println(">总计：" + sum[0] + "元");
            fileWriter.write("总计：" + sum[0] + "元\n");
        }

        fileWriter.close();
    }
}
