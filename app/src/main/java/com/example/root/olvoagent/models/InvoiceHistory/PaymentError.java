package com.example.root.olvoagent.models.InvoiceHistory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 18/11/19.
 */

public class PaymentError {

    @SerializedName("title")
    private String paymentTitle;

    @SerializedName("message")
    private String paymentMessage;

    public void setPaymentTitle(String paymentTitle1){
        this.paymentTitle=paymentTitle1;
    }
    public String getPaymentTitle(){
        return paymentTitle;
    }

    public void setPaymentMessage(String paymentMessage1){
        this.paymentMessage=paymentMessage1;
    }
    public String getPaymentMessage(){
        return paymentMessage;
    }




}
