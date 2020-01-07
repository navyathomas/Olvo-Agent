package com.example.root.olvoagent.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.ui.menu.MenuPage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 18/11/19.
 */

public class PaymentRequest extends AppCompatActivity {
    private RecyclerView paymentRequest;
    private ImageView imagePaymentBack;
    private RelativeLayout relativeFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_request);

        paymentRequest=findViewById(R.id.payment_request);
        imagePaymentBack=findViewById(R.id.img_back);
        relativeFilter=findViewById(R.id.rel_filter);



        relativeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        imagePaymentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMenu=new Intent(getApplicationContext(), MenuPage.class);
                startActivity(goToMenu);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){
       super.onBackPressed();
        Intent toMenuPage=new Intent(this,MenuPage.class);
        startActivity(toMenuPage);
        finish();
    }

    public void paymentRequest(){

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new Interceptor() {
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
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ApiService apiService=retrofit.create(ApiService.class);
//        Call<> call=apiService.getProfile();
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, retrofit2.Response response) {
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//
//                t.printStackTrace();
//            }
//        });
//
//

    }


}
