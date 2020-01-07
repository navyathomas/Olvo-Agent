package com.example.root.olvoagent.models.ListRegionModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 13/11/19.
 */

public class RegionData {

    @SerializedName("_id")
    private String regionId;

    @SerializedName("name")
    private String regionName;

    @SerializedName("status")
    private Boolean regionStatus;

    public void setRegionId(String regionId1){
        this.regionId=regionId1;
    }
    public String getRegionId(){
        return regionId;
    }

    public void setRegionName(String regionName1){
        this.regionName=regionName1;
    }
    public String getRegionName(){
        return regionName;
    }

    public void setRegionStatus(Boolean regionStatus1){
        this.regionStatus=regionStatus1;
    }
    public Boolean getRegionStatus(){
        return regionStatus;
    }
}
