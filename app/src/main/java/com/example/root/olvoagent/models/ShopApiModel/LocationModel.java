package com.example.root.olvoagent.models.ShopApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 2/9/19.
 */

public class LocationModel {

    @SerializedName("latitude")
    private Double lat;

    @SerializedName("longitude")
    private Double lon;

    public void setLat(Double lat1){
        this.lat=lat1;
    }
    public Double getLat(){
        return lat;
    }

    public void setLon(Double lon1){
        this.lon=lon1;
    }

    public Double getLon(){
        return lon;
    }
}
