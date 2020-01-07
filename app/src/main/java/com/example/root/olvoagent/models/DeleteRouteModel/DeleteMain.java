package com.example.root.olvoagent.models.DeleteRouteModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 7/11/19.
 */

public class DeleteMain {

    @SerializedName("status")
    private Boolean deleteStatus;

    @SerializedName("status_code")
    private Integer deleteStatusCode;

    @SerializedName("data")
    private DeleteData deleteData;

    @SerializedName("error")
    private DeleteError deleteError;



    public void setDeleteStatus(Boolean deleteStatus1){
        this.deleteStatus=deleteStatus1;
    }
    public Boolean getDeleteStatus(){
        return deleteStatus;
    }

    public void setDeleteStatusCode(Integer deleteStatusCode1){
        this.deleteStatusCode=deleteStatusCode1;
    }
    public Integer getDeleteStatusCode(){
        return deleteStatusCode;
    }

    public void setDeleteData(DeleteData deleteData1){
        this.deleteData=deleteData1;
    }
    public DeleteData getDeleteData(){
        return deleteData;
    }

    public void setDeleteError(DeleteError deleteError1){
        this.deleteError=deleteError1;
    }
    public DeleteError getDeleteError(){
        return deleteError;
    }
}