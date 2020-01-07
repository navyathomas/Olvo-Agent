package com.example.root.olvoagent.models.ListRegionModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 13/11/19.
 */

public class ListRegionMain {

    @SerializedName("status")
    private Boolean regionStatus;

    @SerializedName("status_code")
    private Integer regionStatusCode;

    @SerializedName("data")
    private List<RegionData> regionDatas;

    @SerializedName("error")
    private RegionError regionError;

    public void setRegionStatus(Boolean regionStatus1){
        this.regionStatus=regionStatus1;
    }
    public Boolean getRegionStatus(){
        return regionStatus;
    }

    public void setRegionStatusCode(Integer regionStatusCode1){
        this.regionStatusCode=regionStatusCode1;
    }
    public Integer getRegionStatusCode(){
        return regionStatusCode;
    }

    public void setRegionDatas(List<RegionData> regionDatas1){
        this.regionDatas=regionDatas1;
    }
    public List<RegionData> getRegionDatas(){
        return regionDatas;
    }

    public void setRegionError(RegionError regionError1){
        this.regionError=regionError1;
    }
    public RegionError getRegionError(){
        return regionError;
    }
}
