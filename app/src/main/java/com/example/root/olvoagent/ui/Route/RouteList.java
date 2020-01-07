package com.example.root.olvoagent.ui.Route;

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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.RouteListAdapter;
import com.example.root.olvoagent.models.RouteApiModel.DataArrayModel;
import com.example.root.olvoagent.models.RouteApiModel.RouteMainModel;
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
 * Created by root on 20/9/19.
 */

public class RouteList extends AppCompatActivity {

    private RouteListAdapter routeListAdapter;
    private RecyclerView recyclerViewRouteSelector;
    private RelativeLayout relativeAddRoute;
    private ImageView routeBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_route);


        relativeAddRoute = findViewById(R.id.rel_add_route);
        relativeAddRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRouteActivity = new Intent(RouteList.this, RouteAdd.class);
                startActivity(addRouteActivity);
                finish();
            }
        });


        recyclerViewRouteSelector = findViewById(R.id.recy_route);   //recycler/ in acitivity_select_rout page//
        callRoutApi();


        routeBack = findViewById(R.id.img_back);
        routeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routeBackToHome = new Intent(RouteList.this, MenuPage.class);
                routeBackToHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(routeBackToHome);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent toMenuActivity = new Intent(this, MenuPage.class);
        startActivity(toMenuActivity);
        finish();
    }


    public void callRoutApi() {

        final SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        final String employeeId = preferences.getString("employee_id", "");
        Log.e("routeEmployeeId", employeeId);


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

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        ApiService apiService = retrofit.create(ApiService.class);

        Call<RouteMainModel> call = apiService.getRoute(employeeId);
        call.enqueue(new Callback<RouteMainModel>() {

            @Override
            public void onResponse(Call<RouteMainModel> call, retrofit2.Response<RouteMainModel> response) {

                Log.e("log", "" + response.body());

                if (response.isSuccessful()) {
                    RouteMainModel routeMainModel = response.body();

                    if (routeMainModel.getStatus()) {
                        List<DataArrayModel> dataArrayModels = routeMainModel.getDataArrayModels();
                        setRouteListAdapter(dataArrayModels);

                        SharedPreferences preferencesRoute = getSharedPreferences("RouteApi", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencesRoute.edit();

                        editor.putString("_id", response.body().getDataArrayModels().get(0).getRouteId());
                        editor.putInt("status_code",response.body().getStatusCode());
                        editor.commit();

                    } else {

                    }


                } else {
                    onTokenExpiry();
                    Toast.makeText(getApplicationContext(), response.body().getErrorModel().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RouteMainModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void setRouteListAdapter(List<DataArrayModel> dataArrayModels) {

        Context context = getApplicationContext();
        routeListAdapter = new RouteListAdapter(context, dataArrayModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRouteSelector.setLayoutManager(linearLayoutManager);
        recyclerViewRouteSelector.setAdapter(routeListAdapter);
    }

    public void onTokenExpiry(){
        SharedPreferences preferencesRoute = getSharedPreferences("RouteApi", MODE_PRIVATE);
        int routeListStatusCode=preferencesRoute.getInt("status_code",0);

        if (routeListStatusCode==401){

            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("token");

            Toast.makeText(getApplicationContext(),"Token Expired",Toast.LENGTH_LONG).show();
            Intent redirectToLogin=new Intent(RouteList.this, Login.class);
            startActivity(redirectToLogin);
        }

    }


}
