package com.example.root.olvoagent.ui.ShopListPage;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.ShopFilters.MultiSelectorAdapters.ShopRouteSelectorAdapter;
import com.example.root.olvoagent.models.AddShopModel.AddShopMainModel;
import com.example.root.olvoagent.models.RouteApiModel.DataArrayModel;
import com.example.root.olvoagent.ui.MultiSelector.ShopCategorySelector;
import com.example.root.olvoagent.ui.MultiSelector.ShopRegionSelector;
import com.example.root.olvoagent.ui.MultiSelector.ShopRouteSelector;
import com.example.root.olvoagent.ui.login.Login;

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
 * Created by root on 19/9/19.
 */

public class AddShop extends Activity {
    private ImageView imageBack;
    /**
     * static is common for all..static makes pgm
     * more efficient..memory is allocated only once..
     */
    public static EditText edtShopname, edtEmail, edtShopNumber, edtCountryCode, edtThreshold;
    private RelativeLayout reltAddShopSubmitButton;
    private static TextView edtShopRegion, edtRouteId, edtShopCategory;
    private CheckBox edtEnableVoip;
    private ImageView imageRouteSelector, imageRegionSelector, imageShopCategory;
    private ShopRouteSelectorAdapter shopRouteSelectorAdapter;
    private List<DataArrayModel> routeSelectorData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        //route Selector.//
        imageRegionSelector = findViewById(R.id.img_region_dropDown);

        imageRouteSelector = findViewById(R.id.img_route_selector);

        imageShopCategory = findViewById(R.id.img_shop_category);


//        final ShopRouteSelectorAdapter.OnItemClickListener listener=new ShopRouteSelectorAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(DataArrayModel item) {
//
//                edtRouteId.setText("fdjfkdk");
//
//                Toast.makeText(getApplicationContext(),"clicked an item",Toast.LENGTH_LONG).show();
//            }
//        };





        /**calling a Fragment into activity.FragmentManager is needed to call a fragment into activity/ **/
        final FragmentManager fragmentManager = getFragmentManager();
        //final SelectRouteDialogFrag selectRouteDialogFrag = new SelectRouteDialogFrag();

        final ShopRouteSelector shopMultiSelector = new ShopRouteSelector();  //For routeSelector..//

        final ShopCategorySelector shopCategorySelector = new ShopCategorySelector(); // For ShopCategoryList.//

        final ShopRegionSelector shopRegionSelector = new ShopRegionSelector(); //For ShopRegionSelector..//


        reltAddShopSubmitButton = findViewById(R.id.rel_button);

        edtShopname = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_shopEmail);
        edtCountryCode = findViewById(R.id.edt_country_code);
        edtShopNumber = findViewById(R.id.edt_shop_number);
        edtShopRegion = findViewById(R.id.tv_enable_region);
        edtThreshold = findViewById(R.id.relt_threshold);
        edtRouteId = findViewById(R.id.relt_route);
        edtShopCategory = findViewById(R.id.tv_shop_type);
        edtEnableVoip = findViewById(R.id.chck_voip);


        reltAddShopSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shopname = edtShopname.getText().toString();
                String shopEmail = edtEmail.getText().toString();
                String countryCodee = edtCountryCode.getText().toString();
                String shopNumber = edtShopNumber.getText().toString();
                String shopThreshold = edtThreshold.getText().toString();
                String shopRegion = edtShopRegion.getText().toString();
                String routeId = edtRouteId.getText().toString();
                String shopType = edtShopCategory.getText().toString();


                int checkChecked = 0;
                if (edtEnableVoip.isChecked()) {
                    checkChecked = 1;
                }

                sentAddShop(shopname, shopEmail, countryCodee, shopNumber, shopRegion, 0, checkChecked, 0, routeId, shopThreshold, shopType);
            }
        });


        imageBack = findViewById(R.id.img_qick_rechrge_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addShopBack = new Intent(AddShop.this, ShopActivity.class);
                startActivity(addShopBack);
            }
        });

        imageRouteSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shopMultiSelector.show(fragmentManager, null);

            }
        });


        imageShopCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopCategorySelector.show(fragmentManager, null);
            }
        });

        imageRegionSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopRegionSelector.show(fragmentManager, null);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent toShopActivity = new Intent(this, ShopActivity.class);
        toShopActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(toShopActivity);
        finish();
    }


    private void sentAddShop(String name, String email, String countryCode, String mobileNumber, String region,
                             Integer shopStatus, Integer enableVoip, Integer enableMobile, String routId, String threshold,
                             String shopCategory) {

        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        final String employeeId = preferences.getString("employee_id", "");
        final String accessToken = preferences.getString("token", "");

        Log.e("employeeId", employeeId);
        Log.e("token", accessToken);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {

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
        Call<AddShopMainModel> call = apiService.addShop(employeeId, name, email, countryCode, mobileNumber, region,
                0, enableVoip, 0, routId, threshold, shopCategory);
        call.enqueue(new Callback<AddShopMainModel>() {

            @Override
            public void onResponse(Call<AddShopMainModel> call, retrofit2.Response<AddShopMainModel> response) {


                if (response.isSuccessful()) {
                    AddShopMainModel addShopMainModel = response.body();

                    if (addShopMainModel.getAddShopstatus()) {


                        SharedPreferences preferencesAdd = getSharedPreferences("AddShop", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencesAdd.edit();

                        editor.putInt("status_code", response.body().getAddShopStatusCode());

                        Toast.makeText(getApplicationContext(), response.body().getErrorAddModel().getShopMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getErrorAddModel().getShopTitle(), Toast.LENGTH_LONG).show();
                    }


                } else {
                    onTokenExpiry();
                    Toast.makeText(getApplicationContext(), response.body().getErrorAddModel().getShopMessage(), Toast.LENGTH_LONG).show();
                }

                Log.e("addShop", "" + response.body());

            }

            @Override
            public void onFailure(Call<AddShopMainModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

    public void onTokenExpiry() {
        SharedPreferences preferenceAdd = getSharedPreferences("AddShop", MODE_PRIVATE);
        int status_code = preferenceAdd.getInt("status_code", 200);

        if (status_code == 401) {

            SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("token");

            Toast.makeText(getApplicationContext(), "Token Expired", Toast.LENGTH_LONG).show();
            Intent redirectToLogin = new Intent(AddShop.this, Login.class);
            startActivity(redirectToLogin);
        }
    }


}
