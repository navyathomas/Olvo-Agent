package com.example.root.olvoagent.ui.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.AdHomeAdapter;
import com.example.root.olvoagent.models.HomeAdModel.AdsModel;
import com.example.root.olvoagent.models.HomeAdModel.HomeMainModel;
import com.example.root.olvoagent.models.ProfileApiModel.ProfileMainModel;
import com.example.root.olvoagent.ui.Route.RouteList;
import com.example.root.olvoagent.ui.ShopListPage.ShopActivity;
import com.example.root.olvoagent.ui.invoice.Invoice;
import com.example.root.olvoagent.ui.login.Login;
import com.example.root.olvoagent.ui.payment.PaymentRequest;
import com.example.root.olvoagent.ui.profile.Profile;
import com.example.root.olvoagent.ui.shop_summary.RechargeHistory;

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
 * Created by root on 26/8/19.
 */

public class MenuPage extends Activity implements View.OnClickListener {

    private AdHomeAdapter adHomeAdapter;
    private RecyclerView home_ad;

    private TextView relativetextShop, textShopName, textRechargCredits, textCreditscredits;
    private TextView tvshopcredits;
    private DrawerLayout drawerMenu;
    private ImageView imageMenu, imageLogout;
    private Button btnLogout;
    private NavigationView navigationView;
    private RelativeLayout relatveHome, relatveProfile, relativeProfit, relativeInvoice,
            relative_route, relatveRechargeHistory,relativePaymentRequest;


    private RelativeLayout rechargeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        home_ad = (RecyclerView) findViewById(R.id.rycl_ad);
        callApi();


        rechargeButton = findViewById(R.id.linr_credits);
        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recharge = new Intent(MenuPage.this, ShopActivity.class);
                startActivity(recharge);
            }
        });


