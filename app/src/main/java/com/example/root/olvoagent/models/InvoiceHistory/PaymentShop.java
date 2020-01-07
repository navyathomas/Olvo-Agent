package com.example.root.olvoagent.models.InvoiceHistory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 19/11/19.
 */

public class PaymentShop {


    @SerializedName("accounts")
    private PaymentAccount account;


    @SerializedName("_id")
    private String paymentId;

    @SerializedName("name")
    private String paymentName;

    @SerializedName("route_id")
    private String paymentRouteId;



    public void setAccount(PaymentAccount paymentAccount){
        this.account=paymentAccount;
    }
    public PaymentAccount getAccount(){
        return account;
    }

    public void setPaymentId(String paymentId1){
        this.paymentId=paymentId1;
    }
    public String getPaymentId(){
        return paymentId;
    }

    public void setPaymentName(String paymentName1){
        this.paymentName=paymentName1;
    }

    public String getPaymentName(){
        return paymentName;
    }

    public void setPaymentRouteId(String paymentRouteId1){
        this.paymentRouteId=paymentRouteId1;
    }
    public String getPaymentRouteId(){
        return paymentRouteId;
    }
}
