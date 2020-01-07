package com.example.root.olvoagent.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.adapters.ShopFilters.FilterTopupShop;
import com.example.root.olvoagent.models.ToupUpHistoryModel.DateToFromMainApi;
import com.example.root.olvoagent.models.ToupUpHistoryModel.HistoryDateModel;

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
 * Created by root on 4/10/19.
 */

public class ShopFilterFragment extends Fragment {


    private RecyclerView shopRecycler;
    private CheckBox checkBoxAll;
    private FilterTopupShop filterTopupShop;
    private List<HistoryDateModel> topupHistory;
    private Context mcontext;


    public ShopFilterFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.container_filer_shop_fragment, container, false);

        shopRecycler = view.findViewById(R.id.recy_shop_list);
        checkBoxAll = view.findViewById(R.id.chechBox_all);

        return view;


        /**calling activity method into fragment..**/
//        Activity activity = getActivity();
//        if (activity instanceof RechargeHistory) {
//            RechargeHistory rechargeHistoryActivity = (RechargeHistory) activity;
//            rechargeHistoryActivity.callRechargeApi();
//        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkBoxAll = view.findViewById(R.id.chechBox_all);

        checkBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkBoxAll.isChecked()) {
                    filterTopupShop.selected();
                } else {
                    filterTopupShop.unSelect();
                    checkBoxAll.clearFocus();

                }
            }
        });
        topupFilter();
    }


    public void topupFilter() {
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        final String employeeId = preferences.getString("employee_id", "");
        final String accessToken = preferences.getString("token", "");

        Log.e("employee", employeeId);
        Log.e("token", accessToken);

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
        Call<DateToFromMainApi> call = apiService.getTopup(employeeId);
        call.enqueue(new Callback<DateToFromMainApi>() {
            @Override
            public void onResponse(Call<DateToFromMainApi> call, retrofit2.Response<DateToFromMainApi> response) {

                Log.e("response", "" + response.body());
                setTopupFilter(response.body().getDateModel().getHistoryDateModels());

                if (response.isSuccessful()) {
                    DateToFromMainApi dateToFromMainApi = response.body();

                    if (dateToFromMainApi.getStatus()) {

                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Filter", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                      //  editor.putString("shop_id", response.body().getDateModel().getHistoryDateModels().get(0).getShopId());


                    } else {

                        Toast.makeText(getActivity(), "Error Occured", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), response.body().getErrorDateModel().toString(), Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<DateToFromMainApi> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }


    private void setTopupFilter(final List<HistoryDateModel> topupHistory) {
        //Context context = getActivity();
        filterTopupShop = new FilterTopupShop(topupHistory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopRecycler.setLayoutManager(linearLayoutManager);
        shopRecycler.setAdapter(filterTopupShop);

    }
}
