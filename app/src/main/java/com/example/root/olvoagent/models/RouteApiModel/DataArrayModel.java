package com.example.root.olvoagent.models.RouteApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 20/9/19.
 */

public class DataArrayModel {

    @SerializedName("_id")
    private String routeId;

    @SerializedName("name")
    private String routeName;

    @SerializedName("description")
    private String routeDescription;

    @SerializedName("employee_id")
    private String routeEmployeeId;



    public void setRouteId(String routeId1){
        this.routeId=routeId1;
    }
    public String getRouteId(){
        return routeId;
    }


    public void  setRouteName(String routeName1){
        this.routeName=routeName1;
    }
    public String getRouteName(){
        return routeName;
    }

    public void  setRouteDescription(String routeDescription1){
        this.routeDescription=routeDescription1;
    }
    public String getRouteDescription(){
        return routeDescription;
    }


    public void setRouteEmployeeId(String routeEmployeeId1){
        this.routeEmployeeId=routeEmployeeId1;
    }
    public String getRouteEmployeeId(){
        return  routeEmployeeId;
    }

}
