package com.example.root.olvoagent.models.AddShopModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 30/10/19.
 */

public class ErrorAddModel {

    @SerializedName("title")
    private String shopTitle;

    @SerializedName("message")
    private String shopMessage;


    public String getShopTitle(){
        return shopTitle;
    }
    public void setShopTitle(String shopTitle1){
        this.shopTitle=shopTitle1;
    }

    public String getShopMessage(){
        return shopMessage;
    }
    public void setShopMessage(String shopMessage1){
        this.shopMessage=shopMessage1;
    }
}
