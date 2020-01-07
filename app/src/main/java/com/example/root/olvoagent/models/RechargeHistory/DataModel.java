package com.example.root.olvoagent.models.RechargeHistory;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 30/9/19.
 */

public class DataModel {

    @SerializedName("count")
    private Integer count;

    @SerializedName("history")
    private List<HistoryModel> historyModel;

    @SerializedName("pageCount")
    private Integer pageCount;

    @SerializedName("totalCount")
    private Integer totalCount;


    public void setCount(Integer count1){
        this.count=count1;
    }
    public Integer getCount(){
        return  count;
    }

    public void setHistoryModel(List<HistoryModel> historyModel1){
        this.historyModel=historyModel1;
    }
    public List<HistoryModel> getHistoryModel(){
        return  historyModel;
    }

    public void setPageCount(Integer pageCount1){
        this.pageCount=pageCount1;
    }
    public Integer getPageCount(){
        return pageCount;
    }

    public void setTotalCount(Integer totalCount1){
        this.totalCount=totalCount1;
    }
    public Integer getTotalCount(){
        return totalCount;
    }
}
