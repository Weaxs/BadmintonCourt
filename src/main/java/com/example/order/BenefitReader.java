package com.example.order;

import com.example.order.domain.Benefit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WTfoxS on 2017/9/15.
 */
public class BenefitReader {

    public void readFile(){

        List<Benefit> benefits = new ArrayList<Benefit>();
        String encoding = "UTF-8";
        File file = new File("/classes/file/benefit");
        try {
            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader buffer = new BufferedReader(reader);
                String lineText = null;

                while((lineText = buffer.readLine()) !=  null){
                    String tmp[] = lineText.split(" ");
                    Benefit benefit = new Benefit();
                    benefit.setBenStartDate(tmp[0]);
                    benefit.setBenEndDate(tmp[1]);
                    benefit.setBenefit(Integer.parseInt(tmp[2]));
                    benefits.add(benefit);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
