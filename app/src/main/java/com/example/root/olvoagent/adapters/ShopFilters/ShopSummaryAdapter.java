package com.example.root.olvoagent.adapters.ShopFilters;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.RouteApiModel.DataArrayModel;

import java.util.List;

/**
 * Created by root on 15/11/19.
 */

public class ShopSummaryAdapter extends RecyclerView.Adapter<ShopSummaryAdapter.SummaryViewHolder> {
    private LayoutInflater layoutInflater;
    private List<DataArrayModel> shopDataArray;

    @Override
    public ShopSummaryAdapter.SummaryViewHolder onCreateViewHolder(ViewGroup viewGroup,int position){
       View shopSummaryView=layoutInflater.inflate(R.layout.shop_summary_item,viewGroup,false);
        return  new ShopSummaryAdapter.SummaryViewHolder(shopSummaryView);
    }

    @Override
    public void onBindViewHolder(ShopSummaryAdapter.SummaryViewHolder summaryViewHolder,int i){
        DataArrayModel dataArrayModel=shopDataArray.get(i);

    }

    @Override
    public int getItemCount(){
        return 0;
    }


    public class SummaryViewHolder extends RecyclerView.ViewHolder{
        TextView textViewHolder;

        public SummaryViewHolder(View itemView){
            super(itemView);
        }
    }
}
