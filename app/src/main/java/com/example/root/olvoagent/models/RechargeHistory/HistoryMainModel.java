package com.example.root.olvoagent.models.RechargeHistory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 30/9/19.
 */

public class HistoryMainModel {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("status_code")
    private Integer status_code;

    @SerializedName("data")
    private DataModel data;

    @SerializedName("error")
    private ErrorModel errorModel;


    public void setStatus(Boolean status1){
        this.status=status1;
    }
    public Boolean getStatus(){
        return status;
    }
    public void  setStatus_code(Integer status_code1){
        this.status_code=status_code1;
    }
    public Integer getStatus_code(){
        return status_code;
    }
    public void setData(DataModel data1){
        this.data=data1;
    }
    public DataModel getData(){
        return data;
    }

    public void setErrorModel(ErrorModel errorModel){
        this.errorModel=errorModel;
    }
    public ErrorModel getErrorModel(){
        return  errorModel;
    }
}
