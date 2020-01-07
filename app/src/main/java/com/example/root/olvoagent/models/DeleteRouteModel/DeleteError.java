package com.example.root.olvoagent.models.DeleteRouteModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 7/11/19.
 */

public class DeleteError {

    @SerializedName("title")
    private String deleteTitle;

    @SerializedName("message")
    private String deleteMessage;

    public void setDeleteTitle(String deleteTitle1){
        this.deleteTitle=deleteTitle1;
    }
    public String getDeleteTitle(){
        return deleteTitle;
    }


    public void setDeleteMessage(String deleteMessage1){
        this.deleteMessage=deleteMessage1;
    }
    public String getDeleteMessage(){
        return deleteMessage;
    }


}
