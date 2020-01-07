package com.example.root.olvoagent.models.LoginApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 26/8/19.
 */

public class ErrorModel {


    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;


    public void setTitle(String title1){
        this.title=title1;
    }

    public String getTitle(){
        return title;
    }


    public void setMessage(String message1){
        this.message=message1;
    }
    public String getMessage(){
        return message;
    }

}
