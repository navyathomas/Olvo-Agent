package com.example.root.olvoagent.models.HomeAdModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 29/8/19.
 */

public class HomeMainModel {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("data")
    private DataAdModel dataModels;

    @SerializedName("error")
    private ErrorModel error;


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

    public void setDataModels(DataAdModel dataModels1){
        this.dataModels=dataModels1;
    }
    public DataAdModel getDataModels(){
        return dataModels;
    }

    public void setError(ErrorModel error1){
        this.error=error1;
    }
    public ErrorModel getError(){
        return error;
    }


}
