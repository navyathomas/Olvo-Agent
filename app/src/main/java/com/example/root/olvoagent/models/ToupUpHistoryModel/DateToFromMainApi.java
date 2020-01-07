package com.example.root.olvoagent.models.ToupUpHistoryModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/10/19.
 */

public class DateToFromMainApi {


    @SerializedName("status")
    private Boolean status;

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("data")
    private DataDateModel dateModel;

    @SerializedName("error")
    private ErrorDateModel errorDateModel;


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

    public void setDateModel(DataDateModel dateModel1){
        this.dateModel=dateModel1;
    }
    public DataDateModel getDateModel(){
        return dateModel;
    }

    public void setErrorDateModel(ErrorDateModel errorDateModel){
        this.errorDateModel=errorDateModel;
    }
    public ErrorDateModel getErrorDateModel(){
        return errorDateModel;
    }


}