//
//
        textShopName = findViewById(R.id.tv_shop_name); //employeeName//
        callprofile();


        tvshopcredits = findViewById(R.id.tv_shop_credits);    //shopText 1//
        callprofile();


        textRechargCredits = findViewById(R.id.tv_recharge_credits);  //recharge 2//
        callprofile();

        textCreditscredits = findViewById(R.id.tv_credits_credits);    //credits 3//
        callprofile();


        drawerMenu = (DrawerLayout) findViewById(R.id.drawer_menu);
        imageMenu = (ImageView) findViewById(R.id.img_menu);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        relatveProfile = (RelativeLayout) findViewById(R.id.relt_profile);
        relativeProfit = (RelativeLayout) findViewById(R.id.relt_profit);


        imageMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (drawerMenu.isDrawerOpen(GravityCompat.START)) {
                    drawerMenu.closeDrawers();
                } else {
                    drawerMenu.openDrawer(GravityCompat.START);
                }
            }
        });

        init();
        clickAction();
    }


    public void isLoggedOut() {
        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        finish();

    }


    private void setAdHomeAdapter(List<AdsModel> adHomeMainModels) {
        Context mcontext = getApplicationContext();
        adHomeAdapter = new AdHomeAdapter(mcontext, adHomeMainModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        home_ad.setLayoutManager(linearLayoutManager);
        home_ad.setAdapter(adHomeAdapter);
    }

    private void init() {
        relatveProfile = findViewById(R.id.relt_profile);
        relatveHome = findViewById(R.id.relt_home);
        btnLogout = findViewById(R.id.btn_logout);
        relativeProfit = findViewById(R.id.relt_profit);
        relatveRechargeHistory = findViewById(R.id.relt_history);
        relative_route = findViewById(R.id.relt_route);
        relativePaymentRequest=findViewById(R.id.payment_request);
        relativeInvoice=findViewById(R.id.innvoice);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relt_profile:
                Intent profileActivity = new Intent(MenuPage.this, ShopActivity.class);
                startActivity(profileActivity);
                finish();
                closeDrawers();
                break;

            case R.id.btn_logout:
                closeDrawers();
                Intent logout = new Intent(MenuPage.this, Login.class);
                isLoggedOut();
                startActivity(logout);
                finish();
                break;

            case R.id.relt_home:
                closeDrawers();
                break;


            case R.id.relt_profit:
                Intent profit = new Intent(MenuPage.this, Profile.class);
                startActivity(profit);
                finish();
                closeDrawers();
                break;

            case R.id.relt_history:
                Intent rechargeHistory = new Intent(MenuPage.this, RechargeHistory.class);
                startActivity(rechargeHistory);
                finish();
                closeDrawers();
                break;


            case R.id.payment_request:
                Intent paymentRequest=new Intent(MenuPage.this, PaymentRequest.class);
                startActivity(paymentRequest);
                finish();
                closeDrawers();
                break;

            case R.id.innvoice:
                Intent invoice=new Intent(MenuPage.this, Invoice.class);
                startActivity(invoice);
                finish();
                break;


            case R.id.relt_route:
                Intent route = new Intent(MenuPage.this, RouteList.class);
                startActivity(route);
                finish();
                closeDrawers();
                break;
        }
    }


    private void clickAction() {
        relatveProfile.setOnClickListener(this);
        relatveHome.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        relativeProfit.setOnClickListener(this);
        relatveRechargeHistory.setOnClickListener(this);
        relativePaymentRequest.setOnClickListener(this);
        relative_route.setOnClickListener(this);
        relativeInvoice.setOnClickListener(this);
        //  relativetextShop.setOnClickListener(this);
    }



    private void closeDrawers() {
        if (drawerMenu.isDrawerOpen(GravityCompat.START)) {
            drawerMenu.closeDrawers();
            return;
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Ad Api
     **/
    private void callApi() {

        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        final String employeeId = preferences.getString("employee_id", "");

        Log.e("id", employeeId);


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

        Call<HomeMainModel> call = apiService.getAd(employeeId);    //5d54f40eb3a8b57206998180  ad shopId//
        call.enqueue(new Callback<HomeMainModel>() {

            @Override

            public void onResponse(Call<HomeMainModel> call, retrofit2.Response<HomeMainModel> response) {

                // setAdHomeAdapter(response.body().getDataModels().getAdsList());


                Log.e("", "" + response.body());

                //List<AdsModel> adsModels=response.body().getDataModels().getAdsList();
//                adHomeAdapter=new AdHomeAdapter(getApplicationContext(),adsModels);
//
                //response.body();
            }

            @Override
            public void onFailure(Call<HomeMainModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    /**
     * Profile Api
     **/
    public void callprofile() {

        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        final String accessToken = preferences.getString("token", "");  //final means we cannot change the value.It is static.//
        final String employeeId = preferences.getString("employee_id", "");


        Log.e("access", accessToken);
        Log.e("id", employeeId);


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
        Call<ProfileMainModel> call = apiService.getProfile(employeeId);
        call.enqueue(new Callback<ProfileMainModel>() {
            @Override
            public void onResponse(Call<ProfileMainModel> call, retrofit2.Response<ProfileMainModel> response) {

                if (response.isSuccessful()) {

                    Log.e("profile", "" + response.body());

                    ProfileMainModel profileMainModel = response.body();

                    if (profileMainModel.getStatus()) {

                        SharedPreferences preferences1 = getSharedPreferences("Profile", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = preferences1.edit();
                        editor1.putInt("status_code",  response.body().getStatusCode());


                        textShopName.setText(profileMainModel.getDataModel().getEmployeeName());
                        tvshopcredits.setText("" + profileMainModel.getDataModel().getShpCount());
                        textRechargCredits.setText("" + profileMainModel.getDataModel().getTotalRechargeCount());
                        textCreditscredits.setText("" + profileMainModel.getDataModel().getTotalSales());

                    } else {

                        Toast.makeText(getApplicationContext(), response.body().getErrorModel().getProfileTitle(), Toast.LENGTH_LONG).show();
                    }


                } else {
                    onTokenExpiry();

                    Toast.makeText(getApplicationContext(), response.body().getErrorModel().getProfileMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ProfileMainModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }


    public void onTokenExpiry(){
        SharedPreferences preferencesMenu=getSharedPreferences("Profile",MODE_PRIVATE);
        int statusCodeProfile=preferencesMenu.getInt("status_code",200);

        if (statusCodeProfile==401){

            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("token");

            Toast.makeText(getApplicationContext(),"Token Expired",Toast.LENGTH_LONG).show();
            Intent RedirectToLogin=new Intent(MenuPage.this,Login.class);
            startActivity(RedirectToLogin);
        }


    }
}
