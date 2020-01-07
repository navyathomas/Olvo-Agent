package com.example.root.olvoagent.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.LoginApiModel.DataModel;
import com.example.root.olvoagent.models.LoginApiModel.LoginMainModel;
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

public class Login extends AppCompatActivity {

    public EditText editTextUsername, editTextPassword;
    public Button loginButton;
    private List<DataModel> loginModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editext_username);
        editTextPassword = (EditText) findViewById(R.id.editext_password);


        /**Check whether, if  the user already LoggedIn.**/
        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        if (preferences.getBoolean("status", false)) {

            Intent goToMenuActivity = new Intent(Login.this, MenuPage.class);
            startActivity(goToMenuActivity);
            finish();
            return;

        }

        loginButton = (Button) findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();


                if (validateLogin(user, password)) {

                    callApi(user, password);
                }

            }
        });
    }


    private boolean validateLogin(String user, String password) {

        if (user == null || user.trim().length() == 0) {

            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            editTextPassword.setError("please enter password");
            return false;
        }
        return true;
    }


    public void callApi(String email, String password) {

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
        Call<LoginMainModel> call = apiService.getLogin(email, password);
        Log.e("log", " " + call);
        call.enqueue(new Callback<LoginMainModel>() {

            @Override
            public void onResponse(Call<LoginMainModel> call, retrofit2.Response<LoginMainModel> response) {

                if (response.isSuccessful()) {
                    LoginMainModel login = response.body();  //Main Model//

                    if (login.getStatus()) {   //true case//


                        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putBoolean("status", true).apply();

                        editor.putString("token", response.body().getDatas().getToken());
                        editor.putString("employee_id", response.body().getDatas().getEmployeeId());
                        editor.commit();


                        Intent loginActivity = new Intent(Login.this, MenuPage.class);
                        startActivity(loginActivity);
                        finish();
                    } else {

                        //false case//
                        Toast.makeText(getApplicationContext(), response.body().getError().getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                }

                response.body();

                //  Log.e("status", "" + response.body().getStatus());

            }

            @Override
            public void onFailure(Call<LoginMainModel> call, Throwable t) {
                t.printStackTrace();
                Log.e("error", "" + t.toString());

            }
        });

    }
}
