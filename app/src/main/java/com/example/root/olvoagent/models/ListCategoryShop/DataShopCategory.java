package com.example.root.olvoagent.models.ListCategoryShop;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 12/11/19.
 */

public class DataShopCategory {


    @SerializedName("_id")
    private String shopCategoryId;

    @SerializedName("name")
    private String shopCategoryName;

    @SerializedName("createdAt")
    private String shopCategoryCreated;

    @SerializedName("updatedAt")
    private String shopCategoryUpdated;


    public void setShopCategoryId(String shopCategoryId){
        this.shopCategoryId=shopCategoryId;
    }
    public String getShopCategoryId(){
        return shopCategoryId;
    }

    public void setShopCategoryName(String shopCategoryName1){
        this.shopCategoryName=shopCategoryName1;
    }
    public String getShopCategoryName(){
        return shopCategoryName;
    }


}
