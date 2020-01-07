package com.example.root.olvoagent.models.ProfileApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 17/9/19.
 */

public class ProfileErrorModel {

    @SerializedName("title")
    private String profileTitle;


    @SerializedName("message")
    private String profileMessage;

    public void setProfileTitle(String profileTitle){
        this.profileTitle=profileTitle;
    }
    public String getProfileTitle(){
        return profileTitle;
    }

    public void setProfileMessage(String profileMessage1){
        this.profileMessage=profileMessage1;
    }
    public String getProfileMessage(){
        return profileMessage;
    }

}
