package com.example.root.olvoagent.ui.MultiSelector;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.ShopFilters.MultiSelectorAdapters.ShopRouteSelectorAdapter;
import com.example.root.olvoagent.models.RouteApiModel.DataArrayModel;
import com.example.root.olvoagent.models.RouteApiModel.RouteMainModel;
import com.example.root.olvoagent.ui.ShopListPage.AddShop;

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
 * Created by root on 12/11/19.
 */

public class ShopRouteSelector extends DialogFragment  {
    private RecyclerView routeSelectorShop;
    private ShopRouteSelectorAdapter routeSelectorAdapter;
    private List<DataArrayModel> routeSelectorData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multi_selector_shop, container, false);



        routeSelectorShop = view.findViewById(R.id.recyclr_add_shop_selector);
        callRoutApi();
        return view;
    }



    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(430, 400);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }


    public void callRoutApi() {

        final SharedPreferences preferences = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);

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

                        final List<DataArrayModel> dataArrayModels = routeMainModel.getDataArrayModels();

                        final ShopRouteSelectorAdapter.OnItemClickListener listener=new ShopRouteSelectorAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(DataArrayModel item) {

                                Toast.makeText(getActivity(),"clicked an item",Toast.LENGTH_LONG).show();
                            }
                        };

                        setRouteListAdapter(dataArrayModels,listener);


                        SharedPreferences preferencesRoute = getActivity().getSharedPreferences("RouteApi", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencesRoute.edit();

                        editor.putString("_id", response.body().getDataArrayModels().get(0).getRouteId());
                        editor.commit();

                    } else {
                        Toast.makeText(getActivity(), "error ", Toast.LENGTH_LONG).show();
                    }


                } else {

                    Toast.makeText(getActivity(), response.body().getErrorModel().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RouteMainModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });


    }


    private void setRouteListAdapter(List<DataArrayModel> dataArrayModels, ShopRouteSelectorAdapter.OnItemClickListener listener) {
        Context context = getActivity().getApplicationContext();
        routeSelectorAdapter = new ShopRouteSelectorAdapter(context, dataArrayModels,listener);
//        routeSelectorAdapter.setOnClickListener(new ShopRouteSelectorAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//                routeSelectorAdapter.onSetChecked(routeSelectorData);
//                Log.e("clickedPosition","position: "+position);
//            }
//        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        routeSelectorShop.setLayoutManager(linearLayoutManager);
        routeSelectorShop.setAdapter(routeSelectorAdapter);
    }


    private AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView adapterView,View view,int position,long id) {

            Intent intent=new Intent(getActivity(), AddShop.class);
            startActivity(intent);

            DataArrayModel dataArrayModel=new DataArrayModel();

            Toast.makeText(getActivity(),"onFragment Clicked",Toast.LENGTH_LONG).show();
        }
    };

}
