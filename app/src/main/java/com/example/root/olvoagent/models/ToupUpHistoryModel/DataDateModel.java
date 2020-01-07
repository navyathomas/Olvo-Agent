package com.example.root.olvoagent.models.ToupUpHistoryModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 21/10/19.
 */

public class DataDateModel {

    @SerializedName("count")
    private Integer count;


    @SerializedName("history")
     private List<HistoryDateModel> historyDateModels;

    @SerializedName("pageCount")
    private Integer pageCount;

    @SerializedName("totalCount")
    private Integer totalCount;

    public void setCount(Integer count1){
        this.count=count1;
    }
    public Integer getCount(){
        return count;
    }

    public void setHistoryDateModels(List<HistoryDateModel> historyDateModels){
        this.historyDateModels=historyDateModels;
    }
    public List<HistoryDateModel> getHistoryDateModels(){
        return historyDateModels;
    }

    public void setPageCount(Integer pageCount){
        this.pageCount=pageCount;
    }
    public Integer getPageCount(){
        return pageCount;
    }

    public void setTotalCount(Integer count){
        this.totalCount=count;
    }
    public Integer getTotalCount(){
        return totalCount;
    }
}
