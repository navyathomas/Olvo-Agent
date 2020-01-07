package com.example.root.olvoagent.models.AddShopModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 29/10/19.
 */

public class AddShopMainModel {

    @SerializedName("status")
    private Boolean AddShopstatus;

    @SerializedName("status_code")
    private Integer AddShopStatusCode;

    @SerializedName("data")
    private DataAddShop dataAddShop;

    @SerializedName("error")
    private ErrorAddModel errorAddModel;


    public Boolean getAddShopstatus(){
        return AddShopstatus;
    }
    public void setAddShopstatus(Boolean shopstatus){
        this.AddShopstatus=shopstatus;
    }

    public Integer getAddShopStatusCode(){
        return AddShopStatusCode;
    }
    public void setAddShopStatusCode(Integer shopStatusCode){
        this.AddShopStatusCode=shopStatusCode;
    }

    public DataAddShop  getDataAddShop(){
        return dataAddShop;
    }
    public void setDataAddShop(DataAddShop dataAddShop){
        this.dataAddShop=dataAddShop;
    }


    public ErrorAddModel getErrorAddModel(){
        return errorAddModel;
    }
    public void setErrorAddModel(ErrorAddModel errorAddModel1){
        this.errorAddModel=errorAddModel1;
    }

}
