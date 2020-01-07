package com.example.root.olvoagent.models.LoginApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 26/8/19.
 */

public class DataModel {

    @SerializedName("userId")
    private String userId;

    @SerializedName("email")
    private String email;

    @SerializedName("employee_id")
    private  String employeeId;

    @SerializedName("token")
    private String token;




    public void setUserId(String userId1){
        this.userId=userId1;
    }

    public String getUserId(){
        return userId;
    }


    public void setEmail(String email1){
        this.email=email1;
    }
    public String getEmail(){
        return email;
    }


    public void setEmployeeId(String employeeId1){
        this.employeeId=employeeId1;
    }
    public String getEmployeeId(){
        return employeeId;
    }

    public void setToken(String token1){
        this.token=token1;
    }

    public String getToken(){
        return  token;
    }
}
