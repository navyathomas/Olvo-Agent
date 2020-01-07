package com.example.root.olvoagent.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.ProfileApiModel.ProfileMainModel;
import com.example.root.olvoagent.ui.login.Login;
import com.example.root.olvoagent.ui.menu.MenuPage;

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
 * Created by root on 28/8/19.
 */

public class Profile extends AppCompatActivity {

    ImageView imageBack;
    private TextView phoneNumber, editInfoName, editInfoEmail, editInfoCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        imageBack = (ImageView) findViewById(R.id.img_back);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Profile.this, MenuPage.class);
//                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                back.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(back);
                finish();
            }
        });

        phoneNumber = findViewById(R.id.edt_phneNumber);
        callprofile();

        editInfoName = findViewById(R.id.edt_info_name);
        callprofile();

        editInfoEmail = findViewById(R.id.edt_info_email);
        callprofile();

        editInfoCountry = findViewById(R.id.edt_info_country);
        callprofile();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent toMenuActivity = new Intent(this, MenuPage.class);
        startActivity(toMenuActivity);
        finish();
    }


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


                Log.e("profileApi", " " + response.body());

                if (response.isSuccessful()) {

                    ProfileMainModel profileMainModel = response.body();

                    if (profileMainModel.getStatus()) {


                        /**For setting currencyIso**/
                        SharedPreferences preferences1 = getSharedPreferences("MyPreff", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences1.edit();

                        /** Setting in ShopActivity on the response.**/
                        editor.putString("currency_iso", response.body().getDataModel().getCurrencyIso());
                        editor.putString("country_id",response.body().getDataModel().getShopCountryId());
                        editor.putInt("status_code",response.body().getStatusCode());
                        editor.commit();


                        Log.e("iso", response.body().getDataModel().getCurrencyIso());


                        phoneNumber.setText("" + profileMainModel.getDataModel().getMobileNumberr());
                        editInfoName.setText(profileMainModel.getDataModel().getEmployeeName());
                        editInfoEmail.setText(profileMainModel.getDataModel().getEmail());
                        editInfoCountry.setText(profileMainModel.getDataModel().getCountryName());

                    } else {

                    }
                } else {
                    onTokenExpiry();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileMainModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void onTokenExpiry(){
        SharedPreferences preferencesProfile=getSharedPreferences("MyPreff",MODE_PRIVATE);
        int statusCodeProfile=preferencesProfile.getInt("status_code",200);

        if (statusCodeProfile==401){

            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("token");


            Toast.makeText(getApplicationContext(),"Token Expired",Toast.LENGTH_LONG).show();
            Intent redirectToLogin=new Intent(Profile.this, Login.class);
            startActivity(redirectToLogin);
        }
    }


}
