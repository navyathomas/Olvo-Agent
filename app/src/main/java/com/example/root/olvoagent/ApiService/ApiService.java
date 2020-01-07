package com.example.root.olvoagent.ApiService;

import com.example.root.olvoagent.models.AddShopModel.AddShopMainModel;
import com.example.root.olvoagent.models.DeleteRouteModel.DeleteMain;
import com.example.root.olvoagent.models.HomeAdModel.HomeMainModel;
import com.example.root.olvoagent.models.ListCategoryShop.ListShopCategoryModel;
import com.example.root.olvoagent.models.ListRegionModel.ListRegionMain;
import com.example.root.olvoagent.models.LoginApiModel.LoginMainModel;
import com.example.root.olvoagent.models.InvoiceHistory.PaymentHistoryMain;
import com.example.root.olvoagent.models.ProfileApiModel.ProfileMainModel;
import com.example.root.olvoagent.models.RechargeHistory.HistoryMainModel;
import com.example.root.olvoagent.models.RouteAddModel.RouteAddMain;
import com.example.root.olvoagent.models.RouteApiModel.RouteMainModel;
import com.example.root.olvoagent.models.ShopApiModel.ShopMainModel;
import com.example.root.olvoagent.models.ToupUpHistoryModel.DateToFromMainApi;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 26/8/19.
 */

public interface ApiService {

    String BaseUrl = "http://192.168.0.209:8000/";


    /** .. Login Api ..**/
    /*** Field parameter works with POST parameter only. **/

    /**
     * FormUrlEncoded / Field should be declared as parameter..It is only specified in Http method with request body(POST)..
     **/

    @FormUrlEncoded
    @POST("v2/salesman/login")
    Call<LoginMainModel> getLogin(@Field("email") String email, @Field("password") String password);


    /** .. Ads Api ..**/
    /***
     * Path parameter
     **/
    // shop id is replaced by employee id..//
    @GET("v1/ads/banner/{employeeId}")
    Call<HomeMainModel> getAd(@Path("employeeId") String shopId);


    //[
    /** .. ShopList Api .. **/
    /***
     * Path parameter
     **/

    @GET("v2/salesman/shop/list/{employeeId}")
    Call<ShopMainModel> getEmployee(@Path("employeeId") String employeeId);


    /**
     * ..AddShopApi..
     **/

    @FormUrlEncoded
    @POST("v2/salesman/shop/{employeeId}")
    Call<AddShopMainModel> addShop(@Path("employeeId") String employeeId, @Field("name") String name, @Field("email") String email,
                                   @Field("country_code") String countryCode, @Field("mobile_number") String mobileNumber
            , @Field("region") String region, @Field("shop_status") long shopStatus, @Field("enable_voip") Integer enableVoip,
                                   @Field("enable_mobile") Integer enableMobile, @Field("route_id") String routId,
                                   @Field("threshold") String threshold, @Field("shop_category") String shopCategory);

    //]

    /**** Path parameter**/
    /**
     * .. Profile Api ..
     **/

    @POST("/v2/salesman/profile/{employeeId}")
    Call<ProfileMainModel> getProfile(@Path("employeeId") String employeeId);


    /***.. Path parameters..**/

    /**
     * Route Api
     **/

    @GET("v2/route/{employeeId}")
    Call<RouteMainModel> getRoute(@Path("employeeId") String employeeId);


    /***
     * Recharge History Api
     **/

    @GET("v2/recharge/topup/history")
    Call<HistoryMainModel> getHistory(@Query("employeeId") String employee, @Query("_id") String shopId);


    /**
     * Recharge Filter Api ..
     **/

    // changed from topup history to shop list.//

    @GET("v2/salesman/shop/list/{employeeId}")
    Call<DateToFromMainApi> getTopup(@Path("employeeId") String employeeId);


    /**
     * Route Add Activity..
     **/
    @FormUrlEncoded
    @POST("v2/route")
    Call<RouteAddMain> addRoute(@Field("name") String routeName, @Field("description") String description, @Field("employee_id") String routeEmployeeId);


    /**
     * Route Delete Api ..
     **/

    @DELETE("v2/route/{routeId}")
    Call<DeleteMain> deleteRoute(@Path("routeId") String routeId);


    /** List Shop Category Api**/
    @GET("v2/shop/category/list")
    Call<ListShopCategoryModel> shopCategory();


    /**List Region Api**/
    @GET("v2/region/list/{countryId}")
    Call<ListRegionMain> regionList(@Path("countryId") String countryId);



    /**Invoice History**/
    @FormUrlEncoded
    @POST("/v1/payment/salesmanPaymentHistory")
    Call<PaymentHistoryMain> invoiceHistory(@Field("employee_id") String employeeId,@Field("type") String type);



}
