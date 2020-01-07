package com.example.root.olvoagent.models.InvoiceHistory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 19/11/19.
 */

public class StatusLogPayment {


    @SerializedName("time")
    private String paymentTime;

    public void setPaymentTime(String paymentTime1){
        this.paymentTime=paymentTime1;
    }
    public String getPaymentTime(){
        return paymentTime;
    }
}
