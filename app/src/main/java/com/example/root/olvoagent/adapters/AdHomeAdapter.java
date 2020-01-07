package com.example.root.olvoagent.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.olvoagent.ApiService.ApiService;
import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.HomeAdModel.AdsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 29/8/19.
 */

public class AdHomeAdapter extends RecyclerView.Adapter<AdHomeAdapter.ViewHolder> {
    public Context mcontext;
    public List<AdsModel> adHomeMainModel = new ArrayList<>();


    public AdHomeAdapter(Context mcontext, List<AdsModel> adHomeMainModels) {
        this.adHomeMainModel = adHomeMainModels;
        this.mcontext = mcontext.getApplicationContext();
    }

    @Override
    public AdHomeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ad_list_home_activity, viewGroup, false);
        return new AdHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdHomeAdapter.ViewHolder viewHolder, int position) {

        AdsModel homeMainModel = adHomeMainModel.get(position);

        Picasso.with(mcontext)
                .load("" + ApiService.BaseUrl + "" + homeMainModel.getImage())
                .into(viewHolder.imageAD);

        //viewFlipper.addView(imageView);

        // viewHolder.textAD.setText(homeMainModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return adHomeMainModel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageAD;
        private TextView textAD;


        public ViewHolder(View itemView) {
            super(itemView);
            imageAD = (ImageView) itemView.findViewById(R.id.img_ad);
            // textAD=(TextView)itemView.findViewById(R.id.tv_ad);
        }
    }
}
