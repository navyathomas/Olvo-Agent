package com.example.root.olvoagent.models.RechargeHistory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 30/9/19.
 */

public class HistoryModel {

    @SerializedName("_id")
    private String id;

    @SerializedName("shop_id")
    private String shopId;

    @SerializedName("topup_id")
    private String topupId;

    @SerializedName("product_amount")
    private Double productAmount;


    @SerializedName("product_amount_currency")
    private Double productCurrency;

    @SerializedName("grand_buyrate")
    private Double grandBuyrate;

    @SerializedName("shop_name")
    private String shopName;

    @SerializedName("topup_name")
    private String topupName;

    @SerializedName("date")
    private String date;

    @SerializedName("transaction_number")
    private String transcationNumber;


    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }

    public void setShopId(String shopId){
        this.shopId=shopId;
    }
    public String getShopId(){
        return shopId;
    }


    public void setTopupId(String topupId){
        this.topupId=topupId;
    }
    public String getTopupId(){
        return topupId;
    }


    public void setProductAmount(Double productAmount1){
        this.productAmount=productAmount1;
    }
    public Double getProductAmount(){
        return productAmount;
    }


    public void setProductCurrency(Double productCurrency1){
        this.productCurrency=productCurrency1;
    }
    public Double getProductCurrency(){
        return productCurrency;
    }


    public void setGrandBuyrate(Double grandBuyrate1){
        this.grandBuyrate=grandBuyrate1;
    }
    public Double getGrandBuyrate(){
        return grandBuyrate;
    }
    public void setShopName(String shopName1){
        this.shopName=shopName1;
    }
    public String getShopName(){
        return shopName;
    }
    public void setTopupName(String topupName){
        this.topupName=topupName;
    }
    public String getTopupName(){
        return topupName;
    }




    public void setDate(String date1){
        this.date=date1;
    }
    public String getDate(){
        return date;
    }



    public void setTranscationNumber(String transcationNumber1){
        this.transcationNumber=transcationNumber1;
    }
    public String getTranscationNumber(){
        return transcationNumber;
    }

}
