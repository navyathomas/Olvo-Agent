package com.example.root.olvoagent.ui.Route;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.RouteAddModel.RouteAddMain;
import com.example.root.olvoagent.ui.login.Login;

import java.io.IOException;

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
 * Created by root on 22/10/19.
 */

public class RouteAdd extends AppCompatActivity {
    private ImageView routeAddBackButton;
    private Button addRouteBtn;
    public EditText routeNamee, routeDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_add_route);

        routeDescription = findViewById(R.id.textView_desciption);
        routeAddBackButton = findViewById(R.id.img_back_route);
        addRouteBtn = findViewById(R.id.btn_route_ok);
        routeNamee = findViewById(R.id.textView_routeName);


        routeAddBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routeAddBack = new Intent(RouteAdd.this, RouteList.class);
                startActivity(routeAddBack);
                finish();
            }
        });


        addRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
                String employee = preferences.getString("employee_id", "");

                String routeName = routeNamee.getText().toString();
                String routeDescriptionn = routeDescription.getText().toString();

                addRoute(routeName, routeDescriptionn, employee);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent toRouteActivity = new Intent(this, RouteList.class);
        startActivity(toRouteActivity);
        finish();
    }


    private void addRoute(String routeName, String description, String routeEmployeeId) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
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
        Call<RouteAddMain> call = apiService.addRoute(routeName, description, routeEmployeeId);
        call.enqueue(new Callback<RouteAddMain>() {
            @Override
            public void onResponse(Call<RouteAddMain> call, retrofit2.Response<RouteAddMain> response) {

                Log.e("", response.body().toString());

                if (response.isSuccessful()) {
                    RouteAddMain routeAddMain = response.body();

                    if (routeAddMain.getRouteStatus()) {

                        SharedPreferences RouteAddPreference = getSharedPreferences("RouteAdd", MODE_PRIVATE);
                        SharedPreferences.Editor editor = RouteAddPreference.edit();

                        editor.putInt("status_code", response.body().getRouteStatusCode());

                        Toast.makeText(getApplicationContext(), "Successfully added Route", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getErrorRoute().getRouteTitle(), Toast.LENGTH_LONG).show();
                    }

                }

                else {
                    onTokenExpiry();
                    Toast.makeText(getApplicationContext(), response.body().getErrorRoute().getRouteTitle(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<RouteAddMain> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void onTokenExpiry() {
        SharedPreferences routeAdd = getSharedPreferences("RouteAdd", MODE_PRIVATE);
        int statusCode = routeAdd.getInt("status_code", 0);

        if (statusCode == 401) {
            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("token");


            Toast.makeText(getApplicationContext(), "Token Expired", Toast.LENGTH_LONG).show();
            Intent redirectToLogin = new Intent(RouteAdd.this, Login.class);
            startActivity(redirectToLogin);
        }
    }
}
