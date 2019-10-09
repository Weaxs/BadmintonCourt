package com.example.order.domain;

/**
 * Created by WTfoxS on 2017/9/15.
 */
public class Benefit {

    //折扣开始时间
    private String benStartDate;

    private String benEndDate;
    private int benefit;


    public String getBenStartDate() {
        return benStartDate;
    }

    public void setBenStartDate(String benStartDate) {
        this.benStartDate = benStartDate;
    }

    public String getBenEndDate() {
        return benEndDate;
    }

    public void setBenEndDate(String benEndDate) {
        this.benEndDate = benEndDate;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }
}
