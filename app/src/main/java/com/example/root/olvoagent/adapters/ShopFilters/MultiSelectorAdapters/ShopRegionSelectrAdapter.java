package com.example.root.olvoagent.adapters.ShopFilters.MultiSelectorAdapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.ListRegionModel.RegionData;

import java.util.List;

/**
 * Created by root on 13/11/19.
 */

public class ShopRegionSelectrAdapter extends RecyclerView.Adapter<ShopRegionSelectrAdapter.ViewHolder> {

    public  LayoutInflater layoutInflater;
    private List<RegionData> regionDataList;
    private Context mcontext;


    public ShopRegionSelectrAdapter(Context context, List<RegionData> regionDataList) {
        this.mcontext = context;
        this.regionDataList = regionDataList;

    }


    @Override
    public ShopRegionSelectrAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vieww = layoutInflater.from(parent.getContext()).inflate(R.layout.shop_region_list, parent, false);
        return new ShopRegionSelectrAdapter.ViewHolder(vieww);
    }

    @Override
    public void onBindViewHolder(ShopRegionSelectrAdapter.ViewHolder viewHolder, int position) {
        RegionData regionData = regionDataList.get(position);
        viewHolder.textShopRegion.setText(regionData.getRegionName());

    }

    @Override
    public int getItemCount() {
        return regionDataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textShopRegion;


        public ViewHolder(View view) {
            super(view);
            textShopRegion = view.findViewById(R.id.tv_shop_region);
        }
    }

}
