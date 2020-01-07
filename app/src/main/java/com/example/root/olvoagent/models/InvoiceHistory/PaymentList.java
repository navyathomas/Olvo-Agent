package com.example.root.olvoagent.models.InvoiceHistory;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 18/11/19.
 */

public class PaymentList {

    @SerializedName("reward_amount")
    private Integer paymentReward;

    @SerializedName("reward_amount_currency")
    private Integer rewardAmountCurrency;

    @SerializedName("processing_fee")
    private Integer paymentProcessingFee;

    @SerializedName("processing_fee_currency")
    private Integer processingFeeCurrency;

    @SerializedName("status")
    private Integer paymentStatus;

    @SerializedName("purchase_status")
    private String purchaseStatus;

    @SerializedName("payment_note")
    private String paymentNote;

    @SerializedName("shop_paid")
    private Boolean shopPaid;

    @SerializedName("salesman_received")
    private Boolean salesmanReceived;


    @SerializedName("shop_id")
    private PaymentShop paymentShop;

    @SerializedName("status_log")
    private List<StatusLogPayment> statusLogPayments;




    public void setPaymentReward(Integer paymentReward1){
        this.paymentReward=paymentReward1;
    }
    public Integer getPaymentReward(){
        return paymentReward;
    }

    public void setRewardAmountCurrency(Integer rewardAmountCurrency1){
        this.rewardAmountCurrency=rewardAmountCurrency1;
    }
    public Integer getRewardAmountCurrency(){
        return rewardAmountCurrency;
    }

    public void setPaymentProcessingFee(Integer paymentProcessingFee1){
        this.paymentProcessingFee=paymentProcessingFee1;
    }
    public Integer getPaymentProcessingFee(){
        return paymentProcessingFee;
    }

    public void setProcessingFeeCurrency(Integer processingFeeCurrency1){
        this.processingFeeCurrency=processingFeeCurrency1;
    }
    public Integer getProcessingFeeCurrency(){
        return processingFeeCurrency;
    }

    public void setPaymentStatus(Integer paymentStatus1){
        this.paymentStatus=paymentStatus1;
    }
    public Integer getPaymentStatus(){
        return paymentStatus;
    }

    public void setPurchaseStatus(String purchaseStatus1){
        this.purchaseStatus=purchaseStatus1;
    }
    public String getPurchaseStatus(){
        return purchaseStatus;
    }

    public void setPaymentNote(String paymentNote1){
        this.paymentNote=paymentNote1;
    }
    public String getPaymentNote(){
        return paymentNote;
    }

    public void setShopPaid(Boolean shopPaid1){
        this.shopPaid=shopPaid1;
    }
    public Boolean getShopPaid(){
        return shopPaid;
    }

    public void setSalesmanReceived(Boolean salesmanReceived){
        this.salesmanReceived=salesmanReceived;
    }
    public Boolean getSalesmanReceived(){
        return  salesmanReceived;
    }


    public void setStatusLogPayments(List<StatusLogPayment> statusLogPayments1){
        this.statusLogPayments=statusLogPayments1;
    }
    public List<StatusLogPayment> getStatusLogPayments(){
        return statusLogPayments;
    }
}
