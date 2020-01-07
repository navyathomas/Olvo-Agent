package com.example.root.olvoagent.models.RouteAddModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 7/11/19.
 */

public class ErrorRoute {

    @SerializedName("title")
    private String routeTitle;

    @SerializedName("message")
    private String routeMessage;

    public void setRouteTitle(String routeTitle1){
        this.routeTitle=routeTitle1;
    }
    public String getRouteTitle(){
        return routeTitle;
    }

    public void setRouteMessage(String routeMessage1){
        this.routeMessage=routeMessage1;
    }
    public String getRouteMessage(){
        return getRouteMessage();
    }



}
