package com.example.root.olvoagent.ui.MultiSelector;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.ShopFilters.MultiSelectorAdapters.ShopCategorySelectorAdapter;
import com.example.root.olvoagent.models.ListCategoryShop.DataShopCategory;
import com.example.root.olvoagent.models.ListCategoryShop.ListShopCategoryModel;

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
 * Created by root on 13/11/19.
 */

public class ShopCategorySelector extends DialogFragment {

    private RecyclerView shopCategory;
    private ShopCategorySelectorAdapter shopCategorySelectorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.multi_selector_shop,viewGroup,false);
        shopCategory=view.findViewById(R.id.recyclr_add_shop_selector);
        shopCategory();
        return view;
    }


    public void onResume(){
        super.onResume();
        Window window=getDialog().getWindow();
        window.setGravity(Gravity.TOP|Gravity.CENTER);
        window.setLayout(390,580);
        window.setTitle("Select Shop Type");
        window.setTitleColor(getResources().getColor(R.color.editText_background));
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    public void shopCategory() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

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
        Call<ListShopCategoryModel> call=apiService.shopCategory();
        call.enqueue(new Callback<ListShopCategoryModel>() {
            @Override
            public void onResponse(Call<ListShopCategoryModel> call, retrofit2.Response<ListShopCategoryModel> response) {

                if (response.isSuccessful()){

                    ListShopCategoryModel listShopCategoryModel=response.body();
                    if (listShopCategoryModel.getShopCategoryStatus()){

                        List<DataShopCategory> dataShopCategories=listShopCategoryModel.getDataShopCategories();

                        setShopCategory(dataShopCategories);
                    }
                    else {

                        Toast.makeText(getActivity(),"An Error Occured",Toast.LENGTH_LONG).show();
                    }
                }

                else {
                    Toast.makeText(getActivity(),response.body().getErrorCategory().toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListShopCategoryModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void setShopCategory(List<DataShopCategory> dataShopCategories){
        Context mcontext=getActivity();
        shopCategorySelectorAdapter=new ShopCategorySelectorAdapter(mcontext,dataShopCategories);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopCategory.setLayoutManager(linearLayoutManager);
        shopCategory.setAdapter(shopCategorySelectorAdapter);
    }
}