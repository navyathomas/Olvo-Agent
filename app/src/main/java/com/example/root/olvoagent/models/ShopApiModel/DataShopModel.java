package com.example.root.olvoagent.models.ShopApiModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/9/19.
 */

public class DataShopModel {

    @SerializedName("shop_list")
    private List<ShopListModel> shopListModels;


    @SerializedName("pageCount")
    private Integer pageCount;

    @SerializedName("totalCount")
    private Integer totalCount;


    public void  setShopListModels(ArrayList<ShopListModel> shopListModels1){
        this.shopListModels=shopListModels1;
    }
    public List<ShopListModel> getShopListModels(){
        return shopListModels;
    }

    public void setPageCount(Integer pageCount1){
        this.pageCount=pageCount1;
    }
    public Integer getPageCount(){
        return pageCount;
    }

    public void  setTotalCount(Integer totalCount1){
        this.totalCount=totalCount1;
    }
    public Integer getTotalCount(){
        return totalCount;
    }
}
