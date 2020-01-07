package com.example.root.olvoagent.models.ShopApiModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 2/9/19.
 */

public class ShopListModel {

 @SerializedName("_id")
 private String shopId;

 @SerializedName("name")
 private String shopName;

 @SerializedName("email")
 private String shopEmail;

 @SerializedName("credit_balance")
 private Double shopCredit;

 @SerializedName("wallet_balance")
 private Double walletBalance;

 @SerializedName("country_id")
 private String countryId;

 @SerializedName("country_name")
 private String countryName;

 @SerializedName("mobile_number")
 private String mobile_number;

 @SerializedName("country_code")
 private String country_code;

 @SerializedName("region_id")
 private String region_id;

 @SerializedName("region_name")
 private String region_name;


 @SerializedName("enable_voip")
 private Boolean enable_voip;

 @SerializedName("enable_mobile")
 private Boolean enable_mobile;

 @SerializedName("shop_status")
 private Integer shop_status;

 @SerializedName("location")
 private LocationModel location;


    @SerializedName("route_name")
    private String route_name;

/**Id**/
     public void setShopId(String shopId1) {
     this.shopId = shopId1;
     }

     public String getShopId(){
      return  shopId;
     }


/**name**/
      public void setShopName(String shopName1){
       this.shopName=shopName1;
      }
      public String getShopName(){
       return shopName;
      }

 /**email**/
      public void setShopEmail(String shopEmail1){
       this.shopEmail=shopEmail1;
      }
      public String getShopEmail(){
       return shopEmail;
      }
 /**shopcredit**/

      public void setShopCredit(Double shopCredit1){
       this.shopCredit=shopCredit1;
      }
      public Double getShopCredit(){
       return shopCredit;
      }


/**wallet balance**/

    public Double getWalletBalance(){
     return walletBalance;
    }

    public void setWalletBalance(Double walletBalance1){
     this.walletBalance=walletBalance1;
    }

 /**location**/

     public LocationModel getLocation(){
      return  location;
     }

     public void setLocation(LocationModel locationModel){
      this.location=locationModel;
     }

    /**shopStatus**/

        public Integer getShop_status(){
         return shop_status;
        }

        public void setShop_status(Integer shop_status1){
         this.shop_status=shop_status1;
        }


    /**mobile number**/
   public void setMobile_number(String mobile_number){
       this.mobile_number=mobile_number;
   }
    public String getMobile_number(){
        return mobile_number;
    }

    /** country name **/
    public void setCountryName(String countryName){
        this.countryName=countryName;
    }
    public String getCountryName(){
        return countryName;
    }

    /**Region Name **/
    public void  setRegion_name(String region_name){
        this.region_name=region_name;
    }
    public String getRegion_name(){
        return region_name;
    }

    /** Route Name **/
    public void setRoute_name(String route_name){
        this.route_name=route_name;
    }
    public String getRoute_name(){
        return route_name;
    }
}

