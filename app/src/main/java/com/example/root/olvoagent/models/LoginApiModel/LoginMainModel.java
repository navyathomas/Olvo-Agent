
package com.example.root.olvoagent.models.LoginApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 26/8/19.
 */

public class LoginMainModel {

    @SerializedName("status")
    private Boolean status;

    @SerializedName("status_code")
    private Integer statusCode;

    @SerializedName("data")
    private DataModel datas;

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

    public void setDatas(DataModel dataModels){
        this.datas=dataModels;
    }

    public DataModel getDatas(){
        return datas;
    }


    public void setError(ErrorModel error1){
        this.error=error1;
    }
    public ErrorModel getError(){
        return error;
    }

}
