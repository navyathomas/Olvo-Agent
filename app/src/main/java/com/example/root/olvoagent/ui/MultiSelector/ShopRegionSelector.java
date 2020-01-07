package com.example.root.olvoagent.ui.MultiSelector;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.ShopFilters.MultiSelectorAdapters.ShopRegionSelectrAdapter;
import com.example.root.olvoagent.models.ListRegionModel.ListRegionMain;
import com.example.root.olvoagent.models.ListRegionModel.RegionData;

import java.io.IOException;
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
 * Created by root on 29/10/19.
 */

public class ShopRegionSelector extends DialogFragment {

    private RecyclerView listRegionRecycler;
    private ShopRegionSelectrAdapter shopRegionSelectrAdapter;

    public ShopRegionSelector() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multi_selector_shop, container, false);

        listRegionRecycler=view.findViewById(R.id.recyclr_add_shop_selector);
        regionList();


        return view;
    }




    public void onResume(){
        super.onResume();

        Window window=getDialog().getWindow();
        window.setLayout(420,540);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.TOP|Gravity.CENTER);
        window.setTitle("Select Region");
        window.setTitleColor(getResources().getColor(R.color.editText_background));

    }

    public void regionList(){

        SharedPreferences preferences1 = getActivity().getSharedPreferences("MyPreff", Context.MODE_PRIVATE);

        final String countryId=preferences1.getString("country_id","");
        Log.e("country id",countryId);



        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Request.Builder builder=request.newBuilder();
                Request request1=builder.build();
                return chain.proceed(request1);
            }
        }).addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ApiService.BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService apiService=retrofit.create(ApiService.class);
        Call<ListRegionMain> call=apiService.regionList(countryId);
        call.enqueue(new Callback<ListRegionMain>() {
            @Override
            public void onResponse(retrofit2.Call<ListRegionMain> call, retrofit2.Response<ListRegionMain> response) {

                if (response.isSuccessful()){
                    ListRegionMain listRegionMain=response.body();

                    if (listRegionMain.getRegionStatus()){

                        List<RegionData> regionDataList=listRegionMain.getRegionDatas();
                        listRegion(regionDataList);

                    }
                    else {

                        Toast.makeText(getActivity(),response.body().getRegionError().toString(),Toast.LENGTH_LONG).show();
                    }

                }
                else {

                }

            }

            @Override
            public void onFailure(retrofit2.Call<ListRegionMain> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void listRegion(List<RegionData> regionDataList){
       Context context=getActivity();
        shopRegionSelectrAdapter=new ShopRegionSelectrAdapter(context,regionDataList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listRegionRecycler.setLayoutManager(linearLayoutManager);
        listRegionRecycler.setAdapter(shopRegionSelectrAdapter);


    }


}
