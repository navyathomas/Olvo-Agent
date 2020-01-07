package com.example.root.olvoagent.ui.shop_summary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.RechargeHistoryAdapter;
import com.example.root.olvoagent.models.RechargeHistory.HistoryMainModel;
import com.example.root.olvoagent.models.RechargeHistory.HistoryModel;
import com.example.root.olvoagent.ui.fragment.FilterFragment;
import com.example.root.olvoagent.ui.login.Login;
import com.example.root.olvoagent.ui.menu.MenuPage;

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
 * Created by root on 30/9/19.
 */

public class RechargeHistory extends Activity {

    private RechargeHistoryAdapter rechargeHistoryAdapter;
    private RecyclerView rechargeHistory;
    private ImageView imageBack;
    private RelativeLayout filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_history);


        imageBack = findViewById(R.id.img_back);
        filter = findViewById(R.id.rel_filter);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent filterPage = new Intent(RechargeHistory.this, FilterFragment.class);
                startActivity(filterPage);
                finish();

            }
        });


        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(RechargeHistory.this, MenuPage.class);
//                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                back.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(back);
                finish();
            }
        });


        rechargeHistory = findViewById(R.id.recharge_filer_recy);
        callRechargeApi();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent toMenuActivity = new Intent(this, MenuPage.class);
        startActivity(toMenuActivity);
        finish();
    }


    public void callRechargeApi() {

        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        final String employee = preferences.getString("employee_id", "");
        Log.e("employee", "" + employee);


        SharedPreferences preference2 = getSharedPreferences("Myprefff", MODE_PRIVATE);
        final String shopId = preference2.getString("_id", "");
        Log.e("id", "" + shopId);


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().addInterceptor(new Interceptor() {
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
        Call<HistoryMainModel> call = apiService.getHistory(employee, shopId);
        call.enqueue(new Callback<HistoryMainModel>() {
            @Override
            public void onResponse(Call<HistoryMainModel> call, retrofit2.Response<HistoryMainModel> response) {

                if (response.isSuccessful()) {
                    HistoryMainModel historyMainModel = response.body();

                    if (historyMainModel.getStatus()) {
                        Log.e("rechargeHistory", "" + historyMainModel);

                        setRechargeHistoryAdapter(response.body().getData().getHistoryModel());


                        SharedPreferences rechargeHistory = getSharedPreferences("Rechrge", MODE_PRIVATE);
                        SharedPreferences.Editor editor = rechargeHistory.edit();
                        editor.putInt("status_code", response.body().getStatus_code());


                        if (historyMainModel.getData().getCount()==0){

                            Toast.makeText(getApplicationContext(),"No shop available",Toast.LENGTH_LONG).show();
                        }

                    } else {

                    }
                } else {

                    Toast.makeText(getApplicationContext(), response.body().getErrorModel().toString(), Toast.LENGTH_LONG).show();
                    onTokenExpiry();

                }
            }

            @Override
            public void onFailure(Call<HistoryMainModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void setRechargeHistoryAdapter(List<HistoryModel> historyModels) {
        Context ccontext = getApplicationContext();
        rechargeHistoryAdapter = new RechargeHistoryAdapter(ccontext, historyModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rechargeHistory.setLayoutManager(linearLayoutManager);
        rechargeHistory.setAdapter(rechargeHistoryAdapter);
    }

    public void onTokenExpiry() {
        SharedPreferences preferencesRecharge = getSharedPreferences("Rechrge", MODE_PRIVATE);
        int StatusCode = preferencesRecharge.getInt("status_code", 200);

        if (StatusCode == 401) {
            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("token");

            Toast.makeText(getApplicationContext(), "Token Expired", Toast.LENGTH_LONG).show();
            Intent redirectToLogin = new Intent(RechargeHistory.this, Login.class);
            startActivity(redirectToLogin);
        }
    }
}