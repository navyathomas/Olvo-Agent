package com.example.root.olvoagent.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.DeleteRouteModel.DeleteMain;
import com.example.root.olvoagent.models.RouteApiModel.DataArrayModel;
import com.example.root.olvoagent.ui.Route.RouteList;

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

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.ViewHolder> {
    private Context mcontext;
    public List<DataArrayModel> dataArrayModels;


    public RouteListAdapter(Context context, List<DataArrayModel> dataArrayModels) {
        this.mcontext = context;
        this.dataArrayModels = dataArrayModels;
    }


    @Override
    public RouteListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_add_list, parent, false);
        return new RouteListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RouteListAdapter.ViewHolder holder, final int position) {

        final DataArrayModel dataArrayModel = dataArrayModels.get(position);
        holder.textRouteList.setText(dataArrayModel.getRouteName());

        holder.textRouteDescription.setText(dataArrayModel.getRouteDescription());


        holder.imageDelteRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog((RouteList) view.getContext());
                dialog.setContentView(R.layout.popup_delete_warning);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                Button deleteYes = dialog.findViewById(R.id.btn_delete_yes);
                Button deleteNo = dialog.findViewById(R.id.btn_delete_no);

                deleteYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        deleteRoute();
                        dataArrayModels.remove(holder.getAdapterPosition());
                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });

                deleteNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });


        holder.relativeRouteDescription.setVisibility(View.GONE);

        holder.imageArrowHidePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.relativeRouteDescription.getVisibility() == View.GONE) {
                    holder.imageArrowHidePicker.setRotation(270);
                    holder.relativeRouteDescription.setVisibility(View.VISIBLE);
                } else {
                    holder.imageArrowHidePicker.setRotation(360);
                    holder.relativeRouteDescription.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataArrayModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textRouteList, textRouteDescription;
        ImageView imageArrowHidePicker, imageDelteRoute;
        RelativeLayout relativeRouteDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            textRouteList = itemView.findViewById(R.id.tv_routeName);
            imageArrowHidePicker = itemView.findViewById(R.id.img_arrow_hide_picker);
            textRouteDescription = itemView.findViewById(R.id.tv_route_description);
            relativeRouteDescription = itemView.findViewById(R.id.rel_route_descrtn);
            imageDelteRoute = itemView.findViewById(R.id.img_delte_button);

        }
    }



    /**
     * Delete Route Api
     * (for adpter class-->RouteListAdapter)
     **/

    public void deleteRoute() {

        SharedPreferences preferencesRoute = mcontext.getSharedPreferences("RouteApi", Context.MODE_PRIVATE);
        String routeId = preferencesRoute.getString("_id", "");
        Log.e("", routeId);


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
        Call<DeleteMain> call = apiService.deleteRoute(routeId);
        call.enqueue(new Callback<DeleteMain>() {
            @Override
            public void onResponse(Call<DeleteMain> call, retrofit2.Response<DeleteMain> response) {

                if (response.isSuccessful()) {
                    DeleteMain deleteMain = response.body();

                    if (deleteMain.getDeleteStatus()) {



                        Toast.makeText(mcontext, response.body().getDeleteError().getDeleteMessage(), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(mcontext, response.body().getDeleteError().getDeleteTitle(), Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(mcontext, response.body().getDeleteError().getDeleteTitle(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DeleteMain> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }
}
