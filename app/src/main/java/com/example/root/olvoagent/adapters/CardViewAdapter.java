package com.example.root.olvoagent.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.ShopApiModel.ShopListModel;

import java.util.List;

/**
 * Created by root on 10/10/19.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private Context mcontext;
    public List<ShopListModel> shopListModelCardView;



    public CardViewAdapter(Context context, List<ShopListModel> shopListModelCardView){
        this.mcontext=context;
        this.shopListModelCardView=shopListModelCardView;
    }


    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mcontext).inflate(R.layout.actvity_card_view,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(CardViewAdapter.ViewHolder holder, int position) {
        ShopListModel shopListModel = shopListModelCardView.get(position);

        holder.textView.setText(shopListModel.getShopName());

    }



    @Override
    public int getItemCount() {
        return shopListModelCardView.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.card_shop_list);
        }
    }
}
