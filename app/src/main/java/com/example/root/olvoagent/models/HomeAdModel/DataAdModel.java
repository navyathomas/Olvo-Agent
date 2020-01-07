package com.example.root.olvoagent.models.HomeAdModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 29/8/19.
 */

public class DataAdModel {

    @SerializedName("count")
    private Integer count;

    @SerializedName("ads")
    private List<AdsModel> adsList=new ArrayList<>(); //array-> []//



    public void setCount(Integer count1){
        this.count=count1;
    }

    public Integer getCount(){
        return count;
    }

    public void  setAdsList(List<AdsModel> adsList1){
        this.adsList=adsList1;
    }

    public List<AdsModel> getAdsList(){
        return adsList;
    }
}
