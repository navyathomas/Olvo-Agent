package com.example.root.olvoagent.models.ProfileApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 17/9/19.
 */

public class ProfileDataModel {

    @SerializedName("employee_id")
    private String employeeId;

    @SerializedName("employee_name")
    private String employeeName;

    @SerializedName("email")
    private String email;

    @SerializedName("mobile_number")
    private String mobileNumberr;


    @SerializedName("currency_iso")
    private String currencyIso;


    @SerializedName("shop_count")
    private Integer shpCount;


    @SerializedName("country_id")
    private String shopCountryId;


    @SerializedName("country_name")
    private String countryName;


    @SerializedName("total_recharge_count")
    private Integer totalRechargeCount;


    @SerializedName("total_totalSales")
    private Double totalSales;




    public void setEmployeeId(String employeeId){
        this.employeeId=employeeId;
    }
    public String getEmployeeId(){
        return employeeId;
    }

    public void setEmployeeName(String employeeName){
        this.employeeName=employeeName;
    }

    public String getEmployeeName(){
        return employeeName;
    }


    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }

public void  setCountryName(String countryName1){
    this.countryName=countryName1;
}
    public String getCountryName(){
        return countryName;
    }



    public void setCurrencyIso(String currencyIso1){
        this.currencyIso=currencyIso1;
    }

    public String getCurrencyIso(){
        return currencyIso;
    }

    public void setShpCount(Integer shpCount1){
        this.shpCount=shpCount1;
    }
    public Integer getShpCount(){
        return shpCount;
    }

    public void setTotalRechargeCount(Integer totalRechargeCount1){
        this.totalRechargeCount=totalRechargeCount1;
    }

    public Integer getTotalRechargeCount(){
        return totalRechargeCount;
    }


    public void setTotalSales(Double totalSales1){
        this.totalSales=totalSales1;
    }

    public Double getTotalSales(){
        return totalSales;
    }


    public void setShopCountryId(String shopCountryId1){
        this.shopCountryId=shopCountryId1;
    }
    public String getShopCountryId(){
        return shopCountryId;
    }



    public void setMobileNumberr(String mobileNUmber1){
        this.mobileNumberr=mobileNUmber1;
    }

    public String getMobileNumberr(){
        return mobileNumberr;
    }
}
