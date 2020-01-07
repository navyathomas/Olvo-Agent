package com.example.root.olvoagent.models.ToupUpHistoryModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/10/19.
 */

public class HistoryDateModel {


    @SerializedName("shop_name")
    private String filterShopName;

    @SerializedName("date")
    private String filterDate;

    @SerializedName("shop_id")
    private String shopId;

    public void setFilterShopName(String filterShopName1){
        this.filterShopName=filterShopName1;
    }
    public String getFilterShopName(){
        return filterShopName;
    }

    public void setFilterDate(String filterDate1){
        this.filterDate=filterDate1;
    }
    public String getFilterDate(){
        return filterDate;
    }

    public void setShopId(String shopId1){
        this.shopId=shopId1;
    }
    public String getShopId(){
        return shopId;
    }




}
