package com.example.root.olvoagent.ui.ShopListPage;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.CardViewAdapter;

/**
 * Created by root on 10/10/19.
 */

public class CardViewActivity extends AppCompatActivity {
    private RecyclerView cardViewList;
    private RecyclerView.LayoutManager layoutManager;
    private CardViewAdapter cardViewActivity;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_route);

        final FragmentManager fragmentManager=getFragmentManager();
        final SelectShopDialog selectShopDialog=new SelectShopDialog();


        cardViewList=findViewById(R.id.recyclr_select_route);



    }


//
//    public void callshopId() {
//
//        Log.e("test", "test");
//        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
//
//        final String accessToken = preferences.getString("token", "");
//        final String employeeId = preferences.getString("employee_id", "");
//
//        Log.e("token", accessToken);
//        Log.e("employeeId", employeeId);
//
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request.Builder builder = request.newBuilder()
//                        .addHeader("Authorization", "Bearer " + accessToken);
//
//
//                Request request1 = builder.build();
//                return chain.proceed(request1);
//            }
//        }).addInterceptor(httpLoggingInterceptor).build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiService.BaseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        Call<ShopMainModel> call = apiService.getEmployee(employeeId);
//        call.enqueue(new Callback<ShopMainModel>() {
//            @Override
//            public void onResponse(Call<ShopMainModel> call, retrofit2.Response<ShopMainModel> response) {
//
//                Log.e("shop", "" + response.body());
//
//                setCardViewList(response.body().getDataModel().getShopListModels());
//
//                if (response.isSuccessful()) {
//                    ShopMainModel shopMainModel = response.body();
//
//                    if (shopMainModel.getStatus()) {
//
//                        SharedPreferences preference2 = getSharedPreferences("Myprefff", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = preference2.edit();
//
//                        editor.putString("_id", response.body().getDataModel().getShopListModels().get(0).getShopId());
//                        Log.e("size", response.body().getDataModel().getShopListModels().get(0).getShopId());
//
//                        editor.commit();
//
//                    } else {
//
//                        Toast.makeText(getApplicationContext(),"No shop available",Toast.LENGTH_LONG).show();
//                    }
//
//                } else {
//                    Toast.makeText(getApplicationContext(),response.body().getErrorModels().toString(), Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ShopMainModel> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//
//
//    private void setCardViewList(List<ShopListModel> shopListModelCard){
//        Context cardContext = getApplicationContext();
//        cardViewActivity=new CardViewAdapter(cardContext,shopListModelCard);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        cardViewList.setLayoutManager(linearLayoutManager);
//        cardViewList.setAdapter(cardViewActivity);
//
//    }
}
