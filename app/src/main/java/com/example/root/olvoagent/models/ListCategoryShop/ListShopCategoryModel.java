package com.example.root.olvoagent.models.ListCategoryShop;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 12/11/19.
 */

public class ListShopCategoryModel {

    @SerializedName("status")
    private Boolean shopCategoryStatus;

    @SerializedName("status_code")
    private Integer shopCategoryStatusCode;

    @SerializedName("data")
    private List<DataShopCategory> dataShopCategories;

    @SerializedName("error")
    private ErrorCategory errorCategory;




    public void setShopCategoryStatus(Boolean shopCategoryStatus1){
        this.shopCategoryStatus=shopCategoryStatus1;
    }
    public Boolean getShopCategoryStatus(){
        return shopCategoryStatus;
    }

    public Integer getShopCategoryStatusCode(){
        return shopCategoryStatusCode;
    }
    public void setShopCategoryStatusCode(Integer shopCategoryStatusCode1){
        this.shopCategoryStatusCode=shopCategoryStatusCode1;
    }

    public List<DataShopCategory> getDataShopCategories(){
        return dataShopCategories;
    }
    public void setDataShopCategories( List<DataShopCategory> dataShopCategories1){
        this.dataShopCategories=dataShopCategories1;
    }

    public void setErrorCategory(ErrorCategory errorCategory1){
        this.errorCategory=errorCategory1;
    }
    public ErrorCategory getErrorCategory(){
        return errorCategory;
    }


}
