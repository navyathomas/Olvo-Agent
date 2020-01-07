package com.example.root.olvoagent.models.ProfileApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 17/9/19.
 */

public class ProfileMainModel {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("data")
    private ProfileDataModel dataModel;

    @SerializedName("error")
    private ProfileErrorModel errorModel;


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


    public void setDataModel(ProfileDataModel dataModel1){
        this.dataModel=dataModel1;
    }
    public ProfileDataModel getDataModel(){
        return dataModel;
    }

    public void  setErrorModel(ProfileErrorModel errorModel){
        this.errorModel=errorModel;
    }
    public ProfileErrorModel getErrorModel(){
        return errorModel;
    }

}
