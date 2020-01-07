package com.example.root.olvoagent.models.InvoiceHistory;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 18/11/19.
 */

public class PaymentData {

    @SerializedName("list")
    private List<PaymentList> paymentLists;

    @SerializedName("createdAt")
    private String paymentCreatedAt;

    @SerializedName("transaction_number")
    private Integer paymentTransactionNumber;


    public void setPaymentLists(List<PaymentList> paymentLists1){
        this.paymentLists=paymentLists1;
    }
    public List<PaymentList> getPaymentLists(){
        return paymentLists;
    }

    public void setPaymentCreatedAt(String paymentCreatedAt1){
        this.paymentCreatedAt=paymentCreatedAt1;
    }
    public String getPaymentCreatedAt(){
        return paymentCreatedAt;
    }

    public void setPaymentTransactionNumber(Integer paymentTransactionNumber1){
        this.paymentTransactionNumber=paymentTransactionNumber1;
    }
    public Integer getPaymentTransactionNumber(){
        return  paymentTransactionNumber;
    }



}
