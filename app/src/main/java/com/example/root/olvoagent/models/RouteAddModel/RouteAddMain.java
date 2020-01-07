package com.example.root.olvoagent.models.RouteAddModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 7/11/19.
 */

public class RouteAddMain {

    @SerializedName("status")
    private Boolean routeStatus;

    @SerializedName("status_code")
    private Integer routeStatusCode;

    @SerializedName("data")
    private RouteData routeData;

    @SerializedName("error")
    private ErrorRoute errorRoute;



    public void setRouteStatus(Boolean routeStatus){
       this.routeStatus=routeStatus;
    }
    public Boolean getRouteStatus(){
        return routeStatus;
    }

    public void setRouteStatusCode(Integer routeStatusCode1){
        this.routeStatusCode=routeStatusCode1;
    }
    public Integer getRouteStatusCode(){
        return routeStatusCode;
    }

    public RouteData getRouteData(){
        return routeData;
    }
    public void setRouteData(RouteData routeData1){
        this.routeData=routeData1;
    }

    public void setErrorRoute(ErrorRoute errorRoute1){
        this.errorRoute=errorRoute1;
    }
    public ErrorRoute getErrorRoute(){
        return errorRoute;
    }
}
