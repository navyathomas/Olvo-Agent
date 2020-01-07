package com.example.root.olvoagent.models.InvoiceHistory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 18/11/19.
 */

public class PaymentHistoryMain {

    @SerializedName("status")
    private Boolean paymentStatus;

    @SerializedName("status_code")
    private Integer paymentStatusCode;

    @SerializedName("data")
    private PaymentData paymentData;

    @SerializedName("error")
    private PaymentError paymentError;

    public void setPaymentStatus(Boolean paymentStatus1){
        this.paymentStatus=paymentStatus1;
    }
    public Boolean getPaymentStatus(){
        return paymentStatus;
    }

    public void setPaymentStatusCode(Integer paymentStatusCode1){
        this.paymentStatusCode=paymentStatusCode1;
    }
    public Integer getPaymentStatusCode(){
        return paymentStatusCode;
    }

    public void setPaymentData(PaymentData paymentData1){
        this.paymentData=paymentData1;
    }
    public PaymentData getPaymentData(){
        return paymentData;
    }

    public void setPaymentError(PaymentError paymentError1){
        this.paymentError=paymentError1;
    }
    public PaymentError getPaymentError(){
        return paymentError;
    }




}
