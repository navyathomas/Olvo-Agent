package com.example.root.olvoagent.ui.ShopListPage;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.ShopListAdapter;
import com.example.root.olvoagent.models.ShopApiModel.ShopListModel;
import com.example.root.olvoagent.models.ShopApiModel.ShopMainModel;
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
 * Created by root on 18/9/19.
 */

public class ShopActivity extends AppCompatActivity {

    private ShopListAdapter shopListAdapter;
    private CardViewActivity cardViewActivity;

    private Context mcontext;
    private RecyclerView recycler_shopList;

    private RelativeLayout relativeAddShop;
    private ImageView imageBackButton;
    private LinearLayout linearRouteSelector;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        final FragmentManager fragmentManager = getFragmentManager();
        final SelectShopDialog selectShopDialog = new SelectShopDialog(); /** Class which extends DialogFragment ..**/


        linearRouteSelector = findViewById(R.id.linr_route_selector);

        linearRouteSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectShopDialog.show(fragmentManager, null);


            }
        });


        imageBackButton = findViewById(R.id.img_qick_rechrge_back);
        imageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ShopActivity.this, MenuPage.class);
                startActivity(back);
                finish();
            }
        });


        relativeAddShop = findViewById(R.id.relt_add_shop);
        relativeAddShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addShop = new Intent(ShopActivity.this, AddShop.class);
                startActivity(addShop);
                finish();
            }
        });


        recycler_shopList = (RecyclerView) findViewById(R.id.shop_list);
        callshopId();

    }


    /**
     * onBack Key pressed in device
     **/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toMenuActivity = new Intent(this, MenuPage.class);
        startActivity(toMenuActivity);
        finish();
    }


    //    /** Shop List Api**/
    public void callshopId() {

        Log.e("test", "test");
        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);

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

                setShopListAdapter(response.body().getDataModel().getShopListModels());

                if (response.isSuccessful()) {
                    ShopMainModel shopMainModel = response.body();

                    if (shopMainModel.getStatus()) {

                        SharedPreferences preference2 = getSharedPreferences("Myprefff", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preference2.edit();

                        editor.putString("_id", response.body().getDataModel().getShopListModels().get(0).getShopId());
                        editor.putInt("status_code",response.body().getStatusCode());
                        Log.e("size", response.body().getDataModel().getShopListModels().get(0).getShopId());

                        editor.commit();

                    } else {

//                        Toast.makeText(getApplicationContext(), "No shop available", Toast.LENGTH_LONG).show();
                    }

                } else {
                    onTokenExpiry();
                    Toast.makeText(getApplicationContext(), response.body().getErrorModels().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ShopMainModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void onTokenExpiry(){

        SharedPreferences preferences2=getSharedPreferences("ShopApi",MODE_PRIVATE);
        int statusCode=preferences2.getInt("status_code",200);
        Log.e("statuscode",""+statusCode);

        if (statusCode==401){
            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("token");

            Toast.makeText(getApplicationContext(),"Token Expired",Toast.LENGTH_LONG).show();
            Intent redirectToLogin=new Intent(ShopActivity.this, Login.class);
            startActivity(redirectToLogin);
        }
    }


    private void setShopListAdapter(List<ShopListModel> shopListModell) {
        Context ccontext = getApplicationContext();
        shopListAdapter = new ShopListAdapter(ccontext, shopListModell);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_shopList.setLayoutManager(linearLayoutManager);
        recycler_shopList.setAdapter(shopListAdapter);
    }

}