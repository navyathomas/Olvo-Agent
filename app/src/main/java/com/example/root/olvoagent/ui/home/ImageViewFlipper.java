package com.example.root.olvoagent.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.ViewFlipperPager;
import com.example.root.olvoagent.models.HomeAdModel.AdsModel;
import com.example.root.olvoagent.models.HomeAdModel.HomeMainModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 17/9/19.
 */

public class ImageViewFlipper extends Activity {

    private ViewFlipperPager viewFlipperPager;
    private Context context;
    private List<AdsModel> adsModels=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper_viewpager);

        ViewPager viewPager=(ViewPager)findViewById(R.id.view_pager);
         viewFlipperPager=new ViewFlipperPager(ImageViewFlipper.this,adsModels);
        callApi();
        viewPager.setAdapter(viewFlipperPager);

    }


    private void callApi() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();  //used for log //
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {  //For Logging API calls//
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {

                Request request = chain.request();
                Request.Builder builder = request.newBuilder();

                Request request1 = builder.build();
                return chain.proceed(request1);
            }
        }).addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        ApiService apiService = retrofit.create(ApiService.class);

        Call<HomeMainModel> call = apiService.getAd("5d54f40eb3a8b57206998180");
        call.enqueue(new Callback<HomeMainModel>() {

            @Override

            public void onResponse(Call<HomeMainModel> call, retrofit2.Response<HomeMainModel> response) {

//                setAdHomeAdapter(response.body().getDataModels().getAdsList());


                Log.e("", "" + response.body());

            }

            @Override
            public void onFailure(Call<HomeMainModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



    private void setViewFlipperPager(List<AdsModel> adsModels){
        Context context=getApplicationContext();
        viewFlipperPager=new ViewFlipperPager(context,adsModels);
    }


}
