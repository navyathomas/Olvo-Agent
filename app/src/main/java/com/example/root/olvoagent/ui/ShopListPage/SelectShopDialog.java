package com.example.root.olvoagent.ui.ShopListPage;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
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
import com.example.root.olvoagent.adapters.CardViewAdapter;
import com.example.root.olvoagent.models.ShopApiModel.ShopListModel;
import com.example.root.olvoagent.models.ShopApiModel.ShopMainModel;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by root on 22/10/19.
 */

public class SelectShopDialog extends DialogFragment {
    private RecyclerView customRecyclerView;
    private CardViewAdapter cardViewAdapter;
    public List<ShopListModel> shopListModelCardView;

    private static SelectShopDialog instance;


    public static SelectShopDialog getInstance() {
        return instance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_select_route, viewGroup, false);


        //For RecyclerView//
        customRecyclerView = view.findViewById(R.id.recyclr_select_route);

        callshopId();
        //FragmentManager fm = getFragmentManager();
        return view;
    }


    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(470, 500);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    public void callshopId() {

        Log.e("test", "test");
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPref", MODE_PRIVATE);

        final String accessToken = preferences.getString("token", "");
        final String employeeId = preferences.getString("employee_id", "");

        Log.e("token", accessToken);
        Log.e("employeeId", employeeId);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + accessToken);


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

        Call<ShopMainModel> call = apiService.getEmployee(employeeId);
        call.enqueue(new Callback<ShopMainModel>() {
            @Override
            public void onResponse(Call<ShopMainModel> call, retrofit2.Response<ShopMainModel> response) {

                Log.e("shop", "" + response.body());

                setCardViewList(response.body().getDataModel().getShopListModels());

                if (response.isSuccessful()) {
                    ShopMainModel shopMainModel = response.body();

                    if (shopMainModel.getStatus()) {

                        SharedPreferences preference2 = getActivity().getSharedPreferences("Myprefff", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preference2.edit();

                        editor.putString("_id", response.body().getDataModel().getShopListModels().get(0).getShopId());
                        Log.e("size", response.body().getDataModel().getShopListModels().get(0).getShopId());

                        editor.commit();

                    } else {

                        Toast.makeText(getActivity(), "No shop available", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), response.body().getErrorModels().toString(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ShopMainModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void setCardViewList(List<ShopListModel> shopListModelCard) {
        Context cardContext = getActivity(); //Fragment using getActivity()//
        cardViewAdapter = new CardViewAdapter(cardContext, shopListModelCard);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());  //getApplicationContext is not applicable in fragment..Only getActivity//
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        customRecyclerView.setLayoutManager(linearLayoutManager);
        customRecyclerView.setAdapter(cardViewAdapter);

    }


}
