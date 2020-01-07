package com.example.root.olvoagent.models.HomeAdModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 29/8/19.
 */

public class AdsModel {      //object->{}//

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;


    @SerializedName("promo_start")
    private String promoStart;

    @SerializedName("promo_end")
    private String promoEnd;


    public void setId(String id1){
        this.id=id1;
    }
    public String getId(){
        return id;
    }

    public void setName(String name1){
        this.name=name1;
    }

    public String getName(){
        return name;
    }

    public void setTitle(String title1){
        this.title=title1;
    }

    public String getTitle(){
        return title;
    }



    public void setImage(String image1){
        this.image=image1;
    }

    public String getImage(){
        return image;
    }


    public void setDescription(String description1){
        this.description=description1;
    }

    public String getDescription(){
        return description;
    }


    public void setPromoStart(String promoStart1){
        this.promoStart=promoStart1;
    }

    public String getPromoStart(){
        return promoStart;
    }


    public void setPromoEnd(String promoEnd1){
        this.promoEnd=promoEnd1;
    }

    public String getPromoEnd(){
        return promoEnd;
    }
}
