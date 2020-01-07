package com.example.root.olvoagent.adapters.ShopFilters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.root.olvoagent.R;
import com.example.root.olvoagent.models.ToupUpHistoryModel.HistoryDateModel;

import java.util.List;

/**
 * Created by root on 21/10/19.
 */

public class FilterTopupShop extends RecyclerView.Adapter<FilterTopupShop.ViewHolder> {
    private Context mcontext;
    public List<HistoryDateModel> topupHistory;

//
//    private  MyInterface myInterface;
//
//
//
//    public interface MyInterface{
//        void onCheckBoxClick(boolean isChecked);
//    }
//


/**AllcheckBox Checked or Multi selector..SelectAll**/
    boolean isSelceted;

    public void selected() {
        isSelceted = true;
        notifyDataSetChanged(); /*to notify the data has changed..To get Updates to recyclerview Adapter.**/
    }

    public void unSelect() {
        isSelceted = false;
        notifyDataSetChanged();
    }


    public FilterTopupShop(List<HistoryDateModel> topupHistory) {
        //this.mcontext = context;
        this.topupHistory = topupHistory;

    }



    @Override
    public FilterTopupShop.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_container_filter_shop_fragment, parent, false);
        return new FilterTopupShop.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final FilterTopupShop.ViewHolder holder, final int position) {

        HistoryDateModel historyDateModel = topupHistory.get(position);

        holder.textTopupHistory.setText(historyDateModel.getFilterShopName());


//      holder.shopListSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//          @Override
//          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//              myInterface.onCheckBoxClick(true);
//
//              if (holder.shopListSelector.isChecked()){
//                  HistoryDateModel historyDateModel = topupHistory.get(position);
//
//
//              }
//          }
//      });


        /**check whether the checkbox is checked..**/

        if (!isSelceted) {
            holder.shopListSelector.setChecked(false);
        } else {
            holder.shopListSelector.setChecked(true);
        }



    }

    @Override
    public int getItemCount() {
        return topupHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTopupHistory;
        CheckBox shopListSelector;

        public ViewHolder(View itemView) {
            super(itemView);

            textTopupHistory = itemView.findViewById(R.id.tv_topup_shops);
            shopListSelector = itemView.findViewById(R.id.checkbox_list);

        }
    }
}
