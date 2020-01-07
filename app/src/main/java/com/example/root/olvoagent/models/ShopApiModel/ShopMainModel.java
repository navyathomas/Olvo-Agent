package com.example.root.olvoagent.models.ShopApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 2/9/19.
 */

public class ShopMainModel {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("data")
    private DataShopModel dataModel;

    @SerializedName("error")
    private ErrorModels errorModels;


    public void setStatus(Boolean status1){
        this.status=status1;
    }
    public Boolean getStatus(){
        return status;
    }

    public void setStatusCode(Integer statusCode1){
        this.statusCode=statusCode1;
    }
    public Integer getStatusCode(){
        return statusCode;
    }

    public void setDataModel(DataShopModel dataShopModel){
        this.dataModel=dataShopModel;
    }
    public DataShopModel getDataModel(){
        return dataModel;
    }

    public void setErrorModels(ErrorModels errorModels1){
        this.errorModels=errorModels1;
    }
    public ErrorModels getErrorModels(){
        return errorModels;
    }
}
