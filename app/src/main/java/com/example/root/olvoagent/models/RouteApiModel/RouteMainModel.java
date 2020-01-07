package com.example.root.olvoagent.models.RouteApiModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 20/9/19.
 */

public class RouteMainModel {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("data")
    public List<DataArrayModel> dataArrayModels;  //new arrayList()<>;//

    @SerializedName("error")
    private ErrorModel errorModel;


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




    public void setDataArrayModels( List<DataArrayModel>  dataArrayModels1){
        this.dataArrayModels=dataArrayModels1;
    }

    public List<DataArrayModel> getDataArrayModels(){
        return dataArrayModels;
    }

    public void setErrorModel(ErrorModel errorModel1){
        this.errorModel=errorModel1;
    }
    public ErrorModel getErrorModel(){
        return errorModel;
    }


}
