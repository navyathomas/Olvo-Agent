package com.example.root.olvoagent.ui.invoice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.InvoiceHistory.PaymentHistoryMain;
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
 * Created by root on 18/11/19.
 */

public class Invoice extends AppCompatActivity {
    private RecyclerView invoiceHistory;
    private ImageView imageInvoiceback;
    private RelativeLayout relativeFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        relativeFilter = findViewById(R.id.rel_filter);
        imageInvoiceback = findViewById(R.id.img_back);
        invoiceHistory = findViewById(R.id.invoice_history);

        imageInvoiceback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMenu = new Intent(getApplicationContext(), MenuPage.class);
                startActivity(goToMenu);
                finish();
            }
        });


        relativeFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInvoiceFilter = new Intent(getApplicationContext(), InvoiceFilterFragment.class);
                startActivity(toInvoiceFilter);
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


    public void invoiceHistory() {

        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        String employeeId=preferences.getString("employee_id","");
        Log.e("",employeeId);

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
                .baseUrl("3.9.11.34:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<PaymentHistoryMain> call = apiService.invoiceHistory(employeeId,"INVOICE");
        call.enqueue(new Callback<PaymentHistoryMain>() {
            @Override
            public void onResponse(Call<PaymentHistoryMain> call, retrofit2.Response<PaymentHistoryMain> response) {

                Log.e("responsePayment",""+response.body());

            }

            @Override
            public void onFailure(Call<PaymentHistoryMain> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}