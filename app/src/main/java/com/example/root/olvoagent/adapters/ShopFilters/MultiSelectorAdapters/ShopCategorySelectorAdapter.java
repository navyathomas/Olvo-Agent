package com.example.root.olvoagent.adapters.ShopFilters.MultiSelectorAdapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.ListCategoryShop.DataShopCategory;

import java.util.List;

/**
 * Created by root on 13/11/19.
 */

public class ShopCategorySelectorAdapter extends RecyclerView.Adapter<ShopCategorySelectorAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<DataShopCategory> dataShopCategories;
    Context context;


    public ShopCategorySelectorAdapter(Context mcontext,List<DataShopCategory> dataShopCategories){
        this.context=mcontext;
        this.dataShopCategories=dataShopCategories;

    }


    @Override
    public ShopCategorySelectorAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup,int position){
        View view=layoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_category_item,viewGroup,false);
        return new ShopCategorySelectorAdapter.ViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataShopCategory dataShopCategory=dataShopCategories.get(position);
        holder.tvShopCategoryName.setText(dataShopCategory.getShopCategoryName());

    }


    @Override
    public int getItemCount(){
        return dataShopCategories.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvShopCategoryName;
        CheckBox chckShopCategory;

        public ViewHolder(View view){
            super(view);
            tvShopCategoryName=view.findViewById(R.id.category_shopName);
            chckShopCategory=view.findViewById(R.id.category_shopSelector);
        }
    }
}
